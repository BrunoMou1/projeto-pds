package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface EnderecoPessoaRepository extends JpaRepository<EnderecoPessoa, Long> {

    public List<EnderecoPessoa> findByPessoaId(Long pessoaId);

    public Optional<EnderecoPessoa> findByPessoaIdAndEnderecoId(Long pessoaId, Long enderecoId);

    @Modifying
    @Transactional
    @Query("delete from endereco_pessoa ep where ep.pessoa.id = :pessoa_id and ep.endereco.id = :endereco_id")
    public int deleteByPessoaIdAndEnderecoId(@Param("pessoa_id") Long pessoaId, @Param("endereco_id") Long enderecoId);
}
