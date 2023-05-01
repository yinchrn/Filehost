package com.yinchrn.demo5.serviceimpl;

import com.yinchrn.demo5.mapper.FileMapper;
import com.yinchrn.demo5.mapper.ShareMapper;
import com.yinchrn.demo5.pojo.MyFile;
import com.yinchrn.demo5.pojo.ShareLink;
import com.yinchrn.demo5.pojo.UserAccount;
import com.yinchrn.demo5.service.FileService;
import com.yinchrn.demo5.service.ShareService;
import com.yinchrn.demo5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final UserService userService;
    private final FileMapper fileMapper;
    @Autowired
    public ShareServiceImpl(ShareMapper shareMapper, UserServiceImpl userService, FileMapper fileMapper) {
        this.shareMapper = shareMapper;
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createShareKey(int fid) {
        UserAccount myAccount = userService.MyAccount();
        int uid = myAccount.getId();
        String link= UUID.randomUUID().toString();
        Date create_date = new Date();
        ShareLink shareLink = new ShareLink(null, fid, uid, link, create_date);
        shareMapper.createShareKey(shareLink);
        return link;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean getSharedFile(String link) throws IOException {

        ShareLink sharedLink = shareMapper.getSharedLink(link);
        if (sharedLink == null)
            return false;
        UserAccount myAccount = userService.MyAccount();
        int uid = myAccount.getId();
        if(sharedLink.getUid() == uid)
            return true;

        Map<String, Object> map = new HashMap<>();
        map.put("uid",sharedLink.getUid());
        map.put("id",sharedLink.getFid());
        MyFile file = fileMapper.getFile(map);
        if (file == null){
            shareMapper.destroySharedLink(sharedLink.getId());
            return false;
        }

        File localPath = new File(".");
        String commonPath = localPath.getCanonicalPath()+"/files_home";
        String sourcePath = commonPath+"/"+sharedLink.getUid()+"/"+file.getLocal_name();
        Resource resource = new FileSystemResource(sourcePath);
        InputStream resourceInputStream = resource.getInputStream();
        String dest = commonPath + "/" +uid;
        File dir = new File(dest);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File destPath = new File(dest+"/"+file.getLocal_name());
        OutputStream destOutputStream = new FileOutputStream(destPath);
        FileCopyUtils.copy(resourceInputStream, destOutputStream);
        file.setUid(uid);
        file.setCreate_date(new Date());
        fileMapper.uploadFile(file);
        return true;
    }
}
