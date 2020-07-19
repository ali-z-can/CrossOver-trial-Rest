package com.company.resourceapi.entities;

import java.time.Instant;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public @Data class PatchRequest{


    @Getter @Setter
    private long id;
    @Getter @Setter
    private String externalId;
    @Getter @Setter
    private String name = "thisnameshouldnotbeused";
    @Getter @Setter
    private SdlcSystem sdlcSystem;
    @Getter @Setter
    private Instant createdDate;
    @Getter @Setter
    private Instant lastModifiedDate;

}