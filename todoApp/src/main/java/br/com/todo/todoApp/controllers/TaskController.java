package br.com.todo.todoApp.controllers;

import br.com.todo.todoApp.entities.Task;
import br.com.todo.todoApp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Task task){
        try{
            Task task1 = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(task1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar task" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTask (@PathVariable Long id){
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task não encontrada, " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask (@PathVariable Long id,@RequestBody Task updatedTask){
        try {
            Task task = taskService.updateTaskById(id, updatedTask);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar task: " + e.getMessage());
        }}

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> taskList =  taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }
}
