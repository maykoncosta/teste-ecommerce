package com.onebrain.loja.business.impl;

import com.onebrain.loja.dto.MarcaViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Marca;
import com.onebrain.loja.repository.MarcaRepository;
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
public class MarcaBusinessImplTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaBusinessImpl marcaBusiness;

    private Marca marca1;

    private Marca marca2;

    @BeforeEach
    public void setUp() {
        marca1 = Marca.builder().id(1L).codigo("MAR01").descricao("Marca Exemplo").ativo(true).build();
        marca2 = Marca.builder().id(2L).codigo("MAR02").descricao("Marca Exemplo 2").ativo(true).build();

        when(marcaRepository.findMarcaByIdAndAtivoTrue(1L)).thenReturn(Optional.of(marca1));
        when(marcaRepository.findById(2L)).thenReturn(Optional.of(marca2));
        when(marcaRepository.findMarcaByIdAndAtivoTrue(2L)).thenReturn(Optional.of(marca2));
        when(marcaRepository.findByAtivoTrue()).thenReturn(List.of(marca1, marca2));
    }

    @Test
    public void testListarTodos() {
        List<MarcaViewDTO> resultado = marcaBusiness.listarTodos();

        verify(marcaRepository, times(1)).findByAtivoTrue();

        assertEquals(2, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        MarcaViewDTO marcaViewDTO = marcaBusiness.buscarPorId(1L);

        verify(marcaRepository, times(1)).findMarcaByIdAndAtivoTrue(1L);

        assertEquals("MAR01", marcaViewDTO.getCodigo());
    }

    @Test
    public void testBuscarPorCodigo() {
        String codigo = "MAR01";
        when(marcaRepository.findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo)).thenReturn(Optional.of(marca1));

        MarcaViewDTO marcaViewDTO = marcaBusiness.buscarPorCodigo(codigo);

        verify(marcaRepository, times(1)).findByCodigoEqualsIgnoreCaseAndAtivoTrue(codigo);
        assertEquals(codigo, marcaViewDTO.getCodigo());
    }

    @Test
    public void testSalvarOuAtualizarSalvar() {
        MarcaViewDTO marcaViewDTO = MarcaViewDTO.builder()
                .codigo("MAR01").descricao("Descrição do Marca 01").build();
        when(marcaRepository.save(any(Marca.class))).thenReturn(Marca.builder().id(1L).build());

        MarcaViewDTO resultado = marcaBusiness.salvarOuAtualizar(marcaViewDTO, TipoOperacaoRepository.SALVAR);

        verify(marcaRepository, times(1)).save(any(Marca.class));
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void testDesativar() {
        Long id = 1L;

        marcaBusiness.desativar(id);

        verify(marcaRepository, times(1)).findMarcaByIdAndAtivoTrue(id);
        verify(marcaRepository, times(1)).save(marca1);
        assertFalse(marca1.isAtivo());
    }

    @Test
    public void testAtivar() {
        Long id = 2L;

        marcaBusiness.ativar(id);

        verify(marcaRepository, times(1)).findById(id);
        verify(marcaRepository, times(1)).save(marca2);
        assertTrue(marca2.isAtivo());
    }

}
