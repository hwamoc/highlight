package com.liner.example.highlight.domain.repo;

import com.liner.example.highlight.domain.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HighlightRepository extends JpaRepository<Highlight, Long> {

    Optional<Highlight> findById(Long id);

    @Query(value = "SELECT hl FROM Highlight hl WHERE hl.pageId= :pageId ORDER BY COALESCE(hl.modifiedDate, hl.createdDate) DESC")
    List<Highlight> selectJPQLByPageId(Long pageId);

}
