package com.project.seenit.services;

import com.project.seenit.config.JwtProvider;
import com.project.seenit.models.LoginRequest;
import com.project.seenit.models.Users;
import com.project.seenit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public Users addUser(Users user) {
        Users tempUser = new Users();
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(encodePassword(user.getPassword()));
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmail(user.getEmail());

        return repository.save(tempUser);
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    public Users findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }

    public Users updateUser(String username, Users user){
        Users ogUser = findUserByUsername(username);
        if(user.getFirstName() != null)
            ogUser.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            ogUser.setLastName(user.getLastName());
        if(user.getPassword() != null)
            ogUser.setPassword(encodePassword(user.getPassword()));
        if(user.getEmail() != null)
            ogUser.setEmail(user.getEmail());

        return repository.save(ogUser);
    }

    public Iterable<Users> findAll(){
        return repository.findAll();
    }

    public Boolean deleteByUsername(String username){
        Users user = findUserByUsername(username);
        if(user != null) {
            repository.delete(user);
            return true;
        }

        return false;
    }

    public String getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }
}


