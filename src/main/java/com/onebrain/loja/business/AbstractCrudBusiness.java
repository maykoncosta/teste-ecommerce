package com.onebrain.loja.business;

import com.onebrain.loja.enums.TipoOperacaoRepository;

import java.util.List;

public interface AbstractCrudBusiness<T> {

    List<T> listarTodos();

    T buscarPorId(Long id);

    T buscarPorCodigo(String codigo);

    T salvarOuAtualizar(T entity, TipoOperacaoRepository operacao);

    void desativar(Long id);

    void ativar(Long id);
}
