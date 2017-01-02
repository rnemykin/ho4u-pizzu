package ru.ho4upizzu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ho4upizzu.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer>{
}
