package com.automacao.buscacep;


import com.automacao.buscacep.model.Endereco;
import com.automacao.buscacep.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class BuscacepApplication implements CommandLineRunner {
	private final EnderecoService enderecoService;

	@Autowired
	public BuscacepApplication(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BuscacepApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				System.out.print("Digite o CEP desejado (ou 'sair' para encerrar): ");
				String input = reader.readLine().trim();

				if (input.equalsIgnoreCase("sair")) {
					System.out.println("Encerrando o programa. Fim!");
					break;
				}

				if (!input.matches("\\d{8}")) {
					System.out.println("Formato de CEP inválido. O CEP deve conter exatamente 8 dígitos numéricos.");
					continue;
				}

				Endereco endereco = enderecoService.getEnderecoByCep(input);

				if (endereco == null) {
					System.out.println("CEP não encontrado.");
				} else {
					System.out.println("Dados do endereço para o CEP " + input + ":");
					System.out.println("CEP: " + endereco.getCep());
					System.out.println("UF: " + endereco.getState());
					System.out.println("Cidade: " + endereco.getCity());
					System.out.println("Vizinhança: " + endereco.getNeighborhood());
					System.out.println("Rua: " + endereco.getStreet());
					System.out.println("=================================================================");
				}
			}
		} catch (IOException e) {
			System.err.println("Erro de entrada/saída: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro ao buscar o endereço: " + e.getMessage());
		}
	}
}
