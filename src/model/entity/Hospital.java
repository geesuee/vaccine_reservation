package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

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
		return "[���� = " + hospitalName + "], [���� = " + location + "], [ȭ���� = " + pfizer + "��], [����� = "
				+ moderna + "��], [AZ = " + az + "��]";
	}

	
}
