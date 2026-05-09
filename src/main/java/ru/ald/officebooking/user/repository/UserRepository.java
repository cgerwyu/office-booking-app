package ru.ald.officebooking.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ald.officebooking.user.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

}
