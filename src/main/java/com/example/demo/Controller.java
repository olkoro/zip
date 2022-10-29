package com.example.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class Controller {

    private final FileService fileService;

    public Controller(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/files")
    public List<String> getFiles() {
        return fileService.getFiles();
    }

    @GetMapping("/file")
    public ResponseEntity<InputStreamResource> getFile(@RequestParam(value = "file") String file) throws FileNotFoundException {
        InputStream is = fileService.getFile(file);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(headers)
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePrivate())
                .body(new InputStreamResource(is));
    }

    @GetMapping("/files/download")
    public void getAllFiles(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        fileService.getAllFiles(response.getOutputStream());
    }
}
