package com.onebrain.loja.repository;

import com.onebrain.loja.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findProdutosByAtivoTrue();

    Optional<Produto> findProdutoByCodigoEqualsIgnoreCaseAndAtivoTrue(String codigoProduto);

    List<Produto> findProdutosByCodigoStartingWithAndAtivoTrue(String codigoProduto);

    List<Produto> findByMarcaCodigoAndAtivoTrue(String codigoMarca);

    List<Produto> findByCategoriasCodigoAndAtivoTrue(String codigoCategoria);

    List<Produto> findProdutosByAtivoFalse();

}
