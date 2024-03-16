package com.onebrain.loja.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaViewDTO {

    private Long id;

    private String codigo;

    private String descricao;

    private List<String> codigoProdutoList;

    private boolean ativo;
}
