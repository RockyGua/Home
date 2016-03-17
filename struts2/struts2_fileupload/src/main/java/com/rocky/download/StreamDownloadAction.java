package com.rocky.download;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StreamDownloadAction {

    private String fileName;
    public String execute() {
        return Action.SUCCESS;
    }

    //getXXX名字应该与struts.xml中的参数名一致
    public InputStream getMyInputStream() throws FileNotFoundException {
        HttpServletRequest request = ServletActionContext.getRequest();

        String path = request.getRealPath("/download");
        return new FileInputStream(new File(path, fileName));
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
