/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.tools;

/**
 *
 * @author dcimen
 */
import java.io.Serializable;

/**
 *
 * @author dursun
 */
public class CleanUrl implements Serializable {

    private char[] eskichar = {'ş', 'ı', 'ü', 'ö', 'ç', 'ş', 'ı', 'ğ', 'ö', 'ç', 'ü', ' ', ',', '_', '"', '\'', '*', '%'};
    private char[] yenichar = {'s', 'i', 'u', 'o', 'c', 's', 'i', 'g', 'o', 'c', 'u', '-', '-', '-', '-', '-', '-', '-'};

    public String produceUrl(String cleanurl) {
        cleanurl = cleanurl.toLowerCase();
        cleanurl = cleanurl.replace(" ", "-");
        char[] eskichar = {'ş', 'ı', 'ü', 'ö', 'ç', 'ş', 'ı', 'ğ', 'ö', 'ç', 'ü', ' ', ',', '_', '"', '\''};
        char[] yenichar = {'s', 'i', 'u', 'o', 'c', 's', 'i', 'g', 'o', 'c', 'u', '-', '-', '-', '-', '-'};
        for (int i = 0; i < 11; i++) {

            cleanurl = cleanurl.replace(eskichar[i], yenichar[i]);
        }
        return cleanurl;

    }
}
