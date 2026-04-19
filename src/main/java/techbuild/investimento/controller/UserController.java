package techbuild.investimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import techbuild.investimento.DTO.CreateUserDTO;
import techbuild.investimento.DTO.UpdateUserDTO;
import techbuild.investimento.entity.User;
import techbuild.investimento.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@RequestBody @Validated CreateUserDTO createUserDTO){
        var userId = service.createUser(createUserDTO);

        return ResponseEntity.created(URI.create("v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        var user = service.getUserById(userId);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        var users = service.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO updateUserDTO){
        boolean updated = service.uptadeUser(userId, updateUserDTO);
        if(updated){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) throws IllegalAccessException {
        service.deletar(userId);
        return ResponseEntity.noContent().build();
    }
}
