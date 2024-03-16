/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prueba.blog.Projections;


import java.util.Date;

/**
 *
 * @author Angelo
 */
public interface NoticiaProjection {

     Long getId_Noticia();

     String getTitulo();

     String getImagen();

     String getCuerpo();

     Date getFechaPub();

     String getDescripcion();

     char getEstado();

}
