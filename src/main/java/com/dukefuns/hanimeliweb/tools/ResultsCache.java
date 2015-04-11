/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.tools;

import java.io.Serializable;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author dcimen
 */
@Named
@RequestScoped
public class ResultsCache implements Serializable {

    private boolean result;

    public ResultsCache() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
