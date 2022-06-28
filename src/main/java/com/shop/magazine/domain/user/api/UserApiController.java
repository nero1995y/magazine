package com.shop.magazine.domain.user.api;

import com.shop.magazine.domain.user.dto.UserResponseDto;
import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.dto.UserUpdateRequestDto;
import com.shop.magazine.domain.user.dto.UsersResponseDto;
import com.shop.magazine.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("api/v1/user")
    public ResponseEntity<Long> save(@RequestBody UserSaveRequestDto requestDto) {
        return ResponseEntity.ok(userService.register(requestDto));
    }

    @GetMapping("api/v1/users")
    public ResponseEntity<UsersResponseDto> findList() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("api/v1/user/{id}")
    public ResponseEntity<UserResponseDto> findByUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    //TODO nero  Long id 바꾸기
    @PatchMapping("api/v1/user/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody UserUpdateRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("api/v1/user/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        userService.remove(id);
        return ResponseEntity.ok().build();
    }
}
