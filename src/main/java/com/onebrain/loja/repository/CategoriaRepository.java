package com.onebrain.loja.repository;

import com.onebrain.loja.model.Categoria;
import com.onebrain.loja.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByAtivoTrue();

    Categoria findByCodigoEqualsIgnoreCaseAndAtivoTrue(String codigo);

}
