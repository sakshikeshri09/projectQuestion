package com.capgemini.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.dao.QuestionDao;
import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.entity.Test;
import com.capgemini.exception.EntityNotFoundException;

@Service
/**
 * 
 * service for Question
 *
 */
@Transactional
public class QuestionService implements QuestionServiceI {
	@Autowired
	QuestionDao dao;

	@Autowired
	TestDao testDao;
	

	@Override
	public Question addQuestion(int testId, Question question) {
		
		Optional<Test> optional = testDao.findById(testId);
		if (optional.isPresent()) {
			Test test = optional.get();
			test.setTestTotalMarks(test.getTestTotalMarks() + question.getQuestionMarks());
			List<Question> ques = test.getTestQuestions();
			ques.add(question);
			test.setTestQuestions(ques);
			dao.save(question);
			testDao.save(test);
			return question;
	} else {
			throw new EntityNotFoundException("Test Not Found and Question Not Added");
	}

	}

	@Override
	/**
	 * method to delete question
	 */
	public boolean deleteQuestion(int testId,int questionId) {
		Optional<Question> findById = dao.findById(questionId);
		Optional<Test> optionalTest=testDao.findById(testId);
		
		if (findById.isPresent()&& optionalTest.isPresent()) {
			Question ques=findById.get();
			Test test=optionalTest.get();
			test.setTestTotalMarks(test.getTestTotalMarks()-ques.getQuestionMarks());
			testDao.save(test);
			dao.deleteById(questionId);
			return true;
		} else {
			throw new EntityNotFoundException("Question Not Found for Id" + questionId);
		}
	}

	/**
	 * @param testId 
	 *
	 */
	@Override
	/**
	 * method to update question
	 * 
	 */
	public Question updateQuestion(int questionId, int testId, Question ques) {

		Optional<Question> optional = dao.findById(questionId);
		Optional<Test> optionalTest=testDao.findById(testId);
		if( optional.isPresent()&&optionalTest.isPresent()) {
			Test test=optionalTest.get();
			Question q = optional.get();
			int previousMarks=q.getQuestionMarks();
			q.setQuestionTitle(ques.getQuestionTitle());
			q.setQuestionOptions(ques.getQuestionOptions());
			q.setQuestionAnswer(ques.getQuestionAnswer());
			q.setQuestionMarks(ques.getQuestionMarks());
			q.setQuestionId(q.getQuestionId());
			test.setTestTotalMarks(test.getTestTotalMarks()-previousMarks+q.getQuestionMarks());
			dao.save(q);
			return q;
		} else {
			throw new EntityNotFoundException("Question Not Found for Id" + questionId);
		}

	}

	@Override
	/**
	 * to view all question
	 */

	public List<Question> viewAll() {

		List<Question> questions=dao.findAll();
//		System.out.println(dao.findAll());
//		return dao.findAll();
		return questions;
	}

	@Override  
	/**
	 * to find question by id
	 */
	public Optional<Question> findById(int id) {

		Optional<Question> findById = dao.findById(id);
		if (findById.isPresent()) {
			return findById;
		} else {
			throw new EntityNotFoundException("Question Not Found for Id" + id);
		}

	}

	

}
