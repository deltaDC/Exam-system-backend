package com.deltadc.examsystem.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
