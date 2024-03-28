package com.deltadc.examsystem.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PutMapping("/change-password")
//    public ResponseEntity<?> changePassword(@RequestBody String newPassword) {
//        return userService.changePassword(newPassword);
//    }

    //xoa user
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        return userService.deleteUserByUserId(userId);
    }

    //chinh sua username va password

    //tim sinh vien theo username
    @GetMapping("/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }

}
