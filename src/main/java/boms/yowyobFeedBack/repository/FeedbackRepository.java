package boms.yowyobFeedBack.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import boms.yowyobFeedBack.model.Feedback;

public interface FeedbackRepository extends CassandraRepository<Feedback, UUID>{

}
