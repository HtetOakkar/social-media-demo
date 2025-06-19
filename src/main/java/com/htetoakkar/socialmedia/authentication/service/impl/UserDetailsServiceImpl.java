package com.htetoakkar.socialmedia.authentication.service.impl;

import com.htetoakkar.socialmedia.authentication.security.user.UserPrincipal;
import com.htetoakkar.socialmedia.common.exceptions.UserNotFoundException;
import com.htetoakkar.socialmedia.user.model.entity.User;
import com.htetoakkar.socialmedia.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return UserPrincipal.build(user);
    }
}
