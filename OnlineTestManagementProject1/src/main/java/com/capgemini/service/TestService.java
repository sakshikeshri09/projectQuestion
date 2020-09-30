package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.capgemini.dao.TestDao;
import com.capgemini.entity.Question;
import com.capgemini.entity.Test;
import com.capgemini.exception.EntityNotFoundException;

@Service

public class TestService implements TestServiceI {

	@Autowired
	TestDao dao;

	/*
	 * Add test with auto-generated test id
	 */

	@Override
	public Test addTest(@Validated Test test) {
		Test test1=new Test();
		test1=dao.save(test);
		return test1;
	}

	/*
	 * Delete test by test id test id passed cannot be negative test must be present
	 * to be deleted
	 */

	@Override
	public String deleteTest(int testId) {

		Optional<Test> findById = dao.findById(testId);
		if (findById.isPresent()) {
			dao.deleteById(testId);
			return "Test deleted";
		} else {
			throw new EntityNotFoundException("No test found with test id " + testId);
		}

	}

	/*
	 * Update test by test id test id passed cannot be negative test must be present
	 * to be updated
	 */

	@Override
	public String updateTest(int testId, @Validated Test test) {
		// TODO Auto-generated method stub
		Optional<Test> tst = dao.findById(testId);
		if (tst.isPresent()) {
			Test t = tst.get();
			t.setStartTime(test.getStartTime());
			t.setEndTime(test.getEndTime());
			t.setTestDuration(test.getTestDuration());
			t.setTestTitle(test.getTestTitle());
			t.setTestTotalMarks(test.getTestTotalMarks());
			dao.save(t);
			return "Test Updated";
		} else {
			throw new EntityNotFoundException("No test found with test id " + testId);
		}

	}

	/*
	 * View all tests
	 */

	@Override
	public List<Test> viewAll() {
		
		return dao.findAll();
	}

	/*
	 * Find test by id test id passed cannot be negative test must be present
	 */

	@Override
	public Test findById(int id) {

		Optional<Test> optional = dao.findById(id);
		if (optional.isPresent()) {
			 Test test=new Test();
			 test=optional.get();
			 return test;
		} else {
			throw new EntityNotFoundException("No test found with test id " + id);
		}

	}

	@Override
	public int calculateMarks(int testId) {
		int marks = 0;


		Optional<Test> tst = dao.findById(testId);

		if (tst.isPresent()) {
			Test test1 = tst.get();

			List<Question> questions = test1.getTestQuestions();
			for (Question q : questions) {
					if ( q.getQuestionAnswer() == q.getChosenAnswer()) {
						q.setMarksScored(q.getQuestionMarks());
						marks = marks + q.getQuestionMarks();
					}
				

			}
			return marks;
		} else
			return 0;
	}

}
