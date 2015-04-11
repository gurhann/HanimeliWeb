/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author dcimen
 */
@FacesValidator("com.dukefuns.hanimeliweb.tools.telephonevalidator")
public class TelephoneValidator implements Validator {

    private static final String EMAIL_PATTERN = "^(5)[0-9][0-9][1-9]([0-9]){6}$";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Geçersiz Telefon Numarası.", "Geçersiz Telefon Numarası.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
