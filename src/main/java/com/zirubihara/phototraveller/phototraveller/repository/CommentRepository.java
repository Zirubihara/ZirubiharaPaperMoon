package com.zirubihara.phototraveller.phototraveller.repository;

import com.zirubihara.phototraveller.phototraveller.model.Comment;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
