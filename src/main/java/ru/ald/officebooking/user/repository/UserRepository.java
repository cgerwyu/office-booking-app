package ru.ald.officebooking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ald.officebooking.user.model.User;

public class UserRepository implements JpaRepository<User, Long> {
}
