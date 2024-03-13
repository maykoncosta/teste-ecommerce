package com.onebrain.loja.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class TrilhaAuditavel {

    @Column(nullable = false, name = "data_criacao")
    private Date dataCriacao;

    @Column(nullable = false, name = "data_ultima_atualizacao")
    private Date dataUltimaAtualizacao;

    @Column(nullable = false, name = "usuario_criacao", length = 100)
    private String usuarioCriacao;

    @Column(nullable = false, name = "usuario_atualizacao",length = 100)
    private String usuarioAtualizacao;

}
