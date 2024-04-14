package com.forescout.cysiv.keyvault.service;

import com.forescout.cysiv.keyvault.domain.UserEntity;
import com.forescout.cysiv.keyvault.repository.UserRepository;
import com.forescout.cysiv.keyvault.security.AuthenticatedUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("retrieving user {}...", username);
        UserEntity user = userRepository.findByUsername(username);

        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User NOT Found");
        }

        log.info("User {} Authenticated Successfully..!!!", username);
        return new AuthenticatedUserDetails(user);
    }
}
