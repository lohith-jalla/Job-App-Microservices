package com.lohith.SpringsecDemo.services;


import com.lohith.SpringsecDemo.models.User;
import com.lohith.SpringsecDemo.models.UserPrincipal;
import com.lohith.SpringsecDemo.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findUserByUserName(username);
        if(user==null){
            System.out.println("Username not found "+username);
            throw new UsernameNotFoundException("Username not found "+username);
        }

        return new UserPrincipal(user);
    }
}
