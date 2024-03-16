package com.onebrain.loja.Mapper;

import com.onebrain.loja.dto.CategoriaViewDTO;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    Categoria viewToEntity(CategoriaViewDTO dto);

    @Mapping(target = "codigoProdutoList", expression = "java(obterCodigoProdutosAtivos(entidade))")
    CategoriaViewDTO entityToView(Categoria entidade);

    default List<String> obterCodigoProdutosAtivos(Categoria entidade){
        if(Objects.nonNull(entidade.getProdutos())){
            return entidade.getProdutos()
                    .stream().filter(Produto::isAtivo)
                    .map(Produto::getCodigo).toList();
        }

        return new ArrayList<>();
    }

}
