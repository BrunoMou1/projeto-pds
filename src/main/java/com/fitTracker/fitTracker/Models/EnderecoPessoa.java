package com.fitTracker.fitTracker.Models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Entity;

@Entity(name="endereco_pessoa")
public class EnderecoPessoa {
    @EmbeddedId
    private EnderecoPessoaKey id;

    @ManyToOne
    @MapsId("pessoaId")
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @MapsId("enderecoId")
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public EnderecoPessoa(Pessoa pessoa, Endereco endereco) {
        this.pessoa = pessoa;
        this.endereco = endereco;
        this.id = new EnderecoPessoaKey(pessoa.getId(), endereco.getId());
    }

    public EnderecoPessoa() {
    }

    public EnderecoPessoaKey getId() {
        return id;
    }

    public void setId(EnderecoPessoaKey id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
