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

import boms.yowyobFeedBack.model.Achievement;
import boms.yowyobFeedBack.repository.AchievementRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AchievementController {
	
	@Autowired
	AchievementRepository achievementRepository;
	
	@PostMapping("/achievement")
	public ResponseEntity<Achievement> createMember(@RequestBody Achievement achievement) {
	    try {
	    	Achievement _achievement = achievementRepository.insert(new Achievement(Uuids.timeBased(), achievement.getTitle(), achievement.getDescription(), achievement.getLink(), achievement.getImage(), false));
	      return new ResponseEntity<>(_achievement, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/achievements")
	public ResponseEntity<List<Achievement>> getAchievements() {
	    try {
	      List<Achievement> achievements = new ArrayList<Achievement>();

	      achievementRepository.findAll().forEach(achievements::add);

	      return new ResponseEntity<>(achievements, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/achievement/{id}")
	public ResponseEntity<Achievement> getAchievementById(@PathVariable("id") UUID id) {
	    Optional<Achievement> achievementData = achievementRepository.findById(id);

	    if (achievementData.isPresent()) {
	      return new ResponseEntity<>(achievementData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	  @DeleteMapping("/achievement/{id}")
	  public ResponseEntity<HttpStatus> deleteAchievementById(@PathVariable("id") UUID id) {
	    try {
	    	achievementRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/achievement/{id}")
	  public ResponseEntity<Achievement> updateAchievement(@PathVariable("id") UUID id, @RequestBody Achievement achievement) {
	    Optional<Achievement> achievementData = achievementRepository.findById(id);

	    if (achievementData.isPresent()) {
	    	Achievement _achievement = achievementData.get();
	    	_achievement.setTitle(achievement.getTitle());
	    	_achievement.setDescription(achievement.getDescription());
	    	_achievement.setLink(achievement.getLink());
	    	_achievement.setImage(achievement.getImage());
	    	
	      return new ResponseEntity<>(achievementRepository.save(_achievement), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PutMapping("/achievement/valid/{id}")
	  public ResponseEntity<Achievement> achievementIsValid(@PathVariable("id") UUID id) {
	    Optional<Achievement> achievementData = achievementRepository.findById(id);

	    if (achievementData.isPresent()) {
	    	Achievement _achievement = achievementData.get();
	    	_achievement.setState(true);
	      return new ResponseEntity<>(achievementRepository.save(_achievement), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
        
	@PutMapping("/achievement/notvalid/{id}")
	  public ResponseEntity<Achievement> achievementIsNotValid(@PathVariable("id") UUID id) {
	    Optional<Achievement> achievementData = achievementRepository.findById(id);

	    if (achievementData.isPresent()) {
	    	Achievement _achievement = achievementData.get();
	    	_achievement.setState(false);
	      return new ResponseEntity<>(achievementRepository.save(_achievement), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}
