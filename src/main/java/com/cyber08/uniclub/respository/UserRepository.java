package com.cyber08.uniclub.respository;

import com.cyber08.uniclub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // SELECT : find
    // WHERE : by

    // Kiểu dữ liệu Optional: xử lí, tránh thiếu sót khi xử lí null
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
