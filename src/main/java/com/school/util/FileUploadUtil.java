package com.school.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zhouchunjie on 16/4/11.
 */
public class FileUploadUtil {

    public static final String FILE_PATH = "/upload/";

    //文件上传
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        File tempFile = new File(FILE_PATH, new Date().getTime() + String.valueOf(fileName));
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return "/download?fileName=" + tempFile.getName();
    }

    public static File getFile(String fileName) {
        return new File(FILE_PATH, fileName);
    }

}
