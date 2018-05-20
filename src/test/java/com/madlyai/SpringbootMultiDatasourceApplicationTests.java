package com.madlyai;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.madlyai.repository.test1.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.madlyai.domain.User;
import com.madlyai.repository.test1.UserTest1Repository;
import com.madlyai.repository.test2.UserTest2Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMultiDatasourceApplicationTests {

	@Resource
	private UserTest1Repository userTest1Repository;
	@Resource
	private UserTest2Repository userTest2Repository;	@Resource
	private UserDao userDao;

	@Test
	@Transactional(propagation=Propagation.REQUIRED)
	public void testSave() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);

		//userTest1Repository.save(new User("aa", "aa123456","aa@126.com", "aa",  formattedDate));
		System.err.println("=============================");
		//userTest2Repository.save(new User("cc", "cc123456","cc@126.com", "cc",  formattedDate));
//		List<User> list=userTest1Repository.findAll();
//		for (User user : list) {
//			System.out.println(user);
//		}
//		for (User user : userTest2Repository.findAll()) {
//			System.out.println("222"+user);
//		}
		for (User user : userDao.findAll()) {
			System.out.println("222"+user);
		}
		
	}

	@Test
	public void contextLoads() {
	}

}
