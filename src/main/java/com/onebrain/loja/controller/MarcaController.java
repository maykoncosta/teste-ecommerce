package com.onebrain.loja.controller;

import com.onebrain.loja.business.impl.MarcaBusinessImpl;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import com.onebrain.loja.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaBusinessImpl marcaBusiness;

    @GetMapping
    public ResponseEntity<List<Marca>> listarTodos() {
        List<Marca> marcas = marcaBusiness.listarTodos();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        Marca marca = marcaBusiness.buscarPorId(id);
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Marca> buscarPorCodigo(@PathVariable String codigo) {
        Marca marca = marcaBusiness.buscarPorCodigo(codigo);
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    public ResponseEntity<Marca> salvar(@RequestBody Marca marca) {
        Marca marcaSalva = marcaBusiness.salvarOuAtualizar(marca, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(marcaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id, @RequestBody Marca marca) {
        marca.setId(id);
        Marca marcaAtualizada = marcaBusiness.salvarOuAtualizar(marca, TipoOperacaoRepository.ATUALIZAR);
        return ResponseEntity.ok(marcaAtualizada);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        Marca marca = marcaBusiness.buscarPorId(id);
        marcaBusiness.desativar(marca);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        Marca marca = marcaBusiness.buscarPorId(id);
        marcaBusiness.ativar(marca);
        return ResponseEntity.noContent().build();
    }
}

