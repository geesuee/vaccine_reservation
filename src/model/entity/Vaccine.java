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
@NamedQuery(query="select v from Vaccine v where v.vaccineName=:name", name="Vaccine.findByVaccineName")
@Entity(name="Vaccine")
public class Vaccine {
	@Id
	@Column(name="name")
	private String vaccineName;
	
	@Column(name="target_age")
	private int targetAge;
	
	private int period;
	
	private String platform;
	
	private String temperature;
	
	private int storage;

	@Override
	public String toString() {
		return "[백신 = " + vaccineName + "], [접종 연령 = " + targetAge + "세 이상], [접종 간격 = " + period + "일], [플랫폼 = "
				+ platform + "], [보관 온도 = " + temperature + "], [보관 기간 = " + storage + "]";
	}
	
}
