package com.enade.questoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questoes")

public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//O banco gera os ids automaticamente
    private Long id;

    @Column(columnDefinition= "TEXT", nullable=false)//TEXT permite grandes enunciados
    private String enunciado;

    private Integer ano;
    @Column(name = "textoApoio", columnDefinition = "TEXT")
    private String textoApoio;
    @Column(columnDefinition="TEXT")
    private String disciplina;

    @Column(name = "gabaritoComentado", columnDefinition = "TEXT")
    private String gabaritoComentado;

    // Relacionamento 1 para Muitos: Uma questão tem várias alternativas
    // CascadeType.ALL garante que ao salvar uma Questão, as alternativas rodam juntas
    @Column(name = "alternativas", columnDefinition = "jsonb")
    private String alternativas;


}
