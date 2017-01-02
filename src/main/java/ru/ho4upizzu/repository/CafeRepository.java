package ru.ho4upizzu.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ho4upizzu.model.Cafe;

@Repository
public interface CafeRepository extends PagingAndSortingRepository<Cafe, Integer> {
}
