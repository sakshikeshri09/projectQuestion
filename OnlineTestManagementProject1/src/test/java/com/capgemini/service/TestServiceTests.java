package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import com.capgemini.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.QuestionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.exception.EntityNotFoundException;

import ch.qos.logback.core.rolling.helper.MonoTypedConverter;

@SpringBootTest

/**
 * 
 * test service layer test
 *
 */
public class TestServiceTests {
	@InjectMocks
	private TestService testService;

	@Mock
	private TestDao testDao;

	@Test
	/**
	 * view all test method test
	 */
	public void viewAllTest() {

		when(testDao.findAll()).thenReturn(Stream
				.of(new com.capgemini.entity.Test(1, "blood", null, 1, 2, 3, null, null),
						new com.capgemini.entity.Test(2, "bp", null, 2, 3, 4, null, null))
				.collect(Collectors.toList()));
		assertEquals(2, testService.viewAll().size());
	}

	@Test
	/**
	 * find test by id method test
	 */

	public void findByIdTest() {

		com.capgemini.entity.Test test = new com.capgemini.entity.Test(1, "blood", null, 1, 2, 3, null, null);
		Optional<com.capgemini.entity.Test> testOptional = Optional.of(test);
		when(testDao.findById(test.getTestId())).thenReturn(testOptional);
		assertEquals(testOptional, testService.findById(test.getTestId()));

	}

}
