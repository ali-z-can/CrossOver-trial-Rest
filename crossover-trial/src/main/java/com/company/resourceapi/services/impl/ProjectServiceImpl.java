package com.company.resourceapi.services.impl;

import java.time.Instant;

import javax.validation.Valid;

import com.company.resourceapi.entities.PatchRequest;
import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import com.company.resourceapi.exceptions.NotFoundException;
import com.company.resourceapi.exceptions.AllreadyExistsException;
import com.company.resourceapi.repositories.ProjectRepository;
import com.company.resourceapi.repositories.SdlcSystemRepository;
import com.company.resourceapi.services.ProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SdlcSystemRepository sdlcSystemRepository;

	public Project getProject(long id) {
		return projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class, id));
	}

	public Project createProject(Project project) {

		boolean check = projectRepository.existsBySdlcSystemIdAndExternalId(project.getSdlcSystem().getId(),project.getExternalId());
		if(check){
			throw new AllreadyExistsException(project.getId());
		}
		else{
			project.setSdlcSystem(sdlcSystemRepository.findById(project.getSdlcSystem().getId()).orElseThrow(() -> new NotFoundException(SdlcSystem.class, project.getSdlcSystem().getId())));
			project.setCreatedDate(Instant.now());
			project.setLastModifiedDate(Instant.now());
			return projectRepository.save(project);
		}
		
	}

	public Project updateProject(long projectId, @Valid PatchRequest patchRequest) {

		
		Project check = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException(Project.class, projectId));
		
		String tempExternalId = check.getExternalId();
		SdlcSystem tSdlcSystem = sdlcSystemRepository.findById(check.getSdlcSystem().getId()).orElseThrow(() -> new NotFoundException(SdlcSystem.class, patchRequest.getSdlcSystem().getId()));

		if(patchRequest.getExternalId() != null){
			check.setExternalId(patchRequest.getExternalId());
		}
		
		

		if(patchRequest.getSdlcSystem() != null){
			check.setSdlcSystem(sdlcSystemRepository.findById(patchRequest.getSdlcSystem().getId()).orElseThrow(() -> new NotFoundException(SdlcSystem.class, patchRequest.getSdlcSystem().getId())));
		}

		if(patchRequest.getName() == null){
			check.setName(patchRequest.getName());
		}
		else if(!patchRequest.getName().equals("thisnameshouldnotbeused")){
			check.setName(patchRequest.getName());
		}
		check.setLastModifiedDate(Instant.now());

		boolean flag = projectRepository.existsBySdlcSystemIdAndExternalId(check.getSdlcSystem().getId(),check.getExternalId());
		if(flag && (!tSdlcSystem.equals(sdlcSystemRepository.findById(check.getSdlcSystem().getId()).orElseThrow(() -> new NotFoundException(SdlcSystem.class, patchRequest.getSdlcSystem().getId()))
		) || !tempExternalId.equals(check.getExternalId())) ){
			throw new AllreadyExistsException(check.getId());
		}
		else{
			Project updatedProject = projectRepository.save(check);
			return updatedProject;
		}
		
	}

	
}
