package com.example.demo.controller;

import com.example.demo.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
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

    @GetMapping("/download")
    public void getAllFiles(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        fileService.getAllFiles(response.getOutputStream());
    }

    @PostMapping(consumes = "multipart/form-data")
    public void saveFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.saveFile(file.getOriginalFilename(), file.getInputStream(), file.getSize(), file.getContentType());
    }
}
