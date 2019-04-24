package com.kien.website.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/images")
public class ImageController {

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    public HttpServletRequest request;

    String path = "/home/kien/images/";

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload")
    @CrossOrigin
    public @ResponseBody
    ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file) {
        logger.info("Upload an image");
        try {
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
        } catch (Exception e) {
            logger.error("Can't upload image",e);
        }
        return ResponseEntity.ok(request.getRequestURL().toString().replace("upload", file.getOriginalFilename()));
    }

    @GetMapping("/{url}")
    @ResponseBody
    public void getFile(@PathVariable("url") String url, HttpServletResponse response) throws Exception {
        logger.info("Getting a image");
        InputStream inputStream = new FileInputStream(
                new File(path + url)
        );
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

}
