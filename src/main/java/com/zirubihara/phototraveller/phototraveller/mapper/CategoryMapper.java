//package com.zirubihara.phototraveller.phototraveller.mapper;
//
//import com.zirubihara.phototraveller.phototraveller.dto.CategoryDto;
//import com.zirubihara.phototraveller.phototraveller.model.Category;
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface CategoryMapper {
//
//    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(category.getPosts()))")
//    CategoryDto mapCategoryToDto(Category category);
//
//    default Integer mapPosts(List<Category> numberOfPosts) {return  numberOfPosts.size();}
//
//    @InheritInverseConfiguration
//    @Mapping(target = "posts", ignore = true)
//    Category mapDtoToCategory(CategoryDto categoryDto);
//}
