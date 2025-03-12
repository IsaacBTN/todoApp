package br.com.todo.todoApp.services;

import br.com.todo.todoApp.entities.User;
import br.com.todo.todoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser (User user)throws Exception {
        try {
            if(user.getName()== null || user.getName().isEmpty()){
                throw new IllegalArgumentException("O nome do usuario não pode ser vazio");
            }
            else if(userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new RuntimeException("Email já cadastrado");
            } return userRepository.save(user);
        }catch (Exception e){
            throw new Exception("Erro ao criar usuario" + e.getMessage());
        }
    }

    public void deleteUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
            return user.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public User updateUser (Long id, User updateUser){
        User user = userRepository.findById(id)
                .orElseThrow (() ->  new  RuntimeException( "Usuario não encontrado"));

            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPassword(updateUser.getPassword());

            return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
