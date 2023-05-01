package com.yinchrn.demo5.mapper;

import com.yinchrn.demo5.pojo.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface FileMapper {
    List<MyFile> findMyFiles(@Param("uid") int uid);
    void uploadFile(MyFile file);
    MyFile getFile(Map<String, Object> map);
    void deleteFile(Map<String, Object> map);
    void deleteMyFiles(@Param("uid") int uid);

    List<MyFile> queryMyFile(Map<String,Object> map);
    void modifyMyFile(Map<String,Object> map);
}
