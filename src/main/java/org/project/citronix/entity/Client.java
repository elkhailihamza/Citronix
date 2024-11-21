package org.project.citronix.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String pass_word;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Vente> ventes;
}
