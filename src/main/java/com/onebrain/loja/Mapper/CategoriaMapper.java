package com.onebrain.loja.Mapper;

import com.onebrain.loja.dto.CategoriaViewDTO;
import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    Categoria viewToEntity(CategoriaViewDTO dto);

    @Mapping(target = "codigoProdutoList", expression = "java(obterCodigoProdutos(entidade))")
    CategoriaViewDTO entityToView(Categoria entidade);

    default List<String> obterCodigoProdutos(Categoria entidade){
        return entidade.getProdutos().stream().map(Produto::getCodigo).toList();
    }

}
