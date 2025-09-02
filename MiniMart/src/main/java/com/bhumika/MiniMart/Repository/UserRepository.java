package com.bhumika.MiniMart.Repository;

import com.bhumika.MiniMart.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // custom query example (optional)
//    User findByEmail(String email);
    Optional<User> findByEmail(String email); // IMPORTANT: Optional
}

