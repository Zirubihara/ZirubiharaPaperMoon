package com.zirubihara.phototraveller.phototraveller.controller;

import com.zirubihara.phototraveller.phototraveller.Assembler.PhotosModelAssembler;
import com.zirubihara.phototraveller.phototraveller.dto.PhotosDto;
import com.zirubihara.phototraveller.phototraveller.exceptions.PhotostNotFoundException;
import com.zirubihara.phototraveller.phototraveller.model.Photos;
import com.zirubihara.phototraveller.phototraveller.repository.PhotosRepository;
import com.zirubihara.phototraveller.phototraveller.service.PhotosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/photos")
@AllArgsConstructor
@Slf4j
public class PhotosController {

    private final PhotosService photosService;
    private final PhotosRepository photosRepository;
    private final PhotosModelAssembler photosModelAssembler;

    @PostMapping(produces = "application/pt.app-v1.0+json")
    public ResponseEntity<PhotosDto> createPhotos(@RequestBody PhotosDto photosDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(photosService.save(photosDto));
    }

    @GetMapping(produces = "application/pt.app-v1.0+json")
    public ResponseEntity<List<PhotosDto>> getAllPhotos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photosService.getAll());
    }

    @GetMapping(path = "/{id}", produces = "application/pt.app-v1.0+json")
    public ResponseEntity<PhotosDto> getPhotos(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photosService.getPhotos(id));
    }

    @GetMapping(path = "/{id}", produces = "application/pt.app-v1.1+json")
    public EntityModel<Photos> one(@PathVariable Long id){
        Photos photos = photosRepository.findById(id)
                .orElseThrow(() -> new PhotostNotFoundException(id));

        return photosModelAssembler.toModel(photos);
    }

    @GetMapping(produces = "application/pt.app-v1.1+json")
    public CollectionModel<EntityModel<Photos>> all() {
        List<EntityModel<Photos>> photos = photosRepository.findAll().stream()
                .map(photosModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(photos,
                linkTo(methodOn(com.zirubihara.phototraveller.phototraveller.controller.PhotosController.class).all()).withSelfRel());
    }

//    @PostMapping(produces = "application/pt.app-v1.0+json")
//    ResponseEntity<EntityModel<Photos>> newPhotos(@RequestBody Photos photos) {
//
//        Photos newPhotos = photosRepository.save(photos);
//
//        return ResponseEntity
//                .created(linkTo(methodOn(com.zirubihara.phototraveller.phototraveller.controller.PhotosController.class).one(newPhotos.getId())).toUri())
//                .body(photosModelAssembler.toModel(newPhotos));
//    }
//
    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deletePhotos(@PathVariable Long id) {
        photosRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
