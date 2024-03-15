package com.onebrain.loja.controller;

import com.onebrain.loja.business.impl.CategoriaBusinessImpl;
import com.onebrain.loja.dto.CategoriaViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
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
    public ResponseEntity<List<CategoriaViewDTO>> listarTodos() {
        List<CategoriaViewDTO> categorias = categoriaBusiness.listarTodos();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaViewDTO> buscarPorId(@PathVariable Long id) {
        CategoriaViewDTO dto = categoriaBusiness.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CategoriaViewDTO> buscarPorCodigo(@PathVariable String codigo) {
        CategoriaViewDTO categoria = categoriaBusiness.buscarPorCodigo(codigo);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaViewDTO> salvar(@RequestBody CategoriaViewDTO dto) {
        CategoriaViewDTO categoriaSalva = categoriaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaViewDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaViewDTO dto) {
        dto.setId(id);
        CategoriaViewDTO categoriaAtualizada = categoriaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.ATUALIZAR);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        categoriaBusiness.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        categoriaBusiness.ativar(id);
        return ResponseEntity.noContent().build();
    }
}
