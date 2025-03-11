package br.com.todo.todoApp.repositories;

import br.com.todo.todoApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
