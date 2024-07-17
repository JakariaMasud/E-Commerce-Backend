package com.phoenix.coder.Ecommerce_Backend.services;

import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.repositories.UserModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserModelServiceImpl implements UserModelService{
    UserModelRepository userModelRepository;
    @Override
    public UserModel getUserByEmail(String email) {
        return userModelRepository.findByEmail(email);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userModelRepository.findAll();
    }

    @Override
    public UserModel getUserById(Long id) {
        var user= userModelRepository.findById(id);
        return user.get();
    }

    @Override
    public void deleteUserById(Long id) {
        userModelRepository.deleteById(id);
    }

    @Override
    public UserModel updateUser(Long id, UserModel user) {
        UserModel oldUser= getUserById(id);
        oldUser.setFirstName(user.getFirstName());
        return userModelRepository.save(oldUser);
    }

    @Override
    public UserModel createUser(UserModel user) {
        return userModelRepository.save(user);
    }
}
