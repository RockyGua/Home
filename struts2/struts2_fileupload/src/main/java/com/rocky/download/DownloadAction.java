package com.rocky.download;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadAction {

    public String execute() throws IOException {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String path = request.getRealPath("/download");
        File file = new File(path + "/uploadTest.txt");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        response.setContentLength((int)file.length());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=uploadTest.txt");
        byte[] buffer = new byte[200];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }
        return null;
    }
}
