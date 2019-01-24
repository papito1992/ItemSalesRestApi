package com.SalesAPI.SalesAPI.ItemData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<SalesItem, Long>, JpaSpecificationExecutor<SalesItem> {

    //CUSTOM QUERIES
    List<SalesItem> findByTitle(String first);

    List<SalesItem> findSalesItemsByPriceBetween(Integer start, Integer end);

    List<SalesItem> findAllByOrderByTitle();
}
