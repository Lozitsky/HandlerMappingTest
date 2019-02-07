package com.kirilo.springMVC.models;

import java.util.ArrayList;
import java.util.List;

public class MultiUploadedFile {
    List<UploadedFile> files = new ArrayList<UploadedFile>();

    public MultiUploadedFile(int count) {
        for (int i = 0; i < count; i++) {
            files.add(new UploadedFile());
        }
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }
}
