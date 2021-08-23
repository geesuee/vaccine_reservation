package vaccine.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vaccine {
	
	@Id
	private Long id;
	
	private String name;
	
	@Column(name="target_age")
	private Integer targetAge;
	
	private Integer period;
	
	private String platform;
	
	private String temperature;
	
	private String storage;
	
}
