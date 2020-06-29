package com.zirubihara.phototraveller.phototraveller.repository;

import com.zirubihara.phototraveller.phototraveller.model.Photos;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPhotos(Photos photos);

    List<Post> findByUser(User user);
}
