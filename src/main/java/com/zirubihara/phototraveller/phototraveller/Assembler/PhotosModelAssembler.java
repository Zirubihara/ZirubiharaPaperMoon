package com.zirubihara.phototraveller.phototraveller.Assembler;

import com.zirubihara.phototraveller.phototraveller.controller.PhotosController;
import com.zirubihara.phototraveller.phototraveller.model.Photos;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotosModelAssembler implements RepresentationModelAssembler<Photos, EntityModel<Photos>> {
    @Override
    public EntityModel<Photos> toModel(Photos photos) {
        EntityModel<Photos> photosModel = EntityModel.of(photos,
                linkTo(methodOn(PhotosController.class).one(photos.getId())).withSelfRel(),
                linkTo(methodOn(PhotosController.class).all()).withRel("photos"));

        return photosModel;
    }
}
