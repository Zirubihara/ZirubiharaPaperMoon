package com.zirubihara.phototraveller.phototraveller.mapper;

import com.zirubihara.phototraveller.phototraveller.dto.CommentsDto;
import com.zirubihara.phototraveller.phototraveller.model.Comment;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);
}
