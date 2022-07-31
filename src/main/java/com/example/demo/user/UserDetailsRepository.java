package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
  @Query(value = "SELECT * FROM user_details ud LEFT OUTER JOIN userq u ON ud.id = u.user_detail_id WHERE u.id = ?1", nativeQuery = true)
  UserDetails findByUserId(Long id);

  @Modifying
  @Query(value = "INSERT INTO user_details_quote(user_details_id, quote_id) VALUES(?1, ?2)", nativeQuery = true)
  void likeQuote(Long userDetailsId, Long quoteId);

}
