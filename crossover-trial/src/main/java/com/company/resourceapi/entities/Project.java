package com.company.resourceapi.entities;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project")
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(name = "external_id", nullable = false)
    @NotBlank
    @Getter @Setter
    private String externalId;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "sdlc_system_id")
    @NotNull
    @Getter @Setter
    private SdlcSystem sdlcSystem;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    @Getter @Setter
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    @Getter @Setter
    private Instant lastModifiedDate;

    
    
}