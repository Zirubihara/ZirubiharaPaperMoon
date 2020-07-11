package com.zirubihara.phototraveller.phototraveller.controller;

import com.zirubihara.phototraveller.phototraveller.Assembler.PostModelAssembler;
import com.zirubihara.phototraveller.phototraveller.dto.PostRequest;
import com.zirubihara.phototraveller.phototraveller.dto.PostResponse;
import com.zirubihara.phototraveller.phototraveller.exceptions.PostNotFoundException;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.repository.PostRepository;
import com.zirubihara.phototraveller.phototraveller.service.PostService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.common.reflection.XMethod;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final PostModelAssembler postModelAssembler;

    @PostMapping(produces = "application/pt.app-v1.0+json")
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/pt.app-v1.0+json")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping(value = "/{id}", produces = "application/pt.app-v1.0+json")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping(value = "by-photos/{id}", produces = "application/pt.app-v1.0+json")
    public ResponseEntity<List<PostResponse>> getPostsByPhotos(Long id) {
        return status(HttpStatus.OK).body(postService.getPostsByPhotos(id));
    }

    @GetMapping(value = "by-user/{name}", produces = "application/pt.app-v1.0+json")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }

    @GetMapping(path = "/id", produces = "application/pt.app-v1.1+json")
    public EntityModel<Post> one(@PathVariable Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() ->  new PostNotFoundException(id));

        return postModelAssembler.toModel(post);
    }

    @GetMapping(produces = "application/pt.app-v1.1+json")
    public CollectionModel<EntityModel<Post>> all() {
        List<EntityModel<Post>> posts = postRepository.findAll().stream()
                .map(postModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(posts,
                linkTo(methodOn(PhotosController.class).all()).withSelfRel());
    }

    @PostMapping(produces =  "application/pt.app-v1.1+json" )
    ResponseEntity<EntityModel<Post>> newPost(@RequestBody Post post) {

        Post newPost = postRepository.save(post);

        return ResponseEntity
                .created(linkTo(methodOn(PostController.class).one(newPost.getPostId())).toUri())
                .body(postModelAssembler.toModel(newPost));
    }

    @DeleteMapping(path = "/{id}", produces = "application/pt.app-v1.1+json")
    ResponseEntity<?> deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
