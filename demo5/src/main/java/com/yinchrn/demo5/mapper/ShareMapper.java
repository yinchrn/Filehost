package com.yinchrn.demo5.mapper;

import com.yinchrn.demo5.pojo.ShareLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ShareMapper {
    void createShareKey(ShareLink shareLink);
    ShareLink getSharedLink(String key);
    void destroySharedLink(@Param("id") int id);
}
