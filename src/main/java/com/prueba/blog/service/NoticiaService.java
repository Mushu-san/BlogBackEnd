/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.service;

import com.prueba.blog.model.Noticia;
import com.prueba.blog.repository.NoticiaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

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
                .orElseThrow(() -> new RuntimeException("No se encontro la noticia"));
    }

    public List<Map<String, Object>> getRelatedNews(Long idCategorie) {
        return this.noticiaRepository.findNewsByCategorie(idCategorie)
                .stream().map(convert -> (Noticia) convert).map(k -> {
                    Map<String, Object> dto = new HashMap<>();
                    dto.put("id", k.getIdNoticia());
                    dto.put("titulo", k.getTitulo());
                    dto.put("descripcion", k.getDescripcion());
                    dto.put("imagen", k.getImagen());
                    return dto;
                }).collect(Collectors.toList());
    }

}
