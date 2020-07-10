package com.zirubihara.phototraveller.phototraveller.mapper;

import com.zirubihara.phototraveller.phototraveller.dto.PhotosDto;
import com.zirubihara.phototraveller.phototraveller.model.Post;
import com.zirubihara.phototraveller.phototraveller.model.Photos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotosMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(photos.getPosts()))")
    PhotosDto mapPhotosToDto(Photos photos);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Photos mapDtoToPhotos(PhotosDto photosDto);
}
