package com.yinchrn.demo5.service;


import com.yinchrn.demo5.pojo.UserAccount;

import java.io.IOException;

public interface UserService {
    UserAccount MyAccount();
    Boolean register (UserAccount userAccount);
    void deleteMyAccount() throws IOException;
}
