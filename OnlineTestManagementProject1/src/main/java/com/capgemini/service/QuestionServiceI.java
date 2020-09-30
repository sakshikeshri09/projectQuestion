package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.entity.Question;

public interface QuestionServiceI {

	Question addQuestion(int testId,Question question);

	boolean deleteQuestion(int testId,int questionId);
 
	List<Question> viewAll();
 
	Optional<Question> findById(int id);

	Question updateQuestion(int questionId, int testId, Question ques);


}
