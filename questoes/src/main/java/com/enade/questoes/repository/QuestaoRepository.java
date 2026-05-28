//O Repository é quem faz a ponte com o banco de dados.(O JPA já nos dá todos os metodos de CRUD)
package com.enade.questoes.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enade.questoes.model.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    
    // Query inteligente: se o parâmetro for nulo, ela ignora o filtro.
    // O retorno "Page" garante que os dados venham fatiados do banco como paginas.
    @Query("SELECT q FROM Questao q WHERE " +
       "(:ano IS NULL OR q.ano = :ano) AND " +
       "(:disciplina IS NULL OR LOWER(CAST(q.disciplina AS string)) LIKE LOWER(CONCAT('%', CAST(:disciplina AS string), '%')))")
    Page<Questao> buscarComFiltros(
        @Param("ano") Integer ano,
        @Param("disciplina") String disciplina,
        Pageable pageable);

    @Query(value = "SELECT * FROM questoes q WHERE disciplina IN (:disciplinas) ORDER BY RANDOM() LIMIT :quantidade", nativeQuery= true)
    List<Questao> buscarSimuladoAleatorio(
        @Param("disciplinas") List<String> disciplinas, 
        @Param("quantidade") int quantidade);
}
