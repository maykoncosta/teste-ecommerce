package com.onebrain.loja.business;

import com.onebrain.loja.enums.TipoOperacaoRepository;

import java.util.List;

public interface AbstractCrudBusiness<T> {

    List<T> listarTodos();

    T buscarPorId(Long id);

    T salvarOuAtualizar(T entity, TipoOperacaoRepository operacao);

    void desativar(T entity);

    void ativar(T entity);
}