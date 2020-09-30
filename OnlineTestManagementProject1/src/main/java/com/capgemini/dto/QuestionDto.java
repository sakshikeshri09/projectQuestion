package com.capgemini.dto;

public class QuestionDto {

	private String questionTitle;

	private int questionAnswer;

	private int questionMarks;
	
	private String questionOptions[]=new String[4];

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public int getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(int questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public int getQuestionMarks() {
		return questionMarks;
	}

	public void setQuestionMarks(int questionMarks) {
		this.questionMarks = questionMarks;
	}

	public String[] getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(String[] questionOptions) {
		this.questionOptions = questionOptions;
	}
	
	
	

}
