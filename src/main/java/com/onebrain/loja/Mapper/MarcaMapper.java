package com.onebrain.loja.Mapper;

import com.onebrain.loja.dto.MarcaViewDTO;
import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper
public interface MarcaMapper {

    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    Marca viewToEntity(MarcaViewDTO dto);

    @Mapping(target = "codigoProdutoList", expression = "java(obterCodigoProdutosAtivos(entidade))")
    MarcaViewDTO entityToView(Marca entidade);

    default List<String> obterCodigoProdutosAtivos(Marca entidade){
        if(Objects.nonNull(entidade.getProdutos())){
            return entidade.getProdutos()
                    .stream().filter(Produto::isAtivo)
                    .map(Produto::getCodigo).toList();
        }

        return new ArrayList<>();
    }

}
