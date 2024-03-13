package com.onebrain.loja.business.impl;

import com.onebrain.loja.business.AbstractCrudBusiness;
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
public class CategoriaBusinessImpl implements AbstractCrudBusiness<Categoria> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaBusinessImpl.class);

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> listarTodos() {
        LOGGER.info("Buscando todas as categorias.");

        return repository.findByAtivoTrue();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        LOGGER.info("Buscando categoria por ID: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + id));
    }

    @Override
    public Categoria buscarPorCodigo(String codigo) {
        LOGGER.info("Buscando categoria por código: {}", codigo);

        return repository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);
    }

    @Override
    public Categoria salvarOuAtualizar(Categoria entity, TipoOperacaoRepository operacao) {
        LOGGER.info("Salvando/Atualizando categoria: {}", entity.getCodigo());

        Categoria savedCategoria;

        if (operacao == TipoOperacaoRepository.SALVAR) {
            savedCategoria = repository.save(entity);
        } else if (operacao == TipoOperacaoRepository.ATUALIZAR) {
            if (Objects.isNull(entity.getId())) {
                throw new IllegalArgumentException("ID da categoria não pode ser nulo para atualização.");
            }
            repository.findById(entity.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + entity.getId()));

            savedCategoria = repository.save(entity);
        } else {
            throw new IllegalArgumentException("Tipo de operação de repositório inválido: " + operacao);
        }

        return savedCategoria;
    }

    @Override
    public void desativar(Categoria entity) {
        LOGGER.info("Desativando categoria: {}", entity);

        entity.setAtivo(false);
        repository.save(entity);
    }

    @Override
    public void ativar(Categoria entity) {
        LOGGER.info("Ativando categoria: {}", entity);

        entity.setAtivo(true);
        repository.save(entity);
    }
}
