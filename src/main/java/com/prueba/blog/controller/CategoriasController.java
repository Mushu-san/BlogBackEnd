/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.controller;

import com.prueba.blog.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Angelo
 */
@RestController
@RequestMapping("/api/categorias/")
public class CategoriasController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    
    @GetMapping(path = "getCats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(categoriaService.getAllCategories());
    }
    
    @GetMapping(path = "getNews/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewsByCategorie(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.getNewsByCategorie(id));
    }
    
}
