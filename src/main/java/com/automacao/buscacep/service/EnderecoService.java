package com.automacao.buscacep.service;

import com.automacao.buscacep.exception.EnderecoNotFoundException;
import com.automacao.buscacep.exception.HttpRequestFailedException;
import com.automacao.buscacep.model.Endereco;
import com.automacao.buscacep.repository.EnderecoRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EnderecoService {

    private final HttpClient httpClient;
    private final Gson gson;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(HttpClient httpClient, Gson gson, EnderecoRepository enderecoRepository) {
        this.httpClient = httpClient;
        this.gson = gson;
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco getEnderecoByCep(String cep) {
        try {
            URI apiUrl = new URI("https://brasilapi.com.br/api/cep/v1/" + cep);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(apiUrl)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Endereco endereco = parseEnderecoJson(response.body());
                enderecoRepository.save(endereco);
                return endereco;
            } else if (response.statusCode() == 404) {
                throw new EnderecoNotFoundException("Endereço não encontrado para o CEP fornecido.");
            } else {
                throw new HttpRequestFailedException("Falha ao obter dados do endereço. Código de status: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao processar a requisição: " + e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro ao construir a URI da API: " + e.getMessage(), e);
        }
    }

    private Endereco parseEnderecoJson(String json) {
        return gson.fromJson(json, Endereco.class);
    }
}
