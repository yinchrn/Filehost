package com.yinchrn.demo5.utils;

public class GetSizeofFile {
    public static String getSize(long size){
        if (size > 1024){
            size /= 1024;
            if(size > 1024){
                size /= 1024;
                return size + "MB";
            }
            return size + "KB";
        }
        return size + "B";
    }
}
