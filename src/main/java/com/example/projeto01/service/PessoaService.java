package com.example.projeto01.service;

import com.example.projeto01.DTO.PessoaRequest;
import com.example.projeto01.model.Pessoa;
import com.example.projeto01.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvar(PessoaRequest pessoaRequest) {
        if (pessoaRequest.getNome() == null || pessoaRequest.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

     Pessoa pessoa = new Pessoa();
     pessoa.setNome(pessoaRequest.getNome());
     pessoa.setIdade(pessoaRequest.getIdade());
     pessoa.setSenha(pessoaRequest.getSenha());
     return pessoaRepository.save(pessoa);

    }
    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
    }

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    public Pessoa atualizar(Long id, PessoaRequest pessoaRequest) {
        Pessoa pessoaExistente = buscarPorId(id);
        pessoaExistente.setNome(pessoaRequest.getNome());
        pessoaExistente.setIdade(pessoaRequest.getIdade());
        return pessoaRepository.save(pessoaExistente);
    }

    public String deletar(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw  new RuntimeException("Pessoa com ID " + id + " não encontrada.");
        }


        pessoaRepository.deleteById(id);
        return "Pessoa com ID " + id + " foi deletada com sucesso!";
    }




}
