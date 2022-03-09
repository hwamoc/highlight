package com.liner.example.page.domain.repo;

import com.liner.example.page.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findById(Long id);

    Optional<Page> findByPageUrl(String pageUrl);

    @Query(value = "SELECT pg FROM Page pg WHERE pg.userId= :userId ORDER BY COALESCE(pg.modifiedDate, pg.createdDate) DESC")
    List<Page> selectJPQLByUserId(Long userId);

}
