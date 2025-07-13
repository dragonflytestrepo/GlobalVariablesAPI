package com.example.globalvariablesapi.controller;

import com.example.globalvariablesapi.model.GlobalVariable;
import com.example.globalvariablesapi.model.GlobalVariablesRequest;
import com.example.globalvariablesapi.service.GlobalVariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/global-vars")
public class GlobalVariablesController {
    
    @Autowired
    private GlobalVariablesService globalVariablesService;
    
    /**
     * POST endpoint to create or update global variables
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> storeGlobalVariables(@RequestBody GlobalVariablesRequest request) {
        try {
            if (request.getGlobalVariables() == null || request.getGlobalVariables().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "GlobalVariables array cannot be empty");
                return ResponseEntity.badRequest().body(response);
            }
            
            for (GlobalVariable globalVariable : request.getGlobalVariables()) {
                if (globalVariable.getId() == null || globalVariable.getId().trim().isEmpty()) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("error", "ID cannot be null or empty");
                    return ResponseEntity.badRequest().body(response);
                }
                
                // Store the global variable directly
                globalVariablesService.storeGlobalVariables(globalVariable);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Global variables stored successfully");
            response.put("count", request.getGlobalVariables().size());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Failed to store global variables: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * GET endpoint to retrieve global variables by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getGlobalVariables(@PathVariable String id) {
        Map<String, String> variables = globalVariablesService.getGlobalVariables(id);
        
        if (variables.isEmpty() && !globalVariablesService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(variables);
    }
    
    /**
     * DELETE endpoint to remove global variables by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteGlobalVariables(@PathVariable String id) {
        boolean deleted = globalVariablesService.deleteGlobalVariables(id);
        
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Global variables deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Global variables not found");
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET endpoint to retrieve all global variables (for debugging/admin purposes)
     */
    @GetMapping
    public ResponseEntity<Map<String, Map<String, String>>> getAllGlobalVariables() {
        Map<String, Map<String, String>> allVariables = globalVariablesService.getAllVariables();
        return ResponseEntity.ok(allVariables);
    }
} 