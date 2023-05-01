package com.yinchrn.demo5.controller;

import com.yinchrn.demo5.pojo.MyFile;
import com.yinchrn.demo5.pojo.Param;
import com.yinchrn.demo5.service.FileService;
import com.yinchrn.demo5.serviceimpl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/file")
@RestController
public class FileController {
    private final FileService fileService;
    @Autowired
    FileController(FileServiceImpl fileService){
        this.fileService = fileService;
    }

    @GetMapping("/all")
    List<MyFile> getMyFiles(){
        return fileService.findMyFiles();
    }

    @PostMapping("/upload")
    Param uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return new Param("error","文件为空");
        }
        fileService.uploadFile(file);
        return new Param("success","上传成功");
    }

    @GetMapping("/download/{id}")
    ResponseEntity<Resource> downloadFile(@PathVariable("id") int id) throws IOException {
        MyFile myFile = fileService.getFile(id);

        File directory = new File(".");
        log.info("{}",directory.getCanonicalPath());
        String path = directory.getCanonicalPath()+"/files_home/"+myFile.getUid()+"/"+myFile.getLocal_name();
        Resource resource = new FileSystemResource(path);

        //需要对含中文的字符串进行编码，不然会报错
        //在 URLEncoder 编码中会把 “ ” 替换为 “+”，所有使用 Spring 中的 UriUtils 编码方法
        String encodedFileName = UriUtils.encode(myFile.getName(), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;"
                        + "filename=" + encodedFileName)
                .body(resource);
    }

    @DeleteMapping("/delete/{id}")
    Param deleteFile(@PathVariable int id) throws IOException {
        if (fileService.deleteFile(id)){
            return new Param("success","删除成功");
        }
        else {
            return new Param("success", "删除失败");
        }
    }
    @GetMapping("/query/{name}")
    List<MyFile> queryMyFile(@PathVariable String name){
        return fileService.queryMyFile(name);
    }

    //接收 id 和 name
    @PostMapping("/modify")
    Param modifyMyFile(@RequestBody Map<String, Object> map){
        if (fileService.modifyMyFile(map)){
            return new Param("success", "修改成功");
        }
        return new Param("error", "修改失败，文件已不存在");
    }
}
