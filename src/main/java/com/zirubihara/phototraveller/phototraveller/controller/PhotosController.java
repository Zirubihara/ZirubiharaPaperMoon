package com.zirubihara.phototraveller.phototraveller.controller;

import com.zirubihara.phototraveller.phototraveller.dto.PhotosDto;
import com.zirubihara.phototraveller.phototraveller.service.PhotosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@AllArgsConstructor
@Slf4j
public class PhotosController {

    private final PhotosService photosService;

    @PostMapping
    public ResponseEntity<PhotosDto> createPhotos(@RequestBody PhotosDto photosDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(photosService.save(photosDto));
    }

    @GetMapping
    public ResponseEntity<List<PhotosDto>> getAllPhotos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotosDto> getPhotos(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photosService.getPhotos(id));
    }
}
