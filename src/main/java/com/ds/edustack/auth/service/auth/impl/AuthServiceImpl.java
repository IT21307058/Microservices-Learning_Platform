package com.ds.edustack.auth.service.auth.impl;

import com.ds.edustack.auth.service.auth.AuthService;
import com.ds.edustack.auth.dto.SignupRequest;
import com.ds.edustack.auth.dto.UserDto;
import com.ds.edustack.auth.entity.User;
import com.ds.edustack.auth.enums.UserRole;
import com.ds.edustack.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    //    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(SignupRequest signupRequest){
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.LEARNER);
        User createUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createUser.getId());

        return userDto;
    }

    public Boolean hasUserWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }

    //get automatically called
    @PostConstruct
    public void createInstructorAccount() {
        User adminAccount = userRepository.findByRole(UserRole.INSTRUCTOR);

        if(null == adminAccount) {
            User user = new User();

            user.setEmail("instructor@test.com");
            user.setName("instructor");
            user.setRole(UserRole.INSTRUCTOR);
            user.setPassword(new BCryptPasswordEncoder().encode("instructor"));
            userRepository.save(user);

        }
    }

    //get automatically called
    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);

        if(null == adminAccount) {
            User user = new User();

            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);

        }
    }
}
