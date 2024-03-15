/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.controller;

import com.prueba.blog.service.ClienteService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Angelo
 */
@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @PostMapping(path = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody Map<String, Object> dto){
        return ResponseEntity.ok(this.clienteService.signIn(dto));
    }
    
    @PostMapping(path = "authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody Map<String, Object> dto){
        return ResponseEntity.ok(this.clienteService.signUp(dto));
    }
    
     @GetMapping(path = "test", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Si jalo");
    }
    
}
