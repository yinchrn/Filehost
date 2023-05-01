package com.yinchrn.demo5.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareLink {
    Integer id;
    Integer fid;
    Integer uid;
    String link;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Shanghai")
    Date create_date = new Date();


}
