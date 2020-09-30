package com.capgemini.service;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.QuestionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.service.QuestionService;

@SpringBootTest

/**
 * 
 * question service class test
 *
 */
public class QuestionServiceTests {
	@Autowired
	private QuestionService questionService;
	
	@MockBean
	private QuestionDao questionDao;
	
	@Test

	/**
	 * view all question method test
	 */
	public void viewAllQuestionTest() {

		when(questionDao.findAll()).thenReturn(
				Stream.of(new Question(1, "who", 2, 3, 3, 4, null), new Question(2, "who are you", 2, 3, 3, 3, null))
						.collect(Collectors.toList()));
		assertEquals(2, questionService.viewAll().size());
	}

	@Test
	/**
	 * find question by id test
	 */
	public void findByIdTest() {
		int id = 1;
		Question que = new Question(1, "who", 2, 3, 3, 3, null);
		Optional<Question> questionOptional = Optional.of(que);
		when(questionDao.findById(id)).thenReturn(questionOptional);
		assertEquals(questionOptional, questionService.findById(id));

	}

}
