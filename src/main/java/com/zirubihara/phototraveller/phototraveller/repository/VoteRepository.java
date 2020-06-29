package com.zirubihara.phototraveller.phototraveller.repository;

import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.model.User;
import com.zirubihara.phototraveller.phototraveller.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
