package com.onebrain.loja.business.impl;

import com.onebrain.loja.Mapper.ProdutoMapper;
import com.onebrain.loja.business.AbstractCrudBusiness;
import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.model.Produto;
import com.onebrain.loja.repository.CategoriaRepository;
import com.onebrain.loja.repository.MarcaRepository;
import com.onebrain.loja.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoBusinessImpl implements AbstractCrudBusiness<ProdutoViewDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoBusinessImpl.class);

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public List<ProdutoViewDTO> listarTodos() {
        LOGGER.info("Buscando todos os produtos ativos.");

        List<Produto> produtos = repository.findProdutosByAtivoTrue();
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
    public ProdutoViewDTO buscarPorCodigo(String codigo) {
        LOGGER.info("Buscando produto por codigo: {}", codigo);

        Produto produto = repository.findProdutoByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);

        return ProdutoMapper.INSTANCE.entityToView(produto);
    }

    @Override
    public ProdutoViewDTO salvarOuAtualizar(ProdutoViewDTO dto, TipoOperacaoRepository operacao) {
        LOGGER.info("Salvando/Atualizando produto: {}", dto.getCodigo());

        Produto produto = ProdutoMapper.INSTANCE.viewToEntity(dto);
        validarEAtualizarMarcaECategoria(dto, produto);
        Produto savedProduto;

        if (operacao == TipoOperacaoRepository.SALVAR) {
            savedProduto = repository.save(produto);
        } else if (operacao == TipoOperacaoRepository.ATUALIZAR) {
            if (Objects.isNull(produto.getId())) {
                throw new IllegalArgumentException("ID do produto não pode ser nulo para atualização.");
            }
            repository.findById(dto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + dto.getId()));

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

    public List<ProdutoViewDTO> buscarPorInicioCodigo(String inicioCodigo) {
        LOGGER.info("Buscando produtos que comecam com codigo: {}", inicioCodigo);
        if(Objects.isNull(inicioCodigo)){
            return new ArrayList<>();
        }
        List<Produto> produtos = repository.findProdutosByCodigoStartingWithAndAtivoTrue(inicioCodigo.toUpperCase());
        return produtos
                .stream().map(ProdutoMapper.INSTANCE::entityToView)
                .toList();
    }

    public List<ProdutoViewDTO> buscarPorCodigoMarca(String codigoMarca) {
        LOGGER.info("Buscando produtos que pertencem a marca: {}", codigoMarca);
        if(Objects.isNull(codigoMarca)){
            return new ArrayList<>();
        }
        List<Produto> produtos = repository.findByMarcaCodigoAndAtivoTrue(codigoMarca.toUpperCase());
        return produtos
                .stream().map(ProdutoMapper.INSTANCE::entityToView)
                .toList();
    }

    public List<ProdutoViewDTO> buscarPorCodigoCategoria(String codigoCategoria) {
        LOGGER.info("Buscando produtos que pertencem a categoria: {}", codigoCategoria);
        if(Objects.isNull(codigoCategoria)){
            return new ArrayList<>();
        }
        List<Produto> produtos = repository.findByCategoriasCodigoAndAtivoTrue(codigoCategoria.toUpperCase());
        return produtos
                .stream().map(ProdutoMapper.INSTANCE::entityToView)
                .toList();
    }

    private void validarEAtualizarMarcaECategoria(ProdutoViewDTO dto, Produto entity) {
        String codigoMarca = dto.getCodigoMarca();
        Marca marca = marcaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigoMarca);
        Set<Categoria> categoriaList = new HashSet<>();
        dto.getCodigoCategorias().forEach(categoria ->
            categoriaList.add(categoriaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(categoria))
        );
        //TODO lancar exception caso nao tenha marca ou categorias

        entity.setMarca(marca);
        entity.setCategorias(categoriaList);
    }
}
