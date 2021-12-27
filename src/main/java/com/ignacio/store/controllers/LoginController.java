package com.ignacio.store.controllers;

import com.ignacio.store.models.UserModel;
import com.ignacio.store.services.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private JsonWebTokenService webTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> authenticate(@RequestBody UserModel model) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        var token = webTokenService.generateToken(model.getUsername());
        var entity = new JSONObject();

        entity.put("username", model.getUsername());
        entity.put("token", token);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody UserModel model) throws Exception {
        var user = new UserModel();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());

        userService.save(model);
        return authenticate(user);
    }
}
