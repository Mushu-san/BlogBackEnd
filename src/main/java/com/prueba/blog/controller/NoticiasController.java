/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.controller;

import com.prueba.blog.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Angelo
 */
@RestController
@RequestMapping("/api/noticias/")
public class NoticiasController {
    
    @Autowired
    private NoticiaService noticiaService;
    
    @GetMapping(path = "getNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNews(){
        return ResponseEntity.ok(this.noticiaService.getNews());
    }
    
}
