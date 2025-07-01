package com.thelong.longsmanor.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.thelong.longsmanor.controller.KeepConnectionController;
import com.thelong.longsmanor.model.Ping;

@Component
public class PingAssembler implements RepresentationModelAssembler<Ping, EntityModel<Ping>> {

    @Override
    public EntityModel<Ping> toModel(Ping entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(KeepConnectionController.class).one(entity.get_id())).withSelfRel(),
                linkTo(methodOn(KeepConnectionController.class).all()).withRel("all-logs"));
    }

}
