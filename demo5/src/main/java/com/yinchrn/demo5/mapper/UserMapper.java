package com.yinchrn.demo5.mapper;

import com.yinchrn.demo5.pojo.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
    UserAccount findByUsername(@Param("username") String username);
    void register (UserAccount userAccount);

    void deleteMyAccount(@Param("id") int id);
}
