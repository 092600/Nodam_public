package com.nodam.nodam_public.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // User findUserByEmail(String email);
    // User findByEmail(String email);
    // Optional<User> findByName(String name);
    // User findUserByEmailAndName(String userEmail, String userName);

    User findUserByEmail(String email);
    boolean existsByEmail(String email);
}
