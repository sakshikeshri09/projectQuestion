package com.capgemini.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.dto.QuestionDto;
import com.capgemini.entity.Question;
import com.capgemini.entity.Test;
import com.capgemini.entity.User;
import com.capgemini.exception.EntityAlreadyExists;
import com.capgemini.exception.EntityNotFoundException;
import com.capgemini.service.QuestionServiceI;
import com.capgemini.service.TestService;
import com.capgemini.service.UserService;

/**
 * @author sakshi keshri
 *
 */
@RestController
@CrossOrigin("*")
public class Controller {

	@Autowired
	QuestionServiceI quesService;

	@Autowired
	UserService userService;
	@Autowired
	TestService testService;
	

	//Logger logger = LoggerFactory.getLogger(Controller.class);

	
	@PostMapping("/add/question/{testId}")
	public ResponseEntity<Question> addQuestion(@PathVariable(value = "testId") int testId,
			 @RequestBody QuestionDto requestData) {
		Question question=convertDto(requestData);
		question=quesService.addQuestion(testId, question);
		return new ResponseEntity<Question>(question, HttpStatus.OK);

	}

	public Question convertDto(QuestionDto requestData) {
		Question question =new Question();
		question.setQuestionTitle(requestData.getQuestionTitle());
		question.setQuestionOptions(requestData.getQuestionOptions());
		question.setQuestionMarks(requestData.getQuestionMarks());
		question.setQuestionAnswer(requestData.getQuestionAnswer());
		return question;
	}

	@PutMapping("/update/question/{testId}/{qId}")
	public ResponseEntity<Question> updateQuestion(@PathVariable("testId")int testId, @PathVariable(value = "qId") int qId,
			 @RequestBody QuestionDto updateRequest) {
		Question question=convertDto(updateRequest);
		question=quesService.updateQuestion(qId,testId, question);
		return new ResponseEntity<Question>(question, HttpStatus.OK);

	}

	@DeleteMapping("/delete/question/{testId}/{id}")
	public ResponseEntity<Boolean> deleteQuestion(@PathVariable("testId")int testId,@PathVariable(value = "id") int qId) {
		quesService.deleteQuestion(testId,qId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);

	}

	@GetMapping("/find/question/{id}")
	public Question getQuestion(@PathVariable(value = "id") int qId) {
		Optional<Question> question = quesService.findById(qId);
		return question.get();
	}

	@GetMapping("/view/all/questions")
	public ResponseEntity<List<Question>> viewAllQuestion() {
		List<Question> questionLists=quesService.viewAll();
		return new ResponseEntity<List<Question>>(questionLists,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)     
    public ResponseEntity<String>handleTestNotFound(EntityNotFoundException ex) {
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.CONFLICT);
        return response;

  }
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		String s = userService.login(user.getEmail(), user.getUserPassword());
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(EntityAlreadyExists.class)
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Validated @RequestBody User usr) {
		try {
			userService.addUser(usr);
			return new ResponseEntity<String>("User Added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<?> removeUser(@PathVariable(value = "email") String email) {
		try {
			userService.deleteUser(email);
			return new ResponseEntity<String>("User deleted", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updateUser/{email}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "email") String email,
			@Validated @RequestBody User user) {
		try {
			userService.updateUser(email, user);
			return new ResponseEntity<String>("User updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/viewUser/{email}")
	public User viewUserByEmail(@PathVariable(value = "email") String email) {
		return userService.findUserByEmail(email);
	}

	@GetMapping("/viewUsers")
	public List<User> viewAllUser() {
		return userService.viewAll();
	}

	@PostMapping("/addTest")
	public ResponseEntity<?> addTest(@RequestBody Test test) {
		try {
			testService.addTest(test);
			return new ResponseEntity<String>("Test Added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/viewTests")
	public List<Test> viewAllTest() {
		return testService.viewAll();
	}

	@DeleteMapping("/deleteTest/{id}")
	public ResponseEntity<?> deleteTest(@PathVariable(value = "id") int testId) {
		try {
			testService.deleteTest(testId);
			return new ResponseEntity<String>("Test deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updateTest/{id}")
	public ResponseEntity<?> updateTest(@PathVariable(value = "id") int testId, @RequestBody Test test) {
		try {
			testService.updateTest(testId, test);
			return new ResponseEntity<String>("Question updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/viewTestById/{id}")
	public ResponseEntity<Test> findById(@PathVariable(value = "id") int testId) {
		Test test=new Test();
		test=testService.findById(testId);
		ResponseEntity<Test> response=new ResponseEntity<Test>(test,HttpStatus.OK);
		return response;
	}


	@PostMapping("/calculateMarks/{testId}")
	public int getResult(@PathVariable(value = "testId") int testId) {
		return testService.calculateMarks(testId);
	}
	@PostMapping("/assignTest/{email}/{id}")
	public ResponseEntity<?> assignTest(@PathVariable(value = "email") String email,
			@PathVariable(value = "id") int testId) {
		try {
			userService.assignTest(email, testId);
			return new ResponseEntity<String>("Test Assigned", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
