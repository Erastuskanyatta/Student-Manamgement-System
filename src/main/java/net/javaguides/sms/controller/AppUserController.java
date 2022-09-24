package net.javaguides.sms.controller;


import net.javaguides.sms.JWT.jwtRequest;
import net.javaguides.sms.JWT.jwtResponse;
import net.javaguides.sms.JWT.jwtUtility;
import net.javaguides.sms.entity.AppUser;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.CustomUserDetails;
import net.javaguides.sms.service.CustomUserDetailsServices;
import net.javaguides.sms.service.appUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/V1/user")
public class AppUserController {
    @Autowired
    private CustomUserDetailsServices customUserDetailsServices;
    @Autowired
    private jwtUtility jwtUtility;
    @Autowired
    private appUserService appUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private jwtRequest jwtRequest;
    // Register User
    @PostMapping("/registeringUser")
    public ResponseEntity<?> registerUser(@RequestBody AppUser appUser){
        Map<String, Object> registeringUser =  new HashMap<>();
        AppUser register = appUserService.registerUser(appUser);
        registeringUser.put("data", register);
        registeringUser.put("Status", HttpStatus.CREATED);
        return  new ResponseEntity<>(registeringUser, HttpStatus.OK);
    }

    // Authenticate User
    @PostMapping("/authenticatingUser")
    public jwtResponse authenticate(@RequestBody jwtRequest jwtRequest) throws Exception {
        try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             jwtRequest.getUsername(),
                             jwtRequest.getPassword()
                     )
             );
        }catch (BadCredentialsException e){
            throw  new Exception("That user does not exist", e);
        }
        final UserDetails userDetails
                = customUserDetailsServices.loadUserByUsername(
                        jwtRequest.getUsername());
        final String token = jwtUtility.generateToken((CustomUserDetails)userDetails );
        return  new jwtResponse(token);
    }
}
