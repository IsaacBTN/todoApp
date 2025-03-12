package br.com.todo.todoApp.repositories;

import br.com.todo.todoApp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
