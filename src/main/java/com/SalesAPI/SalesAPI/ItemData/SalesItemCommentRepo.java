package com.SalesAPI.SalesAPI.ItemData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemCommentRepo extends JpaRepository<SalesItemComment, Long> {
}
