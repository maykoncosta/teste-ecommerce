package com.onebrain.loja.dto;

import com.onebrain.loja.enums.IndicadorDisponibilidade;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoViewDTO {

    private Long id;

    private String codigo;

    private String descricao;

    private double preco;

    private String sku;

    private IndicadorDisponibilidade disponibilidade;

    private List<String> codigoCategorias;

    private String codigoMarca;

    private boolean ativo;
}
