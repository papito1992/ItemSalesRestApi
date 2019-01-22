package com.SalesAPI.SalesAPI.ItemData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<SalesItem, Long> {
}
