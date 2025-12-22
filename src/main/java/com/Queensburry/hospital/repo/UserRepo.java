package com.Queensburry.hospital.repo;

import com.Queensburry.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,String> {
    List<User> findAllByStatus(String active);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<User> searchByName(@Param("name") String userName);

    User findByEmail(String email);

    List<User> findAllByRole(String role);

    @Query(value = "SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1", nativeQuery = true)
    String getLastUserId();
}
