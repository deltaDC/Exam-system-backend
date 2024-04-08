package com.deltadc.examsystem.User;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        List<UserDTO> userDTOS = userList.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(user.getUserId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setRole(user.getRole());

                    return userDTO;
                })
                .toList();

        return ResponseEntity.ok(userDTOS);
    }

    public ResponseEntity<?> getAllAdmins() {
        List<User> userList = userRepository.findByRoleContaining("ADMIN");
        List<UserDTO> userDTOS = userList.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(user.getUserId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setRole(user.getRole());

                    return userDTO;
                })
                .toList();

        return ResponseEntity.ok(userDTOS);
    }

    public ResponseEntity<?> editUser(Long userId, String newUserName) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setUsername(newUserName);

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> getALlUsersAdmins() {
        Pageable paging = PageRequest.of(0, 3, Sort.by("username"));

        Page<User> userList = userRepository.findAll(paging);

        return ResponseEntity.ok(userList);
    }
}
