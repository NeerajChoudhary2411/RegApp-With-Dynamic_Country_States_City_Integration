package in.org.neeraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.org.neeraj.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	
	
	@Query("SELECT c.cityId, c.cityName FROM State s INNER JOIN s.city AS c WHERE s.stateId=:stateId")
	List<Object[]> getAllCitiesIdAndNameByStateId(Integer stateId);


}
