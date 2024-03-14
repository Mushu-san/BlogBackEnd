/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.blog.repository;

import com.prueba.blog.model.Noticia;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Angelo
 */
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    List<Noticia> findAllByEstado(char estado);

    Optional<Noticia> findByIdNoticiaAndEstado(Long id, char estado);

    @Query(value = "SELECT n.* FROM \n"
            + "noticias n\n"
            + "inner join \n"
            + "noticias_categorias ng on ng.id_noticia = n.id_noticia\n"
            + "where ng.id_categoria = :idCategoria\n"
            + "and n.estado = 'a';", nativeQuery = true)
    List<Map<String,Object>> findNewsByCategorie(Long id);
}
