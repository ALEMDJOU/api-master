package boms.yowyobFeedBack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import boms.yowyobFeedBack.model.Feedback;
import boms.yowyobFeedBack.repository.FeedbackRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class FeedbackController {
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@PostMapping("/feedback")
	public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
	    try {
	    	Feedback _feedback = feedbackRepository.insert(new Feedback(Uuids.timeBased(), feedback.getName(), feedback.getFirstname(), feedback.getEmail(), feedback.getJob(), feedback.getComments(), feedback.getImage(), 3));
	      return new ResponseEntity<>(_feedback, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> getFeedbacks() {
	    try {
	      List<Feedback> feedback = new ArrayList<Feedback>();

	      feedbackRepository.findAll().forEach(feedback::add);

	      return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/feedback/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") UUID id) {
	    Optional<Feedback> feedbackData = feedbackRepository.findById(id);

	    if (feedbackData.isPresent()) {
	      return new ResponseEntity<>(feedbackData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	  @DeleteMapping("/feedback/{id}")
	  public ResponseEntity<HttpStatus> deleteFeedbackById(@PathVariable("id") UUID id) {
	    try {
	    	feedbackRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	
	@PutMapping("/feedback/valid/{id}")
	  public ResponseEntity<Feedback> feedbackValid(@PathVariable("id") UUID id) {
	    Optional<Feedback> feedbackData = feedbackRepository.findById(id);

	    if (feedbackData.isPresent()) {
	    	Feedback _member = feedbackData.get();
	    	_member.setState(1);
	      return new ResponseEntity<>(feedbackRepository.save(_member), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
        
    @PutMapping("/feedback/notvalid/{id}")
	  public ResponseEntity<Feedback> feedbackNotValid(@PathVariable("id") UUID id) {
    	Optional<Feedback> feedbackData = feedbackRepository.findById(id);

	    if (feedbackData.isPresent()) {
	    	Feedback _member = feedbackData.get();
	    	_member.setState(0);
	      return new ResponseEntity<>(feedbackRepository.save(_member), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
    
    @PutMapping("/feedback/waiting/{id}")
	  public ResponseEntity<Feedback> feedbackwaiting(@PathVariable("id") UUID id) {
  	Optional<Feedback> feedbackData = feedbackRepository.findById(id);

	    if (feedbackData.isPresent()) {
	    	Feedback _member = feedbackData.get();
	    	_member.setState(2);
	      return new ResponseEntity<>(feedbackRepository.save(_member), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
    
    


}
