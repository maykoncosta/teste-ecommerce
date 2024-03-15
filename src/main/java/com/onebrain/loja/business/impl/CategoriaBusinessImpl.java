package com.onebrain.loja.business.impl;

import com.onebrain.loja.Mapper.CategoriaMapper;
import com.onebrain.loja.business.AbstractCrudBusiness;
import com.onebrain.loja.dto.CategoriaViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoriaBusinessImpl implements AbstractCrudBusiness<CategoriaViewDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaBusinessImpl.class);

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<CategoriaViewDTO> listarTodos() {
        LOGGER.info("Buscando todas as categorias.");
        List<Categoria> categorias = repository.findByAtivoTrue();
        return categorias.stream()
                .map(CategoriaMapper.INSTANCE::entityToView)
                .toList();
    }

    @Override
    public CategoriaViewDTO buscarPorId(Long id) {
        LOGGER.info("Buscando categoria por ID: {}", id);
        Categoria entidade = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + id));
        
        
        return CategoriaMapper.INSTANCE.entityToView(entidade);
    }

    @Override
    public CategoriaViewDTO buscarPorCodigo(String codigo) {
        LOGGER.info("Buscando categoria por código: {}", codigo);

        Categoria categoria = repository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);
        return CategoriaMapper.INSTANCE.entityToView(categoria);
    }

    @Override
    public CategoriaViewDTO salvarOuAtualizar(CategoriaViewDTO dto, TipoOperacaoRepository operacao) {
        LOGGER.info("Salvando/Atualizando categoria: {}", dto.getCodigo());

        Categoria entidade = CategoriaMapper.INSTANCE.viewToEntity(dto);
        Categoria savedCategoria;

        if (operacao == TipoOperacaoRepository.SALVAR) {
            savedCategoria = repository.save(entidade);
        } else if (operacao == TipoOperacaoRepository.ATUALIZAR) {
            if (Objects.isNull(entidade.getId())) {
                throw new IllegalArgumentException("ID da categoria não pode ser nulo para atualização.");
            }
            repository.findById(entidade.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + entidade.getId()));

            savedCategoria = repository.save(entidade);
        } else {
            throw new IllegalArgumentException("Tipo de operação de repositório inválido: " + operacao);
        }

        return CategoriaMapper.INSTANCE.entityToView(savedCategoria);
    }

    @Override
    public void desativar(Long id) {
        LOGGER.info("Desativando categoria: {}", id);
        Categoria entidade = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + id));

        entidade.setAtivo(false);
        repository.save(entidade);
    }

    @Override
    public void ativar(Long id) {
        LOGGER.info("Ativando categoria: {}", id);
        Categoria entidade = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + id));

        entidade.setAtivo(true);
        repository.save(entidade);
    }
    
}
