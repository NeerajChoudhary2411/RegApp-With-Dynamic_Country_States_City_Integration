package in.org.neeraj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CITY_MASTER")
public class City {
	@Id
	@GeneratedValue
	@Column(name="CITY_ID_COL")
	private Integer cityId;
	
	@Column(name="CITY_NAME_COL")
	private String cityName;

}
