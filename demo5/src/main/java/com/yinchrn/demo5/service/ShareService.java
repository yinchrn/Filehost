package com.yinchrn.demo5.service;

import java.io.IOException;

public interface ShareService {
    String createShareKey(int fid);
    Boolean getSharedFile(String key) throws IOException;

}
