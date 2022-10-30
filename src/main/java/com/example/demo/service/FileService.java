package com.example.demo.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileService {

    final File folder = new File("C:\\Users\\developer\\Desktop\\images");

    public List<String> getFiles() {
        return listFilesForFolder(folder);
    }

    public List<String> listFilesForFolder(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                files.add(fileEntry.getName());
            }
        }
        return files;
    }

    public InputStream getFile(String file) throws FileNotFoundException {
        String pathname = folder.getPath() + '\\' + file;
        File initialFile = new File(pathname);
        return new FileInputStream(initialFile);
    }

    public void getAllFiles(ServletOutputStream outputStream) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(outputStream));

        List<String> files = listFilesForFolder(folder);
        for (String file : files) {
            InputStream inputStream = getFile(file);

            ZipEntry ze = new ZipEntry(file);
            zos.putNextEntry(ze);
            byte[] bytes = new byte[1024];
            int count = inputStream.read(bytes);
            while (count > -1)
            {
                zos.write(bytes, 0, count);
                count = inputStream.read(bytes);
            }
            inputStream.close();
            zos.closeEntry();
        }
        zos.close();
    }

    public void saveFile(String originalFilename, InputStream inputStream, long size, String contentType) throws IOException {
        File file = new File("C:\\Users\\developer\\Desktop\\images\\" + originalFilename);
        OutputStream os = new FileOutputStream(file);
        IOUtils.copy(inputStream,os);
        inputStream.close();
        os.close();
    }
}
