package com.hburak.cms.cmsdemo1.repo;

import com.hburak.cms.cmsdemo1.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
