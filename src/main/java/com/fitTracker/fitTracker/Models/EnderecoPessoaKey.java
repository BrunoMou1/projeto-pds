package com.fitTracker.fitTracker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnderecoPessoaKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private Long pessoaId;

    @Column
    private Long enderecoId;

    public EnderecoPessoaKey() {
    }

    public EnderecoPessoaKey(Long pessoaId, Long enderecoId) {
        this.pessoaId = pessoaId;
        this.enderecoId = enderecoId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    @Override
    public int hashCode(){
        return Objects.hash(enderecoId, pessoaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnderecoPessoaKey other = (EnderecoPessoaKey) obj;
        return Objects.equals(enderecoId, other.enderecoId) && Objects.equals(pessoaId, other.pessoaId);
    }
}
