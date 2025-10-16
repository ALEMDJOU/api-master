package boms.yowyobFeedBack.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import boms.yowyobFeedBack.model.Achievement;

public interface AchievementRepository extends CassandraRepository<Achievement, UUID>{

}
