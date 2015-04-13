/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.exclusionstrategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 *
 * @author gurhan
 */
public class AdresCountyGetTownExclusionStrategy implements ExclusionStrategy{

    @Override
    public boolean shouldSkipField(FieldAttributes fa) {
        String className = fa.getDeclaredClass().getName();
        String fieldName = fa.getName();
        System.out.println(className + "->" + fieldName);
        return className.equals("java.util.List") &&
                fieldName.equals("towns");
        
    }

    @Override
    public boolean shouldSkipClass(Class<?> type) {
        return false;
    }
    
}
