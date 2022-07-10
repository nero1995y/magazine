package com.shop.magazine.domain.user.repository;

import com.shop.magazine.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u where u.username in :usernames")
    List<User> findByUsernames(@Param("usernames") List<String> usernames);

    @Query("select u from User u order by u.username asc")
    Page<User> findAllOrderByUsernameAsc(Pageable pageable);


}
