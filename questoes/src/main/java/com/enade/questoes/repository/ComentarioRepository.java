package com.enade.questoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enade.questoes.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    // Busca todos os comentários de uma questão específica ordenando pelos mais recentes
    List<Comentario> findByQuestaoIdOrderByDataCriacaoDesc(Long questaoId);
}
