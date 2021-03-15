package in.org.neeraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.org.neeraj.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	

}
