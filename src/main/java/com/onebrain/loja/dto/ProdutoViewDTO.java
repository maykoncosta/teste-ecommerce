package com.onebrain.loja.dto;

import com.onebrain.loja.enums.IndicadorDisponibilidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoViewDTO {

    private Long id;

    private String codigo;

    private double preco;

    private int quantidade;

    private IndicadorDisponibilidade disponibilidade;

    private String codigoCategoria;

    private String codigoMarca;

}
