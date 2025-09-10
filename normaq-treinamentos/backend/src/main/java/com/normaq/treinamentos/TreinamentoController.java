package com.normaq.treinamentos.controller;

import com.normaq.treinamentos.model.Treinamento;
import com.normaq.treinamentos.service.TreinamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/treinamentos")
@CrossOrigin(origins = "*")
public class TreinamentoController {
    
    @Autowired
    private TreinamentoService treinamentoService;
    
    @GetMapping
    public List<Treinamento> getTreinamentos() throws IOException {
        return treinamentoService.getTodosTreinamentos();
    }
    
    @GetMapping("/tecnico/{tecnico}")
    public List<Treinamento> getTreinamentosPorTecnico(@PathVariable String tecnico) throws IOException {
        return treinamentoService.getTreinamentosPorTecnico(tecnico);
    }
    
    @PostMapping
    public void addTreinamento(@RequestBody Treinamento treinamento) throws IOException {
        treinamentoService.addTreinamento(treinamento);
    }
    
    // Outros endpoints conforme necessário
}