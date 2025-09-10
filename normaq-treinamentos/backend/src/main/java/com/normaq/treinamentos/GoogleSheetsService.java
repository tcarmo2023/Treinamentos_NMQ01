package com.normaq.treinamentos.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.normaq.treinamentos.model.Treinamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleSheetsService {
    
    @Autowired
    private Sheets sheetsService;
    
    private static final String SPREADSHEET_ID = "your-spreadsheet-id";
    private static final String RANGE = "Página1";
    
    public List<Treinamento> getTreinamentos() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
            .get(SPREADSHEET_ID, RANGE)
            .execute();
        
        List<List<Object>> values = response.getValues();
        List<Treinamento> treinamentos = new ArrayList<>();
        
        if (values == null || values.isEmpty()) {
            return treinamentos;
        }
        
        // Pular cabeçalho
        for (int i = 1; i < values.size(); i++) {
            List<Object> row = values.get(i);
            Treinamento treinamento = new Treinamento();
            // Mapear valores da planilha para o objeto Treinamento
            // Implementar conforme estrutura da sua planilha
            treinamentos.add(treinamento);
        }
        
        return treinamentos;
    }
    
    public void addTreinamento(Treinamento treinamento) throws IOException {
        List<Object> row = new ArrayList<>();
        row.add(treinamento.getTreinamento());
        row.add(treinamento.getClassificacaoTecnico());
        // Adicionar outros campos
        
        ValueRange body = new ValueRange()
            .setValues(Collections.singletonList(row));
        
        sheetsService.spreadsheets().values()
            .append(SPREADSHEET_ID, RANGE, body)
            .setValueInputOption("USER_ENTERED")
            .execute();
    }
}