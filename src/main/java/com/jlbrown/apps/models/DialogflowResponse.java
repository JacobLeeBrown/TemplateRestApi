package com.jlbrown.apps.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DialogflowResponse {

    private String status;
    private String message;

}
