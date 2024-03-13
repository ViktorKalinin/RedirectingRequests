package org.example.redirectingrequests.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.redirectingrequests.models.User;
import org.example.redirectingrequests.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User save(User newUser){
        if(newUser.getId() == null){
            newUser.setCreatAt(LocalDateTime.now());
            log.info("Register user: {} created ", newUser);
        }
        newUser.setUpdateAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByName(String name){
        return userRepository.findByUsername(name);
    }
}
