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

import boms.yowyobFeedBack.model.Member;
import boms.yowyobFeedBack.repository.MemberRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class MemberController {

	@Autowired
	MemberRepository memberRepository; 
	
	@PostMapping("/member")
	public ResponseEntity<Member> createMember(@RequestBody Member member) {
	    try {
	    	Member _member = memberRepository.insert(new Member(Uuids.timeBased(), member.getName(), member.getFirstname(), member.getJob(), member.getEmail(), member.getImage(), "7110eda4d09e062aa5e4a390b0a572ac0d2c0220", 0));
	      return new ResponseEntity<>(_member, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/members")
	public ResponseEntity<List<Member>> getMembers() {
	    try {
	      List<Member> members = new ArrayList<Member>();

	      memberRepository.findAll().forEach(members::add);

	      return new ResponseEntity<>(members, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/member/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable("id") UUID id) {
	    Optional<Member> memberData = memberRepository.findById(id);

	    if (memberData.isPresent()) {
	      return new ResponseEntity<>(memberData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	  @DeleteMapping("/member/{id}")
	  public ResponseEntity<HttpStatus> deleteMemberById(@PathVariable("id") UUID id) {
	    try {
	    	memberRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @PutMapping("/member/{id}")
	  public ResponseEntity<Member> updateMember(@PathVariable("id") UUID id, @RequestBody Member member) {
	    Optional<Member> memberData = memberRepository.findById(id);

	    if (memberData.isPresent()) {
	    	Member _member = memberData.get();
	    	_member.setName(member.getName());
	    	_member.setFirstname(member.getFirstname());
	    	_member.setJob(member.getJob());
	    	_member.setEmail(member.getEmail());
	    	_member.setPassword(member.getPassword());
	    	_member.setImage(member.getImage());
	    	
	      return new ResponseEntity<>(memberRepository.save(_member), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PutMapping("/member/admin/{id}")
	  public ResponseEntity<Member> memberIsAdmin(@PathVariable("id") UUID id) {
	    Optional<Member> memberData = memberRepository.findById(id);

	    if (memberData.isPresent()) {
	    	Member _member = memberData.get();
	    	_member.setAdmin(1);
	      return new ResponseEntity<>(memberRepository.save(_member), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
        
    @PutMapping("/member/notAdmin/{id}")
	  public ResponseEntity<Member> memberNotAdmin(@PathVariable("id") UUID id) {
    	Optional<Member> memberData = memberRepository.findById(id);

	    if (memberData.isPresent()) {
	    	Member _member = memberData.get();
	    	_member.setAdmin(0);
	      return new ResponseEntity<>(memberRepository.save(_member), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
    
    @GetMapping("/login/{email}/{password}")
	public ResponseEntity<Member> authentificate(@PathVariable("email") String email, @PathVariable("password") String password) {
			  Optional<Member> member = memberRepository.authentificate(email, password);

		      if (member.isPresent()) {
		      return new ResponseEntity<>(member.get(), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }

		}

}
