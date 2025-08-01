package com.example.globalvariablesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalVariablesRequest {
    @JsonProperty("GlobalVariables")
    private List<GlobalVariable> GlobalVariables;
} 