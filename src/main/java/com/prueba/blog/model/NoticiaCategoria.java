/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prueba.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Angelo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "noticias_categorias")
public class NoticiaCategoria implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "id_noticia_categoria")
    private Long idNoticiaCategoria;

    @Column(name = "id_noticia")
    private Long idNoticia;
    
    @Column(name = "id_categoria")
    private Long idCategoria;
    
    @Column(name = "estado")
    private char estado;
    
}
