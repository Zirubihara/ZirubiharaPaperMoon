package com.zirubihara.phototraveller.phototraveller.Assembler;

import com.zirubihara.phototraveller.phototraveller.controller.CommentsController;
import com.zirubihara.phototraveller.phototraveller.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentsModelAssembler implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {

    @NotNull
    @Override
    public EntityModel<Comment> toModel(@NotNull Comment comment) {

        return EntityModel.of(comment,
                linkTo(methodOn(CommentsController.class).one(comment.getId())).withSelfRel(),
                linkTo(methodOn(CommentsController.class).all()).withRel("comments"));
    }
}
