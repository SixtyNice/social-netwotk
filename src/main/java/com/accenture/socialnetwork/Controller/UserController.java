package com.accenture.socialnetwork.Controller;

import com.accenture.socialnetwork.DTO.UserDTO;
import com.accenture.socialnetwork.Entity.PostEntity;
import com.accenture.socialnetwork.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id}")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable(value = "id") int id) {
        UserDTO user = userService.getUserInfo(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/friends", produces = "application/json")
    public ResponseEntity<List<UserDTO>> getUserFriends(@PathVariable(value = "id") int id) {
        List<UserDTO> friends = userService.getUserFriends(id);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping(path = "/createPost", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Boolean> createUserPost(@RequestBody PostEntity post, @PathVariable(value = "id") int userId) {
        return userService.createPost(post, userId) ?
                new ResponseEntity<>(true,HttpStatus.OK) :
                new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

}
