/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.service;

import com.prueba.blog.model.Cliente;
import com.prueba.blog.repository.ClienteRepository;
import com.prueba.blog.security.JwtService;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> signIn(Map<String, Object> dto) {
        Map<String, Object> response = new HashMap();

        if(!signInValidator(dto)){
            response.put("message", "Bad Request");
            return response;
        }
        
        this.clienteRepository.save(Cliente.builder()
                .nombre((String) dto.get("name"))
                .apellido((String) dto.get("lastname"))
                .correo((String) dto.get("email"))
                .password(passwordEncoder.encode((String) dto.get("password")))
                .estado('a')
                .fechaIngreso(new Date())
                .build());

        response.put("message", "Se ha registrado Exitosamente");

        return response;
    }

    public Map<String, Object> signUp(Map<String, Object> dto) {
        Map<String, Object> response = new HashMap();
        String username = (String) dto.get("username");
        String password = (String) dto.get("password");
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        Cliente user = clienteRepository.findByCorreo(username).orElseThrow(() -> new UsernameNotFoundException("No se encontro al usuario, por favor verifique"));
        var token = this.jwtService.generateToken(user.getUsername());
        response.put("token", token);
        response.put("message", "Ha sido autenticado exitosamente");
        return response;
    }


    public boolean signInValidator(Map<String, Object> dto) {
        var email = (String) dto.get("email");
        var password = (String) dto.get("password");
        var name = (String) dto.get("name");
        var lastname = (String) dto.get("lastname");
        var regex = "/^[a-z ,.'-]+$/i";
        var emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        
        if(email == null){
            throw new RuntimeException("No se encuentra ningun email ingresado, por favor valide");
        }

        boolean alreadyUse = this.clienteRepository.existsByCorreo(email);

        if (!email.matches(emailRegex)) {
            throw new RuntimeException("Los caracteres ingresados en el email son invalidos, por favor valide");
        }

        if (alreadyUse) {
            throw new RuntimeException("El correo que esta tratando de utilizar ya se encuentra en uso");
        }

        if (password.length() < 8 || password.length() > 16) {
            throw new RuntimeException("La cantidad de caracteres ingresada en la contraseña es invalida, por favor valide");
        }

        if (!name.matches(regex) || !lastname.matches(regex)) {
            throw new RuntimeException("Los caracteres ingresados son invalidos, por favor valide");
        }

        return true;
    }

}
