package com.nodam.nodam_public.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Long> {

    // User findByUserId(Long id);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.userInfo.noSmokingDate = :noSmokingDate WHERE u.id = :id")
    public void noSmoking(@Param("noSmokingDate") Date noSmokingDate, @Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    public void updatePassword(@Param("password") String password, @Param("email") String email);
}
