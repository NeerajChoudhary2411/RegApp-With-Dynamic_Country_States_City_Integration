package in.org.neeraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.org.neeraj.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
	
	@Query("SELECT c.countryId,c.countryName FROM Country c")
	List<Object[]> getAllCountriesIdAndName();

	@Query("SELECT s.stateId, s.stateName FROM Country c INNER JOIN c.state as s WHERE c.countryId=:countryId")
	List<Object[]> getAllStatesIdAndNameByCountryId(Integer countryId);
}
