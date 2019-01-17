package com.turkcell.playcell.cms.cmsdemo1.repo;

import com.turkcell.playcell.cms.cmsdemo1.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
