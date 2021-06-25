package com.jlbrown.apps.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResult {

    private String action;
    private RequestResultParameters parameters;

}
