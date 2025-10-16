package boms.yowyobFeedBack.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import boms.yowyobFeedBack.model.Member;

public interface MemberRepository extends CassandraRepository<Member, UUID>{

	@Query("SELECT * FROM member WHERE email = ?0 AND password = ?1 ALLOW FILTERING")
	Optional<Member> authentificate(String email, String password);
	
}
