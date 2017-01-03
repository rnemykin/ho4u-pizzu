package ru.ho4upizzu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ho4upizzu.model.Cafe;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CafeRepository extends PagingAndSortingRepository<Cafe, Integer> {

    Cafe findByViewLink(String viewLink);

    List<Cafe> findByHasDeliveryTrue(Sort sort);

    @Query("from Cafe c join fetch c.addresses a where ?1 between c.deliveryWorkStart and c.deliveryWorkEnd")
    List<Cafe> findDeliverNow(LocalTime now, Sort sort);
}
