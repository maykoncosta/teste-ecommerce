package com.onebrain.loja.controller;

import com.onebrain.loja.business.impl.MarcaBusinessImpl;
import com.onebrain.loja.dto.MarcaViewDTO;
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
    public ResponseEntity<List<MarcaViewDTO>> listarTodos() {
        List<MarcaViewDTO> marcas = marcaBusiness.listarTodos();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaViewDTO> buscarPorId(@PathVariable Long id) {
        MarcaViewDTO dto = marcaBusiness.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<MarcaViewDTO> buscarPorCodigo(@PathVariable String codigo) {
        MarcaViewDTO dto = marcaBusiness.buscarPorCodigo(codigo);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MarcaViewDTO> salvar(@RequestBody MarcaViewDTO dto) {
        MarcaViewDTO dtoSalvo = marcaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaViewDTO> atualizar(@PathVariable Long id, @RequestBody MarcaViewDTO dto) {
        dto.setId(id);
        MarcaViewDTO dtoAtualizado = marcaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.ATUALIZAR);
        return ResponseEntity.ok(dtoAtualizado);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        marcaBusiness.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        marcaBusiness.ativar(id);
        return ResponseEntity.noContent().build();
    }
}

