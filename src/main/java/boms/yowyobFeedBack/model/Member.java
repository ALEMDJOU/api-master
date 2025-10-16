package boms.yowyobFeedBack.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@PrimaryKey
	private UUID id;
	
	private String name;
	private String firstname;
	private String job;
	private String email;
	private String image;
	private String password;
	private int admin;

}
