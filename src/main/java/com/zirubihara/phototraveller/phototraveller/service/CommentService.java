package com.zirubihara.phototraveller.phototraveller.service;

import com.zirubihara.phototraveller.phototraveller.dto.CommentsDto;
import com.zirubihara.phototraveller.phototraveller.exceptions.PostNotFoundException;
import com.zirubihara.phototraveller.phototraveller.exceptions.SpringPhotoTravellerException;
import com.zirubihara.phototraveller.phototraveller.mapper.CommentMapper;
import com.zirubihara.phototraveller.phototraveller.model.Comment;
import com.zirubihara.phototraveller.phototraveller.model.NotificationEmail;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.model.User;
import com.zirubihara.phototraveller.phototraveller.repository.CommentRepository;
import com.zirubihara.phototraveller.phototraveller.repository.PostRepository;
import com.zirubihara.phototraveller.phototraveller.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " skomentował twój post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    public CommentsDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new SpringPhotoTravellerException("Nie odnaleziono komentarza o ID - " + id));
        return commentMapper.mapToDto(comment);
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Skomentowano twój post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
