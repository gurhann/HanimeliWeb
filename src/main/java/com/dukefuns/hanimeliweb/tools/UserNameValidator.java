/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.tools;

import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.model.Person;
import javax.ejb.EJB;
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
@FacesValidator("usernamevalidator")
public class UserNameValidator implements Validator {
    @EJB
    PersonDao pDao;
    public UserNameValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Person person = pDao.findUserByUserName(value.toString());
        
        if (person != null) {
            FacesMessage msg = new FacesMessage("Bu Kullanıcı Adı Kayıtlı.", "Bu Kullanıcı Adı Kayıtlı.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
