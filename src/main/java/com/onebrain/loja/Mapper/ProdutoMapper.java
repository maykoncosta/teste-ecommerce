package com.onebrain.loja.Mapper;

import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto viewToEntity(ProdutoViewDTO dto);

    ProdutoViewDTO entityToView(Produto entity);

}
