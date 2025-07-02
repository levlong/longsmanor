package com.thelong.longsmanor.controller;

import com.thelong.longsmanor.controller.assembler.PingAssembler;
import com.thelong.longsmanor.model.Ping;
import com.thelong.longsmanor.repository.PingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class KeepConnectionController {

    @Autowired
    private PingRepository repository;

    @Autowired
    private PingAssembler assembler;

    /**
     * Get all items
     * 
     * @return
     */
    @GetMapping("/monitors")
    public CollectionModel<EntityModel<Ping>> all() {
        List<EntityModel<Ping>> pings = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(pings,
                linkTo(methodOn(KeepConnectionController.class).all()).withSelfRel());
    }

    /**
     * Get 10 lastest items
     * 
     * @param timePinged
     * @return
     */
    @GetMapping("/monitors/latest")
    public CollectionModel<EntityModel<Ping>> get10() {
        List<EntityModel<Ping>> top10 = repository.findTop10ByOrderByTimePingedDesc().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(top10,
                linkTo(methodOn(KeepConnectionController.class).get10()).withSelfRel());
    }

    /**
     * Get n latest items
     * 
     * @param n
     * @return
     */
    @GetMapping("/monitors/{n}-latest")
    public CollectionModel<EntityModel<Ping>> getN(@PathVariable int n) {

        Pageable pageable = PageRequest.of(0, n, Sort.by("timePinged").descending());

        List<EntityModel<Ping>> top10 = repository.findAllByOrderByTimePingedDesc(pageable).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(top10,
                linkTo(methodOn(KeepConnectionController.class).getN(n)).withSelfRel());
    }

    /**
     * Get item by id
     * 
     * @param id
     * @return
     */
    @GetMapping("/monitor/{id}")
    public EntityModel<Ping> one(@PathVariable ObjectId id) {
        Ping ping = repository.findBy_id(id);

        return assembler.toModel(ping);
    }

    /**
     * Get item by date
     */
    @GetMapping("/monitors/time")
    public CollectionModel<EntityModel<Ping>> getByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {

        List<EntityModel<Ping>> pings = repository.findByTimePingedBetween(from, to).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(pings,
                linkTo(methodOn(KeepConnectionController.class).getByDate(from, to)).withSelfRel());
    }
}
