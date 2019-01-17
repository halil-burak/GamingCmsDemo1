package com.turkcell.playcell.cms.cmsdemo1.repo;

import com.turkcell.playcell.cms.cmsdemo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
