package com.thelong.longsmanor.controller;

import com.thelong.longsmanor.model.Ping;
import com.thelong.longsmanor.repository.PingRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class KeepConnectionController {

    @Autowired
    private PingRepository repository;

    @GetMapping("/monitors")
    public CollectionModel<EntityModel<Ping>> all() {
        List<EntityModel<Ping>> pings = repository.findAll().stream().map(ping -> EntityModel.of(ping,
                linkTo(methodOn(KeepConnectionController.class).one(ping.get_id())).withSelfRel(),
                linkTo(methodOn(KeepConnectionController.class).all()).withRel("all-logs")))
                .collect(Collectors.toList());
        return CollectionModel.of(pings,
            linkTo(methodOn(KeepConnectionController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/monitor/{id}")
    public EntityModel<Ping> one(@PathVariable ObjectId id) {
        Ping ping = repository.findBy_id(id);

        return EntityModel.of(ping,
                linkTo(methodOn(KeepConnectionController.class).one(ping.get_id())).withSelfRel(),
                linkTo(methodOn(KeepConnectionController.class).all()).withRel("all-logs"));
    }

}
