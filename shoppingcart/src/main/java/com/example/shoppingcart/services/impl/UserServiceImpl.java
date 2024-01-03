package com.example.shoppingcart.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.entity.UserEntity;
import com.example.shoppingcart.model.FireBaseUserData;
import com.example.shoppingcart.model.Mapper;
import com.example.shoppingcart.model.UserData;
import com.example.shoppingcart.repository.UserRepository;
import com.example.shoppingcart.services.UserService;

@Service
public class UserServiceImpl implements UserService {


  UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void addUser(UserEntity user) {
    userRepository.save(user);
  }

  @Override
  public List<UserData> getAllUsers() {
    List<UserData> output = new ArrayList<>();
    for (UserEntity convent : userRepository.findAll()) {
      output.add(Mapper.map(convent));
    }
    return output;
  }

  @Override
  public UserEntity getUserById(Long userId) {
    // return Mapper.map(userRepository.findAll().stream()//
    // .filter(e -> e.getUserId().equals(userId))//
    // .findFirst()//
    // .get());
    return userRepository.findById(userId)//
      //  .map(Mapper::map)//
        .orElse(null);
  }

  @Override
  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }

  // @Override
  // public UserData findUserByPhone(String phone) {
  // return Mapper.map(userRepository.findUserByPhone(phone).get());
  // }

  // @Override
  // public UserData findUserByUserName(String name) {
  // return Mapper.map(userRepository.findUserByUserName(name).get());
  // }

  @Override
  public UserData findUserByEmail(String email) {
    return Mapper.map(userRepository.findUserByEmail(email).get());
  }


  @Override
  public UserData updateUser(UserEntity user) {
    userRepository.save(user);
    return Mapper.map(user);
  }

  @Override
  public UserEntity getEntityByFireBaseUserData(
      FireBaseUserData fireBaseUserData) {
    return userRepository.findByFireBaseUid(fireBaseUserData.getFirebaseUid())//
        .orElseGet(() -> userRepository.save(new UserEntity(fireBaseUserData)));
    // Optional<UserEntity> userEntity =
    // userRepository.findByFireBaseUid(fireBaseUserData.getFirebaseUid());

    // if (!userEntity.isPresent()) {
    // userRepository.save(new UserEntity(fireBaseUserData));

    // Supplier : 左邊parameter，右邊->無野行入面statement
  }
}
