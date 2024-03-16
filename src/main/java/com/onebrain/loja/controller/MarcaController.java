package com.onebrain.loja.controller;

import com.onebrain.loja.business.impl.MarcaBusinessImpl;
import com.onebrain.loja.dto.MarcaViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
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

    @GetMapping("/desativados")
    public ResponseEntity<List<MarcaViewDTO>> listarTodosDesativados() {
        List<MarcaViewDTO> marcas = marcaBusiness.listarTodosDesativados();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaViewDTO> buscarPorId(@PathVariable Long id) {
        try{
            MarcaViewDTO dto = marcaBusiness.buscarPorId(id);
            return ResponseEntity.ok(dto);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<MarcaViewDTO> buscarPorCodigo(@PathVariable String codigo) {
        try{
            MarcaViewDTO dto = marcaBusiness.buscarPorCodigo(codigo);
            return ResponseEntity.ok(dto);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MarcaViewDTO> salvar(@RequestBody MarcaViewDTO dto) {
        MarcaViewDTO dtoSalvo = marcaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(dtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaViewDTO> atualizar(@PathVariable Long id, @RequestBody MarcaViewDTO dto) {
        try{
            dto.setId(id);
            MarcaViewDTO dtoAtualizado = marcaBusiness.salvarOuAtualizar(dto, TipoOperacaoRepository.ATUALIZAR);
            return ResponseEntity.ok(dtoAtualizado);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        try{
            marcaBusiness.desativar(id);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        try{
            marcaBusiness.ativar(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }
}

