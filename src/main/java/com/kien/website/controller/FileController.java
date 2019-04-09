package com.kien.website.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/files")
public class FileController{

    @Autowired
    public HttpServletRequest request;

    String path = "/home/kien/images/";

    @PostMapping("/upload")
    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody String fileUpload(@RequestParam("file") MultipartFile file)
            throws IOException {
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream =
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File(path + file.getOriginalFilename())
                            )
                    );
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        return request.getRequestURL().toString().replace("upload",file.getOriginalFilename());
    }

    @GetMapping("/{url}")
    @ResponseBody
    public void getFile(@PathVariable("url") String url, HttpServletResponse response) throws Exception {
            InputStream inputStream = new FileInputStream(
                    new File(path + url)
            );
            IOUtils.copy(inputStream,response.getOutputStream());
            response.flushBuffer();
    }

}
