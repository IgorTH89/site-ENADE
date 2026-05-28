package com.enade.questoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enade.questoes.model.Comentario;
import com.enade.questoes.repository.ComentarioRepository;


@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    //METODO DE LISTAGEM/VISUALIZAÇÃO DO COMENTÁRIO
    @GetMapping("/questao/{questaoId}")
    public ResponseEntity<List<Comentario>> listarPorQuestao(@PathVariable Long questaoId) {
        List<Comentario> comentarios = comentarioRepository.findByQuestaoIdOrderByDataCriacaoDesc(questaoId); 
        return ResponseEntity.ok(comentarios);  
    }

    //METODO DE CADASTRO DO COMENTÁRIO
    @PostMapping
    public ResponseEntity<Comentario> criarComentario(@RequestBody Comentario comentario){
        Comentario novoComentario = comentarioRepository.save(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoComentario);
    }
    
}
