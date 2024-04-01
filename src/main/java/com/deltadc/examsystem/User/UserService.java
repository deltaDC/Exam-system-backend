package com.deltadc.examsystem.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public ResponseEntity<?> changePassword(String newPassword) {
//
//    }

    public ResponseEntity<?> deleteUserByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("khong tim thay nguoi dung theo id");
        }

        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body("da xoa nguoi dung theo id");
    }

    public ResponseEntity<?> findUserByUserName(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userRepository.findByRoleContaining("USER");

        return ResponseEntity.ok(userList);
    }

    public ResponseEntity<?> getAllAdmins() {
        List<User> userList = userRepository.findByRoleContaining("ADMIN");

        return ResponseEntity.ok(userList);
    }

    public ResponseEntity<?> editUser(Long userId, User newUser) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
