package com.onebrain.loja.controller;

import com.onebrain.loja.business.impl.CategoriaBusinessImpl;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaBusinessImpl categoriaBusiness;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos() {
        List<Categoria> categorias = categoriaBusiness.listarTodos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaBusiness.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable String codigo) {
        Categoria categoria = categoriaBusiness.buscarPorCodigo(codigo);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoriaViewDTO) {
        Categoria categoriaSalva = categoriaBusiness.salvarOuAtualizar(categoriaViewDTO, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoriaViewDTO) {
        categoriaViewDTO.setId(id);
        Categoria categoriaAtualizada = categoriaBusiness.salvarOuAtualizar(categoriaViewDTO, TipoOperacaoRepository.ATUALIZAR);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        Categoria categoriaViewDTO = categoriaBusiness.buscarPorId(id);
        categoriaBusiness.desativar(categoriaViewDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        Categoria categoriaViewDTO = categoriaBusiness.buscarPorId(id);
        categoriaBusiness.ativar(categoriaViewDTO);
        return ResponseEntity.noContent().build();
    }
}
