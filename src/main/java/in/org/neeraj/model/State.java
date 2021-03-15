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
@Table(name="STATE_MASTER")
public class State {
	@Id
	@GeneratedValue
	@Column(name="STATE_ID_COL")
	private Integer stateId;
	
	@Column(name="STATE_NAME_COL")
	private String stateName;
	
	@OneToMany
	@JoinColumn(name="STATE_ID_FK_COL")
	private List<City> city;
	

}
