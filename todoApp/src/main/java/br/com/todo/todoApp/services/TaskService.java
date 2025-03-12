package br.com.todo.todoApp.services;

import br.com.todo.todoApp.entities.Task;
import br.com.todo.todoApp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task createTask (Task task){
        try{
        if(task.getName()==null || task.getName().isEmpty()){
            throw new IllegalArgumentException("Dados para criação de task invalidos!");
        }else if (taskRepository.findByName(task.getName()).isPresent()){
            throw new IllegalArgumentException("Task com esse nome já criada");
        }return taskRepository.save(task);
    }catch (Exception e){
            throw new RuntimeException("Erro ao criar task: " + e.getMessage());
        }
    }

    public void deleteTaskById(Long id){
        if(!taskRepository.existsById(id)){
            throw new IllegalArgumentException("Task com o id : " + id + "não foi encontrada");
        } taskRepository.deleteById(id);
    }

    public Task getTaskById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task com o id : " + id + "não foi encontrada"));
        return task;
    }

    public Task updateTaskById(Long id, Task updatedTask){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task não encontrada"));

        if (updatedTask.getName() != null && !taskRepository.existsByName(updatedTask.getName())){
            task.setName(updatedTask.getName());
        }
        if (updatedTask.getStatus() != null ) {
            task.setStatus(updatedTask.getStatus());
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }





}
