package com.yinchrn.demo5.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {
    private Integer id;
    private String type;
    private String name;
    private Long size;
    private String local_name;
    private String observed_size;
    private Integer uid;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date create_date;





    public MyFile(String type, String name, Long size, String local_name, String observed_size, Integer uid, Date create_date) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.local_name = local_name;
        this.uid = uid;
        this.observed_size = observed_size;
        this.create_date = create_date;
    }
}
