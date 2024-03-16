package com.onebrain.loja.business.impl;

import com.onebrain.loja.Mapper.MarcaMapper;
import com.onebrain.loja.business.AbstractCrudBusiness;
import com.onebrain.loja.dto.MarcaViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.repository.MarcaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MarcaBusinessImpl implements AbstractCrudBusiness<MarcaViewDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarcaBusinessImpl.class);

    @Autowired
    private MarcaRepository repository;

    @Override
    public List<MarcaViewDTO> listarTodos() {
        LOGGER.info("Buscando todas as marcas.");
        List<Marca> marcas = repository.findByAtivoTrue();

        return marcas.stream().map(MarcaMapper.INSTANCE::entityToView).toList();
    }

    @Override
    public MarcaViewDTO buscarPorId(Long id) {
        LOGGER.info("Buscando marca por ID: {}", id);
        Marca marca = repository.findMarcaByIdAndAtivoTrue(id)
                .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada com o ID: " + id));
        return MarcaMapper.INSTANCE.entityToView(marca);
    }

    @Override
    public MarcaViewDTO buscarPorCodigo(String codigo) {
        LOGGER.info("Buscando marca por código: {}", codigo);
        Marca marca = repository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada com o codigo: " + codigo));

        return MarcaMapper.INSTANCE.entityToView(marca);
    }

    @Override
    public MarcaViewDTO salvarOuAtualizar(MarcaViewDTO dto, TipoOperacaoRepository operacao) {
        LOGGER.info("Salvando/Atualizando marca: {}", dto.getCodigo());

        Marca marca = MarcaMapper.INSTANCE.viewToEntity(dto);
        marca.setCodigo(dto.getCodigo().toUpperCase());
        Marca savedMarca;

        if (operacao == TipoOperacaoRepository.SALVAR) {
            savedMarca = repository.save(marca);
        } else if (operacao == TipoOperacaoRepository.ATUALIZAR) {
            if (Objects.isNull(marca.getId())) {
                throw new IllegalArgumentException("ID da marca não pode ser nulo para atualização.");
            }
            Marca entidade = repository.findById(marca.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada com o ID: " + marca.getId()));

            marca.setDataCriacao(entidade.getDataCriacao());
            marca.setUsuarioCriacao(entidade.getUsuarioCriacao());
            savedMarca = repository.save(marca);
        } else {
            throw new IllegalArgumentException("Tipo de operação de repositório inválido: " + operacao);
        }

        return MarcaMapper.INSTANCE.entityToView(savedMarca);
    }

    @Override
    public void desativar(Long id) {
        LOGGER.info("Desativando marca: {}", id);
        Marca entidade = repository.findMarcaByIdAndAtivoTrue(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + id));
        entidade.setAtivo(false);
        repository.save(entidade);
    }

    @Override
    public void ativar(Long id) {
        LOGGER.info("Ativando marca: {}", id);
        Marca entidade = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com o ID: " + id));

        entidade.setAtivo(true);
        repository.save(entidade);
    }
}
