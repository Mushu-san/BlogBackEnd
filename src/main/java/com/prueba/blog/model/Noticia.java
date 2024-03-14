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
import java.util.Date;
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
@Table(name = "noticias")
public class Noticia implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_noticia")
    private Long idNoticia;

    @Column(name = "titulo", length = 50)
    private String titulo;

    @Column(name = "imagen", length = 255)
    private String imagen;

    @Column(name = "cuerpo", length = 255)
    private String cuerpo;

    @Column(name = "fecha_pub")
    private Date fechaPub;

    @Column(name = "descripcion", length = 75)
    private String descripcion;

    @Column(name = "estado")
    private char estado;

}
