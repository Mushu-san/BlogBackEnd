/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.service;

import com.prueba.blog.Constants.Role;
import com.prueba.blog.model.Cliente;
import com.prueba.blog.repository.ClienteRepository;
import com.prueba.blog.security.JwtService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Angelo
 */
@Service
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public Map<String, Object> signIn(Map<String, Object> dto) {
        Map<String, Object> response = new HashMap();

        this.clienteRepository.save(Cliente.builder()
                .nombre((String) dto.get("name"))
                .apellido((String) dto.get("lastname"))
                .correo((String) dto.get("email"))
                .password(passwordEncoder.encode((String) dto.get("password")))
                .estado('a')
                .fechaIngreso(new Date())
                .rol(Role.USER)
                .build());

        return response;
    }

    public Map<String, Object> signUp(Map<String, Object> dto) {
        Map<String, Object> response = new HashMap();

        
        
        
        return response;
    }

}
