package com.yinchrn.demo5.serviceimpl;


import com.yinchrn.demo5.mapper.FileMapper;
import com.yinchrn.demo5.mapper.UserMapper;
import com.yinchrn.demo5.pojo.UserAccount;
import com.yinchrn.demo5.service.UserService;
import com.yinchrn.demo5.utils.DeleteAllFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final FileMapper fileMapper;

    @Autowired
    UserServiceImpl(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    @Override
    public UserAccount MyAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userMapper.findByUsername(username);
    }

    @Override
    @Transactional
    public Boolean register(UserAccount userAccount) {
        if (userMapper.findByUsername(userAccount.getUsername())==null)
        {
            userMapper.register(userAccount);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteMyAccount() throws IOException {
        UserAccount MyAccount = MyAccount();
        int uid = MyAccount.getId();
        File directory = new File(".");
        String path = directory.getCanonicalPath()+"/files_home/"+uid;
        File deletePath = new File(path);
        DeleteAllFiles.delete(deletePath);
        userMapper.deleteMyAccount(uid);
        fileMapper.deleteMyFiles(uid);
    }
}



