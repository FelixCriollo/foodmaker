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
public class ColeccionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idColeccion;

    private int idReceta;

    @ManyToOne
    @JoinColumn(name = "idReceta", insertable = false, updatable = false)
    private Receta receta;

    private LocalDate fechaCreacion;

    private byte[] imagen;
}
