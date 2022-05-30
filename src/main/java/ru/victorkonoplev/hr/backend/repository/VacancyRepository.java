package ru.victorkonoplev.hr.backend.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.victorkonoplev.hr.backend.entity.Vacancy;

public interface VacancyRepository extends PagingAndSortingRepository<Vacancy, Long> {
}
