package com.enade.questoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enade.questoes.model.Usuario;
import com.enade.questoes.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") 
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Rota de cadastro do usuario
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario){
        //Verifica se o email já está no banco
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioExistente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        }

        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    //Rota de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario dadosLogin){
        //Busca do user pelo email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(dadosLogin.getEmail());
        if(usuarioOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } 

        Usuario usuario = usuarioOpt.get();

        //Teste de incoerencia de senha com o banco
        if(!usuario.getSenha().equals(dadosLogin.getSenha())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }

        return ResponseEntity.ok(usuario);
    }

    // ROTA DE ADMINISTRADOR PARA LISTAR TODOS OS USUÁRIOS NO POSTMAN
    @GetMapping("/listar-todos-usuarios")
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
            return ResponseEntity.ok(usuarios);
}
}
