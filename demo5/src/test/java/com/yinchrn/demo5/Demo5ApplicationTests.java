package com.yinchrn.demo5;

import com.yinchrn.demo5.pojo.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Demo5ApplicationTests {

	@Test
	void contextLoads() throws IOException {
		String a = "123232";
		File directory = new File(".");//设定为当前文件夹

			System.out.println(directory.getCanonicalPath());//获取标准的路径
			System.out.println(directory.getAbsolutePath());


	}
	@Test
	void testList(){
		List<UserAccount> userAccounts = new ArrayList<>();
	}
}
