package com.yinchrn.demo5.controller;

import com.yinchrn.demo5.pojo.Param;
import com.yinchrn.demo5.service.ShareService;
import com.yinchrn.demo5.serviceimpl.ShareServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/share")
public class ShareController {
    ShareService shareService;

    public ShareController(ShareServiceImpl shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/create/{fid}")
    Param createShareKey(@PathVariable int fid){
        String key = shareService.createShareKey(fid);
        return new Param("success",key);
    }
    @GetMapping("/get/{key}")
    Param getSharedFile(@PathVariable String key) throws IOException {
        if (shareService.getSharedFile(key)){
            return new Param("success","获取成功");
        }
        return new Param("error","文件已失效");
    }
}
