package com.onebrain.loja.business.impl;

import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.model.Produto;
import com.onebrain.loja.repository.CategoriaRepository;
import com.onebrain.loja.repository.MarcaRepository;
import com.onebrain.loja.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProdutoBusinessImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private MarcaRepository marcaRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoBusinessImpl produtoBusiness;

    private Produto produto1;

    private Produto produto2;

    @BeforeEach
    public void setUp() {
        Marca marca1 = Marca.builder().id(1L).codigo("MAR01").descricao("Marca Exemplo").ativo(true).build();
        produto1 = Produto.builder().id(1L).codigo("PROD01").descricao("Descrição do Produto 01").preco(29.99).marca(marca1).ativo(true).build();

        Marca marca2 = Marca.builder().id(2L).codigo("MAR02").descricao("Marca Exemplo 2").ativo(true).build();
        produto2 = Produto.builder().id(2L).codigo("PROD02").descricao("Descrição do Produto 02").preco(39.99).marca(marca2).ativo(true).build();
        Categoria categoriaExistente = new Categoria();

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto1));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));
        when(produtoRepository.findProdutosByAtivoTrue()).thenReturn(List.of(produto1, produto2));
        when(marcaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(anyString())).thenReturn(Optional.of(marca1));
        when(categoriaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(anyString())).thenReturn(Optional.of(categoriaExistente));

    }

    @Test
    public void testListarTodos() {
        List<ProdutoViewDTO> resultado = produtoBusiness.listarTodos();

        verify(produtoRepository, times(1)).findProdutosByAtivoTrue();

        assertEquals(2, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        ProdutoViewDTO produtoViewDTO = produtoBusiness.buscarPorId(1L);
        // Verificar se o método do repositório foi chamado
        verify(produtoRepository, times(1)).findById(1L);

        assertEquals("PROD01", produtoViewDTO.getCodigo());
    }

    @Test
    public void testBuscarPorCodigo() {
        String codigo = "PROD01";
        when(produtoRepository.findProdutoByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo)).thenReturn(Optional.ofNullable(produto1));

        ProdutoViewDTO produtoViewDTO = produtoBusiness.buscarPorCodigo(codigo);

        verify(produtoRepository, times(1)).findProdutoByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);
        assertEquals(codigo, produtoViewDTO.getCodigo());
    }

    @Test
    public void testSalvarOuAtualizarSalvar() {
        ProdutoViewDTO produtoViewDTO = ProdutoViewDTO.builder()
                .codigo("PROD01").descricao("Descrição do Produto 01")
                .preco(29.99).codigoMarca("MARCA_EXISTENTE")
                .codigoCategorias(List.of("CATEGORIA_EXISTENTE")).build();
        when(produtoRepository.save(any(Produto.class))).thenReturn(Produto.builder().id(1L).build());

        ProdutoViewDTO resultado = produtoBusiness.salvarOuAtualizar(produtoViewDTO, TipoOperacaoRepository.SALVAR);

        verify(produtoRepository, times(1)).save(any(Produto.class));
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testDesativar() {
        Long id = 1L;

        produtoBusiness.desativar(id);

        verify(produtoRepository, times(1)).findById(id);
        verify(produtoRepository, times(1)).save(produto1);
        assertFalse(produto1.isAtivo());
    }

    @Test
    public void testAtivar() {
        // Dado um ID de produto
        Long id = 2L;

        // Quando o método ativar é chamado com esse ID
        produtoBusiness.ativar(id);

        // Então o método do repositório deve ser chamado e o produto deve estar ativado
        verify(produtoRepository, times(1)).findById(id);
        verify(produtoRepository, times(1)).save(produto2);
        assertTrue(produto2.isAtivo());
    }

    @Test
    public void testBuscarPorInicioCodigo() {
        String inicioCodigo = "PROD";
        when(produtoRepository.findProdutosByCodigoStartingWithAndAtivoTrue(inicioCodigo.toUpperCase())).thenReturn(List.of(produto1, produto2));

        List<ProdutoViewDTO> resultado = produtoBusiness.buscarPorInicioCodigo(inicioCodigo);

        verify(produtoRepository, times(1)).findProdutosByCodigoStartingWithAndAtivoTrue(inicioCodigo.toUpperCase());
        assertEquals(2, resultado.size());
    }

    @Test
    void validarEAtualizarMarcaECategoria_MarcasECategoriasExistentes_DeveRetornarEntidadeComMarcasECategorias() {

        Marca marcaExistente = new Marca();
        Categoria categoriaExistente = new Categoria();
        ProdutoViewDTO produtoDTO = new ProdutoViewDTO();
        produtoDTO.setCodigoMarca("MARCA_EXISTENTE");
        produtoDTO.setCodigoCategorias(List.of("CATEGORIA_EXISTENTE"));
        Produto produto = new Produto();

        when(marcaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(anyString())).thenReturn(Optional.of(marcaExistente));
        when(categoriaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(anyString())).thenReturn(Optional.of(categoriaExistente));

        produtoBusiness.validarEAtualizarMarcaECategoria(produtoDTO, produto);

        assertEquals(marcaExistente, produto.getMarca());
        assertEquals(Collections.singleton(categoriaExistente), produto.getCategorias());
    }

}
