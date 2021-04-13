package com.s3training.jwt.generate.services;

import com.s3training.jwt.generate.exception.DataNotFoundException;
import com.s3training.jwt.generate.configuration.jwt.CustomUserDetails;
import com.s3training.jwt.generate.model.Accounts;
import com.s3training.jwt.generate.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsService implements UserDetailsService {

    @Autowired
    AccountsRepository accountsRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Accounts> account = accountsRepository.findByUserName(s);
        if(account.isEmpty()){
            throw new DataNotFoundException("Cant find any User for UserName : "+s);
        }
        return new CustomUserDetails(account.get());
    }


    public UserDetails loadUserByUserID(Long userId) throws UsernameNotFoundException {
        Optional<Accounts> users = accountsRepository.findById(userId);
        if(users.isEmpty()){
            throw new DataNotFoundException("Cant find any User for UserID : "+userId);
        }
        return new CustomUserDetails(users.get());
    }
}