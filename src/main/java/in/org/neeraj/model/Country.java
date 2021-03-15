package in.org.neeraj.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY_MASTER")
public class Country {
	@Id
	@GeneratedValue
	@Column(name="COUNTRY_ID_COL")
	private Integer countryId;
	
	@Column(name="COUNTRY_NAME_COL")
	private String countryName;
	
	@Column(name="COUNTRY_CODE_COL")
	private String countryCode;
	
	@OneToMany
	@JoinColumn(name="STATE_ID_FK_COL")
	private List<State> state;

}
