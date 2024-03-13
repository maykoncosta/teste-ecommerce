package com.onebrain.loja.business.impl;

import com.onebrain.loja.Mapper.ProdutoMapper;
import com.onebrain.loja.business.AbstractCrudBusiness;
import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Produto;
import com.onebrain.loja.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProdutoBusinessImpl implements AbstractCrudBusiness<ProdutoViewDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoBusinessImpl.class);

    @Autowired
    private ProdutoRepository repository;

    @Override
    public List<ProdutoViewDTO> listarTodos() {
        LOGGER.info("Buscando todos os produtos ativos.");

        List<Produto> produtos = repository.findByAtivoTrue();
        return produtos
                .stream().map(ProdutoMapper.INSTANCE::entityToView)
                .toList();
    }

    @Override
    public ProdutoViewDTO buscarPorId(Long id) {
        LOGGER.info("Buscando produto por ID: {}", id);

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + id));

        return ProdutoMapper.INSTANCE.entityToView(produto);
    }

    @Override
    public ProdutoViewDTO salvarOuAtualizar(ProdutoViewDTO entity, TipoOperacaoRepository operacao) {
        LOGGER.info("Salvando/Atualizando produto: {}", entity.getCodigo());

        Produto produto = ProdutoMapper.INSTANCE.viewToEntity(entity);
        Produto savedProduto;

        if (operacao == TipoOperacaoRepository.SALVAR) {
            savedProduto = repository.save(produto);
        } else if (operacao == TipoOperacaoRepository.ATUALIZAR) {
            if (Objects.isNull(produto.getId())) {
                throw new IllegalArgumentException("ID do produto não pode ser nulo para atualização.");
            }
            repository.findById(entity.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + entity.getId()));

            savedProduto = repository.save(produto);
        } else {
            throw new IllegalArgumentException("Tipo de operação de repositório inválido: " + operacao);
        }

        return ProdutoMapper.INSTANCE.entityToView(savedProduto);
    }

    @Override
    public void desativar(ProdutoViewDTO entity) {
        LOGGER.info("Desativando produto: {}", entity);

        Produto produto = ProdutoMapper.INSTANCE.viewToEntity(entity);
        produto.setAtivo(false);
        repository.save(produto);
    }

    @Override
    public void ativar(ProdutoViewDTO entity) {
        LOGGER.info("Ativando produto: {}", entity);

        Produto produto = ProdutoMapper.INSTANCE.viewToEntity(entity);
        produto.setAtivo(true);
        repository.save(produto);
    }
}
