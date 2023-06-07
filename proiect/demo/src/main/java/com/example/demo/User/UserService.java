package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email adress already used");
        }
        userRepository.save(user);

    }

    public void deleteUser(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if(!userExists){
            throw new IllegalStateException("user " + userId + " doesn't exists");
        }

        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("user " + userId + " doesn't exist"));

        if(name!=null && name.length()>0 && !Objects.equals(user.getFirstname(), name)){
            user.setName(name);
        }

        if(email!=null && email.length()>0 && !Objects.equals(user.getEmail(), email)){
            user.setEmail(email);
        }

    }
}
