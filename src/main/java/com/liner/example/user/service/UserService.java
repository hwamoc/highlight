package com.liner.example.user.service;

import com.liner.example.exception.ErrorCode;
import com.liner.example.exception.NotFound;
import com.liner.example.theme.domain.Theme;
import com.liner.example.theme.domain.repo.ThemeRepository;
import com.liner.example.user.domain.User;
import com.liner.example.user.domain.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThemeRepository themeRepository;

    public User add() {
        User user = User.builder()
                .Email("tester4@gmail.com")
                .name("테스터4")
                .password("1234")
                .build();
        Theme theme = themeRepository.findById((long) 1).orElseThrow(() -> new NotFound("Not Found Theme", ErrorCode.NOT_FOUND_PAGE));
        user.setTheme(theme);
        System.out.println("NEW USER: " + user.toString());
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Transactional(readOnly = true)
    @Cacheable("user")
    public User getUser(Long id) {
        User user = userRepository.getById(id);
        return user;
    }

}
