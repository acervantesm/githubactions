package com.apress.users.web;

import com.apress.users.model.User;
import com.apress.users.model.UserGravatar;
import com.apress.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAll(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserById(@PathVariable String email) throws Throwable {
        return ResponseEntity.of(this.userRepository.findById(email));
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseEntity<User> save(@Valid @RequestBody User user){
       this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(user.getEmail())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String email){
        this.userRepository.deleteById(email);
    }


    @GetMapping("/{email}/gravatar")
    public ResponseEntity<String> getUserGravatar(@PathVariable String email) throws Throwable {
        return ResponseEntity.ok(UserGravatar.getGravatarUrlFromEmail(email));
    }
}

// aw2s0meR!

