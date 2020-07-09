package com.zirubihara.phototraveller.phototraveller.service;

import com.zirubihara.phototraveller.phototraveller.exceptions.PostNotFoundException;
import com.zirubihara.phototraveller.phototraveller.exceptions.PhotostNotFoundException;
import com.zirubihara.phototraveller.phototraveller.mapper.PostMapper;
import com.zirubihara.phototraveller.phototraveller.model.Photos;
import com.zirubihara.phototraveller.phototraveller.model.User;
import com.zirubihara.phototraveller.phototraveller.repository.PostRepository;
import com.zirubihara.phototraveller.phototraveller.repository.PhotosRepository;
import com.zirubihara.phototraveller.phototraveller.repository.UserRepository;
import com.zirubihara.phototraveller.phototraveller.dto.PostRequest;
import com.zirubihara.phototraveller.phototraveller.dto.PostResponse;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PhotosRepository photosRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Photos photos = photosRepository.findByName(postRequest.getPhotosName())
                .orElseThrow(() -> new PhotostNotFoundException(postRequest.getPostId()));
        postRepository.save(postMapper.map(postRequest, photos, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByPhotos(Long photosId) {
        Photos photos = photosRepository.findById(photosId)
                .orElseThrow(() -> new PhotostNotFoundException(photosId));
        List<Post> posts = postRepository.findAllByPhotos(photos);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
