package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedQuery(query="select u from Users u where u.userName=:name", name="Users.findByUserName")
@NamedQuery(query="select u from Users u where u.idNum=:id", name="Users.findByIdNum")
@Entity(name="Users")
public class Users {
	@Id
	@Column(name="id_num")
	private int idNum;
	
	@Column(name="name")
	private String userName;
	
	private int age;
	
	@Column(name="address")
	private String userAddress;
	
	private String date1;
	
	private String date2;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vaccine_name")
	private Vaccine vaccine;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hospital_name")
	private Hospital hospital;

	@Override
	public String toString() {
		return "[주민등록번호 = " + idNum + "], [이름 = " + userName + "], [나이 = " + age + "], [주소 = " + userAddress + "], [1차 접종날짜 = "
				+ date1 + "], [2차 접종날짜 = " + date2 + "], [접종 백신 = " + vaccine.getVaccineName() + "], [접종 병원 = " + hospital.getHospitalName() + "]";

	}
	
}
