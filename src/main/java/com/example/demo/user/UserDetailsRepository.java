package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
  @Query(value = "SELECT * FROM user_details ud LEFT OUTER JOIN userq u ON ud.id = ?1", nativeQuery = true)
  UserDetails findByUserId(Long id);
}
