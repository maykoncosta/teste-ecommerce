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

    @Column(nullable = false, length = 20, name = "codigo")
    private String codigo;

    @Column(length = 50, name = "descricao")
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new HashSet<>();

    @Column(nullable = false, name = "ativo")
    private boolean ativo;
}
