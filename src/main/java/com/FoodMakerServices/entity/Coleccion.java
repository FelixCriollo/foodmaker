package com.FoodMakerServices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "coleccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coleccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private int idUsuario;

    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    private Usuario usuario;

    private byte[] imagen;

    private LocalDate fechaCreacion;

    private LocalDate fechaUltimaModificacion;

    private boolean activado;

    private boolean borrado;
}
