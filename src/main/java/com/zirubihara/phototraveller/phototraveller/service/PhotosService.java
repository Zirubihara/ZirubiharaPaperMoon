package com.zirubihara.phototraveller.phototraveller.service;

import com.zirubihara.phototraveller.phototraveller.exceptions.SpringPhotoTravellerException;
import com.zirubihara.phototraveller.phototraveller.mapper.PhotosMapper;
import com.zirubihara.phototraveller.phototraveller.model.Photos;
import com.zirubihara.phototraveller.phototraveller.repository.PhotosRepository;
import com.zirubihara.phototraveller.phototraveller.dto.PhotosDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PhotosService {

    private final PhotosRepository photosRepository;
    private final PhotosMapper photosMapper;

    @Transactional
    public PhotosDto save(PhotosDto photosDto) {
        Photos save = photosRepository.save(photosMapper.mapDtoToPhotos(photosDto));
        photosDto.setId(save.getId());
        return photosDto;
    }

    @Transactional(readOnly = true)
    public List<PhotosDto> getAll() {
        return photosRepository.findAll()
                .stream()
                .map(photosMapper::mapPhotosToDto)
                .collect(toList());
    }

    public PhotosDto getPhotos(Long id) {
        Photos photos = photosRepository.findById(id)
                .orElseThrow(() -> new SpringPhotoTravellerException("Nie odnaleziono Photosa o ID  - " + id));
        return photosMapper.mapPhotosToDto(photos);
    }
}
