package com.github.wangchenning.controller;

import com.github.wangchenning.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "D:\\workspace\\wcn-security\\wcn-security-demo\\src\\main\\java\\com\\github\\wangchenning\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());

    }

    @GetMapping("/{id}")
    public void download(HttpServletResponse response, @PathVariable("id") String id) {
        try (InputStream is = new FileInputStream(new File(folder, id + ".txt"))
             ; OutputStream os = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(is, os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
