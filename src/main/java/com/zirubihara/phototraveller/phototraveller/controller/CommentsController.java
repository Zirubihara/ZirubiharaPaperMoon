package com.zirubihara.phototraveller.phototraveller.controller;

import com.zirubihara.phototraveller.phototraveller.Assembler.CommentsModelAssembler;
import com.zirubihara.phototraveller.phototraveller.dto.CommentsDto;
import com.zirubihara.phototraveller.phototraveller.exceptions.CommentNotFoundException;
import com.zirubihara.phototraveller.phototraveller.model.Comment;
import com.zirubihara.phototraveller.phototraveller.repository.CommentRepository;
import com.zirubihara.phototraveller.phototraveller.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Slf4j

public class CommentsController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final CommentsModelAssembler commentsModelAssembler;
    //private final PhotosModelAssembler photosModelAssembler;


    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(userName));
    }

    @GetMapping("by-id/{commentId}")
    public EntityModel<Comment> one(@PathVariable Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        return commentsModelAssembler.toModel(comment);
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Comment>> all() {
        List<EntityModel<Comment>> comment = commentRepository.findAll().stream()
                .map(commentsModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(comment,
                linkTo(methodOn(CommentsController.class).all()).withSelfRel());
    }
}
