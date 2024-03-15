package com.onebrain.loja.Mapper;

import com.onebrain.loja.dto.MarcaViewDTO;
import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MarcaMapper {

    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    Marca viewToEntity(MarcaViewDTO dto);

    @Mapping(target = "codigoProdutoList", expression = "java(obterCodigoProdutos(entidade))")
    MarcaViewDTO entityToView(Marca entidade);

    default List<String> obterCodigoProdutos(Marca entidade){
        return entidade.getProdutos().stream().map(Produto::getCodigo).toList();
    }

}
