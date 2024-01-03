package com.example.shoppingcart.services;

import java.util.List;
import com.example.shoppingcart.entity.UserEntity;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.model.FireBaseUserData;
import com.example.shoppingcart.model.UserData;

public interface UserService {

  void addUser(UserEntity user);

  List<UserData> getAllUsers();

  UserEntity getEntityByFireBaseUserData(FireBaseUserData fireBaseUserData);

  UserEntity getUserById(Long userId) throws UserNotExistException;

  // UserData findUserByUserName(String userName);

  // UserData findUserByPhone(String phone);

  UserData findUserByEmail(String email);

  UserData updateUser(UserEntity user);

  void deleteUserById(Long userId);

}
