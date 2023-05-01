package com.yinchrn.demo5.service;

import com.yinchrn.demo5.pojo.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    List<MyFile> findMyFiles();
    void uploadFile(MultipartFile file) throws IOException;
    MyFile getFile(int id);
    Boolean deleteFile(int id) throws IOException;
    List<MyFile> queryMyFile(String name);
    Boolean modifyMyFile(Map<String, Object> map);
}
