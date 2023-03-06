package com.code.springboot.repository;

import com.code.springboot.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiDataRepository extends JpaRepository<WikimediaData, Long> {
}
