package com.zirubihara.phototraveller.phototraveller.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.zirubihara.phototraveller.phototraveller.model.*;
import com.zirubihara.phototraveller.phototraveller.repository.CommentRepository;
import com.zirubihara.phototraveller.phototraveller.repository.VoteRepository;
import com.zirubihara.phototraveller.phototraveller.service.AuthService;
import com.zirubihara.phototraveller.phototraveller.dto.PostRequest;
import com.zirubihara.phototraveller.phototraveller.dto.PostResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "photos", source = "photos")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Photos photos, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "photosName", source = "photos.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, VoteType.UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, VoteType.DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

}
