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
public class Achievement {
	
	@PrimaryKey
	private UUID id;
	
	private String title;
	private String description;
	private String link;
	private String image;
	private boolean state;

}
