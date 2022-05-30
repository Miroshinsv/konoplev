package ru.victorkonoplev.hr.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.victorkonoplev.hr.backend.entity.Vacancy;
import ru.victorkonoplev.hr.backend.repository.VacancyRepository;

import java.util.Optional;

@RestController
@RequestMapping(value = "/vacancy")
public class VacancyController {
    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @PostMapping(value = "/create")
    ResponseEntity<Vacancy> postVacancy(@RequestBody Vacancy entity) {
        return ResponseEntity.ok().body(vacancyRepository.save(entity));
    }

    @GetMapping(value = "/get/all")
    ResponseEntity<Iterable<Vacancy>> getAllVacancy() {
        return ResponseEntity.ok().body(vacancyRepository.findAll());
    }

    @GetMapping(value = "/get/{id}")
    ResponseEntity<Optional<Vacancy>> getVacancyById(@PathVariable Long id) {
        return ResponseEntity.ok().body(vacancyRepository.findById(id));
    }
}
