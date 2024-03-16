/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.service;

import com.prueba.blog.model.Categoria;
import com.prueba.blog.repository.CategoriaRepository;
import com.prueba.blog.repository.NoticiaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Angelo
 */
@Service
@Slf4j
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private NoticiaRepository noticiaRepository;
    
    public List<Categoria> getAllCategories(){
        return this.categoriaRepository.findAll();
    }
    
    public List<Map<String, Object>> getNewsByCategorie(Long idCategorie){
        return this.noticiaRepository.findNewsByCategorie(List.of(idCategorie))
                .stream().map(k -> {
                    Map<String, Object> dto = new HashMap<>();
                    dto.put("id", k.getId_Noticia());
                    dto.put("titulo", k.getTitulo());
                    dto.put("descripcion", k.getDescripcion());
                    dto.put("imagen", k.getImagen());
                    return dto;
                }).collect(Collectors.toList());
    }
}
