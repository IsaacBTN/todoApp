package br.com.todo.todoApp.controllers;

import br.com.todo.todoApp.entities.User;
import br.com.todo.todoApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        try {
            User createUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar usuario" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserById (@PathVariable Long id, @RequestBody User userupdate){
        try {
            User user = userService.updateUser(id,userupdate);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuario");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers (){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}
