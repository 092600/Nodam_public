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


    @Modifying
    @Query("UPDATE User u SET u.profileImage = :profileImage WHERE u.id = :id")
    public void updateProfileImg(@Param("profileImage") String profileImage, @Param("id") Long id);
    // @Modifying
    // @Query(value = "UPDATE User u SET u.userInfo.certificationNumber = :certificationNumber, u.usreInfo.certificateDate = :certificateDate WHERE u.email = :email", nativeQuery = true)
    // public void certificateForPassword(@Param("certificationNumber") Integer password,
    //                             @Param("certificateDate") Date certificateDate,
    //                             @Param("email") String email);


}
