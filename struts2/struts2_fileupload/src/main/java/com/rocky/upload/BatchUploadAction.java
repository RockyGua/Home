package com.rocky.upload;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class BatchUploadAction {

    private File[] files;
    private String[] filesFileName;
    private String[] filesContentType;

    public String execute() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getRealPath("");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        for (int i=0; i<files.length; i++) {
            inputStream = new FileInputStream(files[i]);
            outputStream = new FileOutputStream(new File(path, filesFileName[i]));
            byte[] buffer = new byte[200];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }
        if (null != inputStream && null != outputStream) {
            outputStream.close();
            inputStream.close();
        }

        return Action.SUCCESS;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public String[] getFilesFileName() {
        return filesFileName;
    }

    public void setFilesFileName(String[] filesFileName) {
        this.filesFileName = filesFileName;
    }

    public String[] getFilesContentType() {
        return filesContentType;
    }

    public void setFilesContentType(String[] filesContentType) {
        this.filesContentType = filesContentType;
    }
}
