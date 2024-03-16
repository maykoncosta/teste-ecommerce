package com.onebrain.loja.model;

import com.onebrain.loja.audit.TrilhaAuditavel;
import com.onebrain.loja.audit.TrilhaAuditavelListener;
import com.onebrain.loja.enums.IndicadorDisponibilidade;
import com.onebrain.loja.enums.IndicadorStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(TrilhaAuditavelListener.class)
@Entity(name="produto")
public class Produto extends TrilhaAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(length = 20, nullable = false, name = "codigo")
    private String codigo;

    @Column(length = 50, nullable = false, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "preco")
    private double preco;

    @Column(name = "sku")
    private String sku;

    @Column(name = "disponibilidade")
    private IndicadorDisponibilidade disponibilidade;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;
}

