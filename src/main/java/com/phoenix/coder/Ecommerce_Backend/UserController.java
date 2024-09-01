package com.phoenix.coder.Ecommerce_Backend;

import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.services.UserModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private UserModelService userModelService;

    @GetMapping("profile")
    ResponseEntity<UserModel> getUserProfile(@RequestHeader("Authorization") String jwt){
        UserModel user = userModelService.findUserByJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
