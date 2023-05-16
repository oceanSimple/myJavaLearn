package com.ocean.controller;

import com.ocean.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 处理文件的上传与下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${website.localImagePath}")
    private String basePath;

    @Value("${website.imagePath}")
    private String imagePath;

    @PostMapping("/food/upload")
    public R<String> upload(@RequestPart("file") MultipartFile file) {
        String newPath = basePath + "/foodImages/";
        //获取原始文件名，以此获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 通过uuid生成随机文件名
        String fileName = UUID.randomUUID() + suffix;
        // 判断目标目录是否存在，若不存在就创建
        File dir = new File(newPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            System.out.println(newPath + fileName);
            file.transferTo(new File(newPath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(imagePath + "/foodImages/" + fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            // 输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            // 输出流，通过输出流将文件返回浏览器
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpg");//告诉浏览器返回的是图片
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            // 关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
