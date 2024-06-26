package com.deltadc.examsystem.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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
    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> editUser(@PathVariable("userId") Long userId, @RequestBody String newUserName) {
        newUserName = newUserName.replace("\"", "");
        return userService.editUser(userId, newUserName);
    }

    //tim sinh vien theo username
    @GetMapping("/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }

    //lay tat ca cac user khong phai admin
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    //lay tat ca cac user la admin
    @GetMapping("/all-admins")
    public ResponseEntity<?> getAllAdmins() {
        return userService.getAllAdmins();
    }

    //lay tat ca cac user, tinh ca admin
    @GetMapping("/all-users-admins")
    public ResponseEntity<?> getALlUsersAdmins() {
        return userService.getALlUsersAdmins();
    }

    @DeleteMapping("/test-modifying/{username}")
    @Transactional
    public ResponseEntity<?> testModifying(@PathVariable("username") String username) {
        System.out.println(username);
        return userService.testModifying(username);
    }

    @GetMapping("/test-specification")
    public ResponseEntity<?> testSpecification() {
        return userService.testSpecification();
    }

    @GetMapping("/test-projection")
    public ResponseEntity<?> testProjection() {
        return userService.testProjection();
    }
}
