package com.codewithdorcas.crud.controller;

import com.codewithdorcas.crud.exception.UserNotFoundException;
import com.codewithdorcas.crud.model.User;
import com.codewithdorcas.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/") //to connect the backend to the front end
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User newUser(@RequestBody User user){
       return userRepository.save(user);

    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")

    User getUserId(@PathVariable Long id){
       return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

@PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));

}


    @DeleteMapping("/user/{id}")

    String deleteUserId(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user with id " + id + "deleted successfully";

    }

}
