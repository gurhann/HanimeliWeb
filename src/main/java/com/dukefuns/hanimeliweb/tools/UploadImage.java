/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dursun
 */
public class UploadImage implements Serializable {

    private Part file1;
    private List<Part> files;
    private Pattern pattern;
    private Matcher matcher;
    private Date date = new Date();
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
    private CleanUrl crul = new CleanUrl();
    private static final String IMAGE_PATTERN
            = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    private List<String> uploadAttachment = new ArrayList<String>();//getters and setters

    public void upload(FileUploadEvent event) throws IOException {

        handleFileUpload(event.getFile());

    }

    public void handleFileUpload(UploadedFile event) throws IOException {
        date = new Date();
        String name = crul.produceUrl(event.getFileName());
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"/img/" + name;
        File targetFolder = new File(path);
        InputStream inputStream = event.getInputstream();
        OutputStream out = new FileOutputStream(targetFolder);
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        inputStream.close();
        out.flush();
        out.close();
        uploadAttachment.add(name);

    }

    public List<String> getUploadAttachment() {
        return uploadAttachment;
    }

    public void setUploadAttachment(List<String> uploadAttachment) {
        this.uploadAttachment = uploadAttachment;
    }

    public UploadImage() {
        pattern = Pattern.compile(IMAGE_PATTERN);

    }


    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public List<Part> getFiles() {
        return files;
    }

    public void setFiles(List<Part> files) {
        this.files = files;
    }

}
