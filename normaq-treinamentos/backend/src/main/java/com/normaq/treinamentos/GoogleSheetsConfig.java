package com.normaq.treinamentos.model;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleSheetsConfig {
    
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    
    @Bean
    public Sheets sheetsService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        
        // Obter credenciais do ambiente (Netlify env vars)
        String credentialsJson = System.getenv("GOOGLE_CREDENTIALS");
        
        GoogleCredentials credentials;
        if (credentialsJson != null && !credentialsJson.isEmpty()) {
            credentials = GoogleCredentials.fromStream(
                new ByteArrayInputStream(credentialsJson.getBytes()))
                .createScoped(SCOPES);
        } else {
            throw new RuntimeException("Google credentials not found in environment variables");
        }
        
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
            .setApplicationName("Sistema Treinamentos NORMAQ")
            .build();
    }
}