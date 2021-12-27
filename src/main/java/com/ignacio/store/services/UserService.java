package com.ignacio.store.services;

import com.ignacio.store.models.UserModel;
import com.ignacio.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserModel save(UserModel model) {
        model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));

        return userRepository.save(model);
    }
}
