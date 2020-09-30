package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.TestDao;
import com.capgemini.dao.UserDao;
import com.capgemini.entity.User;

@SpringBootTest

/**
 * 
 * user service layer tests
 *
 */
public class UserServiceTest {
//	@Autowired
//	private UserService userService;
//	@MockBean
//	private UserDao userDao;
//	@MockBean
//	private TestDao testDao;
//
//	@Test
//	/**
//	 * view all users test
//	 */
//	public void viewAllUsersTest() {
//
//		when(userDao.findAll())
//				.thenReturn(Stream
//						.of(new User("Sahstra", 1, "True", "sahstra@gmail.com", "abcd@1234"),
//								new User("Mihir", 1, "True", "mihir @gmail.com", "abcd@1234"))
//						.collect(Collectors.toList()));
//		
//		assertEquals(0, userService.viewAll().size());
//
//	}
//
//	@Test
//	/**
//	 * find user by id method test
//	 */
//	public void findByIdTest() {
//		long id = 1;
//		User user = new User("Sahstra", 1, "True", "sahstra@gmail.com", "abcd@1234");
//		Optional<User> userOptional = Optional.of(user);
//		when(userDao.findById(id)).thenReturn(userOptional);
//		assertEquals(userOptional, userService.findById(id));
//
//	}
//
//	@Test
//	/**
//	 * find user by email test
//	 */
//	public void findUserByEmailTest() {
//		String email = "sahstra@gmail.com";
//		User user = new User("Sahstra", 1, "True", "sahstra@gmail.com", "abcd@1234");
//		user.setEmail(email);
//		long id = userDao.getIdByEmail(email);
//
//		Optional<User> userOptional = Optional.of(user);
//		when(userDao.findById(id)).thenReturn(userOptional);
//		assertEquals(userOptional, userService.findById(id));
//
//	}
}
