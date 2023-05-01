package com.yinchrn.demo5.serviceimpl;

import com.yinchrn.demo5.mapper.FileMapper;
import com.yinchrn.demo5.pojo.MyFile;
import com.yinchrn.demo5.pojo.UserAccount;
import com.yinchrn.demo5.service.FileService;
import com.yinchrn.demo5.service.UserService;
import com.yinchrn.demo5.utils.GetSizeofFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;
    private final UserService userService;
    @Autowired
    FileServiceImpl(FileMapper fileMapper, UserServiceImpl userService){
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MyFile> findMyFiles() {
        UserAccount MyAccount = userService.MyAccount();
        return fileMapper.findMyFiles(MyAccount.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadFile(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();

        UserAccount MyAccount = userService.MyAccount();
        int uid = MyAccount.getId();

        long size = file.getSize();
        String observed_size = GetSizeofFile.getSize(size);
        String uuid = UUID.randomUUID().toString();

        Date date = new Date();

        File directory = new File(".");
        String path = directory.getCanonicalPath()+"/files_home/"+uid;
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        int index = name.lastIndexOf(".");

        if(index != -1 && index+1<name.length()){
            String suffix = name.substring(index+1);
            String local_name = uuid+"."+suffix;
            fileMapper.uploadFile(new MyFile(suffix, name, size, local_name, observed_size, uid, date));
            file.transferTo(new File(path+"/"+local_name));
        }
        else {
            fileMapper.uploadFile(new MyFile("file", name, size, uuid, observed_size, uid, date));
            file.transferTo(new File(path+"/"+uuid));
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MyFile getFile(int id) {
        UserAccount MyAccount = userService.MyAccount();
        int uid = MyAccount.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("uid", uid);
        return fileMapper.getFile(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteFile(int id) throws IOException {
        UserAccount MyAccount = userService.MyAccount();
        int uid = MyAccount.getId();

        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("uid", uid);

        MyFile file = fileMapper.getFile(map);
        if (file == null)
            return false;
        String local_name = file.getLocal_name();
        File directory = new File(".");
        String path = directory.getCanonicalPath()+"/files_home/"+uid+"/"+local_name;
        File deleteFilepath = new File(path);
        if(deleteFilepath.delete()){
            fileMapper.deleteFile(map);
            return true;
        }
        return false;
    }

    @Override
    public List<MyFile> queryMyFile(String name) {
        UserAccount myAccount = userService.MyAccount();
        int uid = myAccount.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("name", name);
        return fileMapper.queryMyFile(map);
    }

    @Override
    public Boolean modifyMyFile(Map<String, Object> map) {
        UserAccount myAccount = userService.MyAccount();
        int uid = myAccount.getId();
        map.put("uid", uid);
        MyFile file = fileMapper.getFile(map);
        if (file == null)
            return false;
        String name = (String) map.get("name");
        int index = name.lastIndexOf(".");
        if(index != -1 && index+1<name.length()){
            String suffix = name.substring(index+1);
            map.put("type",suffix);
        }else {
            map.put("type","file");
        }
        fileMapper.modifyMyFile(map);
        return true;
    }


}
