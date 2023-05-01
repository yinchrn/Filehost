package com.yinchrn.demo5.utils;

import java.io.File;

public class DeleteAllFiles {
    public static void delete(File folder){
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    delete(file);
                }
            }
        }
        folder.delete();
    }

}
