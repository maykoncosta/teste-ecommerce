package com.onebrain.loja.Mapper;

import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto viewToEntity(ProdutoViewDTO dto);

    @Mapping(target = "codigoCategorias", expression = "java(obterCodigoCategoriasAtivas(entidade))")
    @Mapping(target = "codigoMarca", source = "marca.codigo")
    ProdutoViewDTO entityToView(Produto entidade);

    default List<String> obterCodigoCategoriasAtivas(Produto entidade){
        if(Objects.nonNull(entidade.getCategorias())){
            return entidade.getCategorias()
                    .stream().filter(Categoria::isAtivo)
                    .map(Categoria::getCodigo).toList();
        }
        return new ArrayList<>();
    }

}
