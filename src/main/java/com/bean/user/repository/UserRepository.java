package com.bean.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
