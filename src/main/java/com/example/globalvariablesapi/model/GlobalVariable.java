package com.example.globalvariablesapi.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalVariable {
    private String id;
    private Map<String, String> variables = new HashMap<>();
    
    @JsonAnySetter
    public void setVariable(String key, String value) {
        if (!"id".equals(key)) {
            variables.put(key, value);
        }
    }
} 