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

    @GetMapping("/desativados")
    public ResponseEntity<List<CategoriaViewDTO>> listarTodosDesativados() {
        List<CategoriaViewDTO> categorias = categoriaBusiness.listarTodosDesativados();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaViewDTO> buscarPorId(@PathVariable Long id) {
        try{
            CategoriaViewDTO dto = categoriaBusiness.buscarPorId(id);
            return ResponseEntity.ok(dto);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CategoriaViewDTO> buscarPorCodigo(@PathVariable String codigo) {
        try{
            CategoriaViewDTO categoria = categoriaBusiness.buscarPorCodigo(codigo);
            return ResponseEntity.ok(categoria);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaViewDTO> salvar(@RequestBody CategoriaViewDTO dto) {
        CategoriaViewDTO categoriaSalva = categoriaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaViewDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaViewDTO dto) {
        try{
            dto.setId(id);
            CategoriaViewDTO categoriaAtualizada = categoriaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.ATUALIZAR);
            return ResponseEntity.ok(categoriaAtualizada);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        try{
            categoriaBusiness.desativar(id);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        try{
            categoriaBusiness.ativar(id);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
