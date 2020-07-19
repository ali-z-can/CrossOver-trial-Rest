package com.company.resourceapi.services;

import javax.validation.Valid;

import com.company.resourceapi.entities.PatchRequest;
import com.company.resourceapi.entities.Project;

public interface ProjectService {

    Project getProject(long id);
    Project createProject(Project project);
	Project updateProject(long projectId, @Valid PatchRequest patchRequest);
}
