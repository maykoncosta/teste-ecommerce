package com.onebrain.loja.dto;

import com.onebrain.loja.enums.IndicadorDisponibilidade;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProdutoViewDTO {

    private Long id;

    private String codigo;

    private String descricao;

    private double preco;

    private int quantidade;

    private IndicadorDisponibilidade disponibilidade;

    private List<String> codigoCategorias;

    private String codigoMarca;

    private boolean ativo;
}
