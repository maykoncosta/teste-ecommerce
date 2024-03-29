package com.onebrain.loja.controller;

import com.onebrain.loja.business.impl.ProdutoBusinessImpl;
import com.onebrain.loja.dto.ProdutoViewDTO;
import com.onebrain.loja.enums.TipoOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoBusinessImpl produtoBusiness;

    @GetMapping
    public ResponseEntity<List<ProdutoViewDTO>> listarTodos() {
        List<ProdutoViewDTO> produtos = produtoBusiness.listarTodos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<ProdutoViewDTO>> listarTodosDesativados() {
        List<ProdutoViewDTO> produtos = produtoBusiness.listarTodosDesativados();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoViewDTO> buscarPorId(@PathVariable Long id) {
        ProdutoViewDTO produto = produtoBusiness.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ProdutoViewDTO> buscarPorCodigo(@PathVariable String codigo) {
        try {
            ProdutoViewDTO produto = produtoBusiness.buscarPorCodigo(codigo);
            return ResponseEntity.ok(produto);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoViewDTO> salvar(@RequestBody ProdutoViewDTO produtoViewDTO) {
        ProdutoViewDTO produtoSalvo = produtoBusiness.salvarOuAtualizar(produtoViewDTO, TipoOperacaoRepository.SALVAR);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoViewDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoViewDTO produtoViewDTO) {
        produtoViewDTO.setId(id);
        try{
            ProdutoViewDTO produtoAtualizado = produtoBusiness.salvarOuAtualizar(produtoViewDTO, TipoOperacaoRepository.ATUALIZAR);
            return ResponseEntity.ok(produtoAtualizado);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        try{
            produtoBusiness.desativar(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        try{
            produtoBusiness.ativar(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inicio-codigo/{inicioCodigo}")
    public ResponseEntity<List<ProdutoViewDTO>> buscarPorInicioCodigo(@PathVariable String inicioCodigo) {
        List<ProdutoViewDTO> produtos = produtoBusiness.buscarPorInicioCodigo(inicioCodigo);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/marca/{codigoMarca}")
    public ResponseEntity<List<ProdutoViewDTO>> buscarPorCodigoMarca(@PathVariable String codigoMarca) {
        List<ProdutoViewDTO> produtos = produtoBusiness.buscarPorCodigoMarca(codigoMarca);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/categoria/{codigoCategoria}")
    public ResponseEntity<List<ProdutoViewDTO>> buscarPorCodigoCategoria(@PathVariable String codigoCategoria) {
        List<ProdutoViewDTO> produtos = produtoBusiness.buscarPorCodigoCategoria(codigoCategoria);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}