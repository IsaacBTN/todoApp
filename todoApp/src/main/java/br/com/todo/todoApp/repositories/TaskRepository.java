package br.com.todo.todoApp.repositories;

import br.com.todo.todoApp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public Optional<Task> findByName (String name);

    public boolean existsByName(String name);
}
