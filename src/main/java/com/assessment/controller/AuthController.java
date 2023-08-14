package com.assessment.controller;


import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.entity.Role;
import com.assessment.entity.User;
import com.assessment.payload.LoginDto;
import com.assessment.payload.SignUpDto;
import com.assessment.payload.SigninDto;
import com.assessment.repo.RoleRepository;
import com.assessment.repo.UserRepository;
import com.assessment.service.UserService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000") // Replace with your React.js application's URL
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService ctservice;
    
    @GetMapping("/showuser")
  	 public List<User> showuser()
  	 {
  		 return ctservice.ShowUser();
  	 }

//        @GetMapping("/login")
//        public String login() {
//            // Your login logic
//            return "Login endpoint";
//
//        }

 
    @GetMapping("/logout-success")
    public ResponseEntity<String> logoutSuccess() {
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody SigninDto signinDto){
        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword())
	        );

        

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

//        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
//        user.setRoles(Collections.singleton(roles));
        
        // Set user roles based on the signUpDto.getRoles() list
        Set<Role> roles = new HashSet<>();
        for (String roleName : signUpDto.getRoles()) {
            java.util.Optional<Role> roleOptional = roleRepository.findByName(roleName);
            if (roleOptional.isPresent()) {
                roles.add(roleOptional.get());
            } else {
                // Handle case when the role does not exist
                return new ResponseEntity<>("Invalid role: " + roleName, HttpStatus.BAD_REQUEST);
            }
        }
        user.setRoles(roles);

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
	
}
