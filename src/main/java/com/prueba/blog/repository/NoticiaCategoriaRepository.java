/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.blog.repository;

import com.prueba.blog.model.NoticiaCategoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Angelo
 */
public interface NoticiaCategoriaRepository extends JpaRepository<NoticiaCategoria, Long> {

    @Query(value = "select distinct nc.id_categoria from noticias_categorias nc where id_noticia = :idNoticia", nativeQuery = true)
    List<Long> findCategoriesByIdNoticia(Long idNoticia);

}
