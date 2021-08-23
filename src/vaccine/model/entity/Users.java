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
public class Users {
	
	@Id
	@Column(name="id_num")
	private Long idNum;
	
	private String name;
	
	private Integer age;
	
	private String address;
	
	@ManyToOne
	@JoinColumn(name="vaccine_name")
	private Vaccine vaccine_name;

	
	private String date1;
	
	private String date2;
	
	@ManyToOne
	@JoinColumn(name="hospital_name")
	private Hospital hospital_name;

}
