package ru.victorkonoplev.hr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.victorkonoplev.hr.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
