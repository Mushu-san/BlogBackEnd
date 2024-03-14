/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.blog.repository;

import com.prueba.blog.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Angelo
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
