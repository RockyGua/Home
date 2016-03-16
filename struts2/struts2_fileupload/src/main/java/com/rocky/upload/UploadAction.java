package com.rocky.upload;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class UploadAction extends ActionSupport {

    private File file;

    /**
     * fileFileName,fileContentType两个属性的属性名是不能更改的
     * 在fileUploadInterceptor中默认使用这两个名字。
     */
    private String fileFileName;
    private String fileContentType;

    public String upload() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getRealPath("");
        InputStream in = new FileInputStream(file);
        OutputStream out = new FileOutputStream(new File(path, fileFileName));
        byte[] buffer = new byte[200];
        int len = 0;
        while ((len = in.read(buffer))!=-1)
        {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
        return Action.SUCCESS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}
