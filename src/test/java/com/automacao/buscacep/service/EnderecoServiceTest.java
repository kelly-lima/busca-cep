package com.automacao.buscacep.service;

import com.automacao.buscacep.exception.EnderecoNotFoundException;
import com.automacao.buscacep.exception.HttpRequestFailedException;
import com.automacao.buscacep.model.Endereco;
import com.automacao.buscacep.repository.EnderecoRepository;
import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private EnderecoRepository enderecoRepository;

    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        enderecoService = new EnderecoService(httpClient, new Gson(), enderecoRepository);
    }

    @Test
    public void testGetEnderecoByCep_Success() throws Exception {

        String cep = "12345678";
        Endereco expectedEndereco = new Endereco("12345678", "PE", "Cidade Teste", "Bairro Teste", "Rua Teste");

        // Mock da resposta HTTP
        HttpResponse<String> successResponse = mock(HttpResponse.class);
        when(successResponse.statusCode()).thenReturn(200);
        when(successResponse.body()).thenReturn("{\"cep\":\"12345678\",\"uf\":\"PE\",\"cidade\":\"Cidade Teste\",\"bairro\":\"Bairro Teste\",\"rua\":\"Rua Teste\"}");

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(successResponse);

        Endereco endereco = enderecoService.getEnderecoByCep(cep);

        assertEquals(expectedEndereco, endereco);
    }

    @Test
    public void testGetEnderecoByCep_NotFound() throws Exception {

        String cep = "00000000";

        // Mock da resposta HTTP indicando que o CEP não foi encontrado
        HttpResponse<String> notFoundResponse = mock(HttpResponse.class);
        when(notFoundResponse.statusCode()).thenReturn(404);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(notFoundResponse);


        assertThrows(EnderecoNotFoundException.class, () -> enderecoService.getEnderecoByCep(cep));
    }

    @Test
    public void testGetEnderecoByCep_HttpRequestFailed() throws Exception {

        String cep = "12345678";

        // Mock da resposta HTTP indicando uma falha na requisição
        HttpResponse<String> failedResponse = mock(HttpResponse.class);
        when(failedResponse.statusCode()).thenReturn(500);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(failedResponse);

        assertThrows(HttpRequestFailedException.class, () -> enderecoService.getEnderecoByCep(cep));
    }

}


