package com.onebrain.loja.business.impl;

import com.onebrain.loja.dto.CategoriaViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.repository.CategoriaRepository;
import com.onebrain.loja.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoriaBusinessImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaBusinessImpl categoriaBusiness;

    private Categoria categoria1;

    private Categoria categoria2;

    @BeforeEach
    public void setUp() {
        categoria1 = Categoria.builder().id(1L).codigo("CAT01").descricao("Categoria Exemplo").ativo(true).build();
        categoria2 = Categoria.builder().id(2L).codigo("CAT02").descricao("Categoria Exemplo 2").ativo(true).build();

        when(categoriaRepository.findCategoriaByIdAndAtivoTrue(1L)).thenReturn(Optional.of(categoria1));
        when(categoriaRepository.findById(2L)).thenReturn(Optional.of(categoria2));
        when(categoriaRepository.findCategoriaByIdAndAtivoTrue(2L)).thenReturn(Optional.of(categoria2));
        when(categoriaRepository.findByAtivoTrue()).thenReturn(List.of(categoria1, categoria2));
    }

    @Test
    public void testListarTodos() {
        List<CategoriaViewDTO> resultado = categoriaBusiness.listarTodos();

        verify(categoriaRepository, times(1)).findByAtivoTrue();

        assertEquals(2, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        CategoriaViewDTO categoriaViewDTO = categoriaBusiness.buscarPorId(1L);

        verify(categoriaRepository, times(1)).findCategoriaByIdAndAtivoTrue(1L);

        assertEquals("CAT01", categoriaViewDTO.getCodigo());
    }

    @Test
    public void testBuscarPorCodigo() {
        String codigo = "CAT01";
        when(categoriaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo)).thenReturn(Optional.of(categoria1));

        CategoriaViewDTO categoriaViewDTO = categoriaBusiness.buscarPorCodigo(codigo);

        verify(categoriaRepository, times(1)).findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);
        assertEquals(codigo, categoriaViewDTO.getCodigo());
    }

    @Test
    public void testSalvarOuAtualizarSalvar() {
        CategoriaViewDTO categoriaViewDTO = CategoriaViewDTO.builder()
                .codigo("CAT01").descricao("Descrição do Categoria 01").build();
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(Categoria.builder().id(1L).build());

        CategoriaViewDTO resultado = categoriaBusiness.salvarOuAtualizar(categoriaViewDTO, TipoOperacaoRepository.SALVAR);

        verify(categoriaRepository, times(1)).save(any(Categoria.class));
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testDesativar() {
        Long id = 1L;

        categoriaBusiness.desativar(id);

        verify(categoriaRepository, times(1)).findCategoriaByIdAndAtivoTrue(id);
        verify(categoriaRepository, times(1)).save(categoria1);
        assertFalse(categoria1.isAtivo());
    }

    @Test
    public void testAtivar() {
        Long id = 2L;

        categoriaBusiness.ativar(id);

        verify(categoriaRepository, times(1)).findById(id);
        verify(categoriaRepository, times(1)).save(categoria2);
        assertTrue(categoria2.isAtivo());
    }

}
