package com.liner.example.user.service;

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

    @Transactional(readOnly = true)
    @Cacheable("user")
    public User getUser(Long id) {
        User user = userRepository.getById(id);
        return user;
    }

    public void changeTheme(Long id, Long themeId) {
        User user = this.getUser(id);
        Theme theme = themeRepository.getById(themeId);
        user.setTheme(theme);
    }
}
