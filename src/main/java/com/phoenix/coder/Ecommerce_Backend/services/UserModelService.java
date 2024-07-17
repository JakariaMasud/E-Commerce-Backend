package com.phoenix.coder.Ecommerce_Backend.services;


import com.phoenix.coder.Ecommerce_Backend.models.UserModel;

import java.util.List;

public interface UserModelService {
    public UserModel getUserByEmail(String email);
    public List<UserModel> getAllUser();
    public UserModel getUserById(Long id);
    public void deleteUserById(Long id);
    public UserModel updateUser(Long id,UserModel user);
    public UserModel createUser(UserModel user);
}
