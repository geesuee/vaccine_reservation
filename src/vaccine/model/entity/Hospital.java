package vaccine.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedQuery(query="select h from Hospital h where h.hospitalName=:name", name="Hospital.findByHospitalName")
@Entity(name="Hospital")
public class Hospital {
	@Id
	@Column(name="name")
	private String hospitalName;
	
	private String location;
	
	private int pfizer;
	
	private int moderna;
	
	private int az;

	@Override
	public String toString() {
		return "[병원 = " + hospitalName + "], [지역 = " + location + "], [화이자 = " + pfizer + "개], [모더나 = "
				+ moderna + "개], [AZ = " + az + "개]";
	}
	
}
