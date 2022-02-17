package com.JavaFullStack.Proyecto.Java.FullStack.models;
import javax.persistence.*;
import javax.persistence.Entity;

import lombok.*;


@Entity
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "nombre")
    private String nombre;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "password")
    private String password;

}

