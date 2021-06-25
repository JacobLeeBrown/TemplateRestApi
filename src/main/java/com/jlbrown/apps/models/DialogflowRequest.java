package com.jlbrown.apps.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DialogflowRequest {

    private RequestResult result;

}
