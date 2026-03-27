package com.SocialPlat.SocialPlat.Repository;

import com.SocialPlat.SocialPlat.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
