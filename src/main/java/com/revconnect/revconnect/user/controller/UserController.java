package com.revconnect.revconnect.user.controller;

import com.revconnect.revconnect.user.dto.ProfileResponse;
import com.revconnect.revconnect.user.dto.UpdateProfileRequest;
import com.revconnect.revconnect.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getMyProfile(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<ProfileResponse> updateMyProfile(Authentication authentication, @RequestBody UpdateProfileRequest request) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.updateMyProfile(email, request));
    }

    @GetMapping("/creator-test")
    @PreAuthorize("hasRole('CREATOR')")
    public ResponseEntity<String> creatorTest() {
        return ResponseEntity.ok("Creator-only API accessed successfully!");
    }
}