package com.lohith.SpringsecDemo.services;


import com.lohith.SpringsecDemo.models.User;
import com.lohith.SpringsecDemo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepo userRepo;
    private BCryptPasswordEncoder passEncoder=new BCryptPasswordEncoder(12);

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService  userDetailsService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;

    }


    public String register(User user){
        if(userRepo.getByUserName(user.getUserName())) {
            return null;
        }
        user.setPassword(passEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return jwtService.generateToken(user.getUserName());
    }


    // Previously we are sending the AUTH in Postman, but now there is no need of that
    // AUTH, as the userName and the password is enough to validate the login using
    // Authentication Manager.
    public String verify(User user){
        Authentication authentication = authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())
                        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());
        }
        return "Fail";
    }

    public boolean validateToken(String jwt){

        String userName=jwtService.extractUserName(jwt);
        UserDetails user=myUserDetailsService.loadUserByUsername(userName);

        return jwtService.validateToken(jwt,user);
    }
}