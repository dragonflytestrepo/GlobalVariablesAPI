package com.example.globalvariablesapi.service;

import com.example.globalvariablesapi.model.GlobalVariable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GlobalVariablesService {
    
    // Using ConcurrentHashMap for thread-safe operations
    private final Map<String, Map<String, String>> storage = new ConcurrentHashMap<>();
    
    /**
     * Store or update global variables for a given ID
     */
    public void storeGlobalVariables(GlobalVariable globalVariable) {
        String id = globalVariable.getId();
        Map<String, String> variables = globalVariable.getVariables();
        
        if (variables != null && !variables.isEmpty()) {
            // If ID exists, merge the new variables with existing ones
            // If ID doesn't exist, create new entry
            storage.merge(id, new HashMap<>(variables), (existing, newVars) -> {
                existing.putAll(newVars);
                return existing;
            });
        }
    }

    /**
     * Patch (partial update) global variables for a given ID
     */
    public void patchGlobalVariables(String id, Map<String, String> updates) {
        storage.computeIfPresent(id, (key, existing) -> {
            existing.putAll(updates);
            return existing;
        });
    }
    
    /**
     * Get global variables for a given ID
     */
    public Map<String, String> getGlobalVariables(String id) {
        return storage.getOrDefault(id, new HashMap<>());
    }
    
    /**
     * Delete global variables for a given ID
     */
    public boolean deleteGlobalVariables(String id) {
        return storage.remove(id) != null;
    }
    
    /**
     * Check if global variables exist for a given ID
     */
    public boolean exists(String id) {
        return storage.containsKey(id);
    }
    
    /**
     * Get all stored IDs (for debugging/admin purposes)
     */
    public Map<String, Map<String, String>> getAllVariables() {
        return new HashMap<>(storage);
    }
} 