//package com.zirubihara.phototraveller.phototraveller.controller;
//
//import com.zirubihara.phototraveller.phototraveller.Assembler.CategoryModelAssembler;
//import com.zirubihara.phototraveller.phototraveller.exceptions.CategoryNotFoundException;
//import com.zirubihara.phototraveller.phototraveller.model.Category;
//import lombok.AllArgsConstructor;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@RestController
//@AllArgsConstructor
//public class CategoryController {
//    private final CategoryModelAssembler categoryModelAssembler;
//    private final CategoryRepository categoryRepository;
//
//    @GetMapping(path = "/categories", produces = "application/pt.app-v1.0+json")
//    public CollectionModel<EntityModel<Category>> all() {
//        List<EntityModel<Category>> categories = categoryRepository.findAll().stream()
//                .map(categoryModelAssembler::toModel)
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(categories,
//                linkTo(methodOn(com.zirubihara.phototraveller.phototraveller.controller.CategoryController.class).all()).withSelfRel());
//    }
//
//    @GetMapping(path = "/category/{id}", produces = "application/pt.app-v1.0+json")
//    public EntityModel<Category> one(@PathVariable Long id){
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new CategoryNotFoundException(id));
//
//        return categoryModelAssembler.toModel(category);
//    }
//
//    @PostMapping(path = "/categories", produces = "application/pt.app-v1.0+json")
//    ResponseEntity<EntityModel<Category>> newCategory(@RequestBody Category category) {
//
//        Category newCategory = categoryRepository.save(category);
//
//        return ResponseEntity
//                .created(linkTo(methodOn(com.zirubihara.phototraveller.phototraveller.controller.CategoryController.class).one(newCategory.getId())).toUri())
//                .body(categoryModelAssembler.toModel(newCategory));
//    }
//
//    @DeleteMapping(path = "/categories/{id}", produces = "application/pt.app-v1.0+json")
//    ResponseEntity<?> deleteCategoty(@PathVariable Long id) {
//        categoryRepository.deleteById(id);
//
//        return ResponseEntity.noContent().build();
//    }
//
//
//}
