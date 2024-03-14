package com.onebrain.loja.business.impl;

import com.onebrain.loja.business.AbstractCrudBusiness;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.repository.MarcaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MarcaBusinessImpl implements AbstractCrudBusiness<Marca> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarcaBusinessImpl.class);

    @Autowired
    private MarcaRepository repository;

    @Override
    public List<Marca> listarTodos() {
        LOGGER.info("Buscando todas as marcas.");

        return repository.findByAtivoTrue();
    }

    @Override
    public Marca buscarPorId(Long id) {
        LOGGER.info("Buscando marca por ID: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada com o ID: " + id));
    }

    @Override
    public Marca buscarPorCodigo(String codigo) {
        LOGGER.info("Buscando marca por código: {}", codigo);

        return repository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);
    }

    @Override
    public Marca salvarOuAtualizar(Marca entity, TipoOperacaoRepository operacao) {
        LOGGER.info("Salvando/Atualizando marca: {}", entity.getCodigo());

        Marca savedMarca;

        if (operacao == TipoOperacaoRepository.SALVAR) {
            savedMarca = repository.save(entity);
        } else if (operacao == TipoOperacaoRepository.ATUALIZAR) {
            if (Objects.isNull(entity.getId())) {
                throw new IllegalArgumentException("ID da marca não pode ser nulo para atualização.");
            }
            repository.findById(entity.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada com o ID: " + entity.getId()));

            savedMarca = repository.save(entity);
        } else {
            throw new IllegalArgumentException("Tipo de operação de repositório inválido: " + operacao);
        }

        return savedMarca;
    }

    @Override
    public void desativar(Marca entity) {
        LOGGER.info("Desativando marca: {}", entity.getCodigo());

        entity.setAtivo(false);
        repository.save(entity);
    }

    @Override
    public void ativar(Marca entity) {
        LOGGER.info("Ativando marca: {}", entity.getCodigo());

        entity.setAtivo(true);
        repository.save(entity);
    }
}
