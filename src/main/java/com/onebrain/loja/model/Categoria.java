package com.onebrain.loja.model;


import com.onebrain.loja.audit.TrilhaAuditavel;
import com.onebrain.loja.audit.TrilhaAuditavelListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EntityListeners(TrilhaAuditavelListener.class)
@Entity(name="categoria")
public class Categoria extends TrilhaAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new HashSet<>();
}
