package com.example.remind.domain.user.repository;

import com.example.remind.domain.user.entity.User;
import com.example.remind.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId")
    List<Long> findFollowingUserIdsByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p WHERE p.author.id IN :followingUserIds")
    Page<Post> findPostsByFollowingUsers(@Param("followingUserIds") List<Long> followingUserIds, Pageable pageable);
}



