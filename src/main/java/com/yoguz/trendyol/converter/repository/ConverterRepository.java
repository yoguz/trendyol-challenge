package com.yoguz.trendyol.converter.repository;

import com.yoguz.trendyol.converter.entity.ConvertedLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConverterRepository extends JpaRepository<ConvertedLink, Long> {

}
