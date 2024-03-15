package com.onebrain.loja.dto;

import com.onebrain.loja.enums.IndicadorDisponibilidade;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriaViewDTO {

    private Long id;

    private String codigo;

    private String descricao;

    private List<String> codigoProdutoList;

    private boolean ativo;
}
