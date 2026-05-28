//O Controller é quem vai receber a requisição do frontend (ex: quando o usuário entrar na tela de questões) e vai devolver a lista de questões em formato JSON.
package com.enade.questoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enade.questoes.model.Questao;
import com.enade.questoes.repository.QuestaoRepository;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api/questoes")
@CrossOrigin(origins = "*")// Permite que o frontend acesse o backend sem erros de CORS
public class QuestaoController {
    @Autowired
    private QuestaoRepository questaoRepository;

    // Instancia o leitor de JSON do Spring de forma segura
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Endpoint para listar todas as questões com suas alternativas, aceitando filtros e paginação automatica
    @GetMapping
    public ResponseEntity<Page<Questao>> listarTodas(
        @RequestParam(required = false) Integer ano,
        @RequestParam(required = false) String disciplina,
        @PageableDefault(page = 0, size = 20) Pageable pageable){
        
        Page<Questao> questoes = questaoRepository.buscarComFiltros(ano, disciplina, pageable);
        return ResponseEntity.ok(questoes);

    }
    
    @PostMapping("/{id}/responder")
    public ResponseEntity<com.enade.questoes.dto.GabaritoResultadoDTO> responderQuestao(
        @PathVariable("id") Long id,
        @RequestParam("letra") String letra){

        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questao não encontrada"));
        
        // Descobre qual é a alternativa correta na lista daquela questão
        String letraCorreta = "";

        // Lógica para ler a coluna JSONB do banco de dados de forma dinâmica
        try {
            // Transforma o texto da coluna alternativas em nós de árvore legíveis
            JsonNode alternativasJson = objectMapper.readTree(questao.getAlternativas());
            
            if (alternativasJson.isArray()) {
                for (JsonNode alternativa : alternativasJson) {
                    // Verifica se o campo "correta" é true dentro do JSON
                    if (alternativa.has("correta") && alternativa.get("correta").asBoolean()) {
                        letraCorreta = alternativa.get("letra").asText();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar as alternativas da questão", e);
        }
        
        boolean acertou = letraCorreta.equalsIgnoreCase(letra);

        com.enade.questoes.dto.GabaritoResultadoDTO resultado = new com.enade.questoes.dto.GabaritoResultadoDTO(
            acertou,
            letraCorreta,
            questao.getGabaritoComentado()
        );

        return ResponseEntity.ok(resultado);
        }

    
    @GetMapping("/simulado")
    public ResponseEntity<List<Questao>> gerarSimulado(
        @RequestParam(value = "disciplinas") List<String> disciplinas,
        @RequestParam("quantidade") int quantidade) {

    List<Questao> questoes = questaoRepository.buscarSimuladoAleatorio(disciplinas, quantidade);
    return ResponseEntity.ok(questoes);
    }
}

