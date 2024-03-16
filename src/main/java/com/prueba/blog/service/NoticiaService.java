/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.service;

import com.prueba.blog.model.Noticia;
import com.prueba.blog.repository.NoticiaCategoriaRepository;
import com.prueba.blog.repository.NoticiaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author Angelo
 */
@Service
@Slf4j
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private NoticiaCategoriaRepository noticiaCategoriaRepository;
    
    public List<Map<String, Object>> getNews() {
        return this.noticiaRepository.findAllByEstado('a')
                .stream().map(k -> {
                    Map<String, Object> dto = new HashMap<>();
                    dto.put("id", k.getIdNoticia());
                    dto.put("titulo", k.getTitulo());
                    dto.put("descripcion", k.getDescripcion());
                    dto.put("imagen", k.getImagen());
                    return dto;
                }).collect(Collectors.toList());
    }

    public Noticia getNew(Long id) {
        return this.noticiaRepository.findByIdNoticiaAndEstado(id, 'a')
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND ,"No se encontro la noticia"));
    }

    public List<Map<String, Object>> getRelatedNews(Long idNoticia) {
        
        List<Long> categories = this.noticiaCategoriaRepository.findCategoriesByIdNoticia(idNoticia);
        
        return this.noticiaRepository.findNewsByCategorie(categories)
                .stream().filter(f -> !Objects.equals(f.getId_Noticia(), idNoticia)).map(k -> {
                    Map<String, Object> dto = new HashMap<>();
                    dto.put("id", k.getId_Noticia());
                    dto.put("titulo", k.getTitulo());
                    dto.put("descripcion", k.getDescripcion());
                    dto.put("imagen", k.getImagen());
                    return dto;
                }).collect(Collectors.toList());
    }

}
