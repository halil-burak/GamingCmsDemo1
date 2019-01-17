package com.turkcell.playcell.cms.cmsdemo1.repo;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
