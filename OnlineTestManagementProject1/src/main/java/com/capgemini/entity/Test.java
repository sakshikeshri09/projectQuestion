package com.capgemini.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "test")
/**
 * 
 * test pojo class
 *
 */
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer testId;
	private String testTitle;

	@OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ts_id", referencedColumnName = "testId")
	private List<Question> testQuestions =new ArrayList<Question>();
	private int testDuration;
	private int testTotalMarks;
	@Transient
	private int currentQuestion;
	private LocalTime startTime;
	private LocalTime endTime;

	/**
	 * 
	 * @return
	 */
	public Integer getTestId() {
		return testId;
	}

	/**
	 * 
	 * @param testId
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	/**
	 * 
	 * @return
	 */
	public String getTestTitle() {
		return testTitle;
	}

	/**
	 * 
	 * @param testTitle
	 */
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	/**
	 * 
	 * @return
	 */
	public List<Question> getTestQuestions() {
		return testQuestions;
	}

	/**
	 * 
	 * @param testQuestions
	 */
	public void setTestQuestions(List<Question> testQuestions) {
		this.testQuestions = testQuestions;
	}

	/**
	 * 
	 * @return
	 */

	public int getTestTotalMarks() {
		return testTotalMarks;
	}

	/**
	 * 
	 * @param testTotalMarks
	 */
	public void setTestTotalMarks(int testTotalMarks) {
		this.testTotalMarks = testTotalMarks;
	}

	/**
	 * 
	 * @return
	 */
	public int getCurrentQuestion() {
		return currentQuestion;
	}

	/**
	 * 
	 * @param currentQuestion
	 */
	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	/**
	 * 
	 * @param testId
	 * @param testTitle
	 * @param testQuestions
	 * @param testDuration
	 * @param testTotalMarks
	 * @param currentQuestion
	 * @param startTime
	 * @param endTime
	 */

	public Test(Integer testId, String testTitle, List<Question> testQuestions, int testDuration, int testTotalMarks,
			int currentQuestion, LocalTime startTime, LocalTime endTime) {
		super();
		this.testId = testId;
		this.testTitle = testTitle;
		this.testQuestions = testQuestions;
		this.testDuration = testDuration;
		this.testTotalMarks = testTotalMarks;
		this.currentQuestion = currentQuestion;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * 
	 * @return
	 */

	public int getTestDuration() {
		return testDuration;
	}

	/**
	 * 
	 * @param testDuration
	 */
	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}

	/**
	 * 
	 * @return
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @param endTime
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Test() {
	}

}
