package com.onebrain.loja.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class TrilhaAuditavelListener {

    @PrePersist
    private void antesDeSalvar(TrilhaAuditavel auditavel){
        auditavel.setDataCriacao(new Date());
        auditavel.setDataUltimaAtualizacao(new Date());
        auditavel.setUsuarioAtualizacao("TESTE");
        auditavel.setUsuarioCriacao("TESTE");
    }

    @PreUpdate
    private void antesDeAtualizar(TrilhaAuditavel auditavel){
        auditavel.setDataUltimaAtualizacao(new Date());
        auditavel.setUsuarioAtualizacao("TESTE");
    }
}
