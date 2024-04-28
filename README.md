# busca_cep
## Automação para pesquisar informações sobre código de endereço postal (CEP).

### Requisitos Java 11 MySQL 8.0.26 ou superior

* Certifique-se de ter configurado corretamente o MySQL antes de executar o projeto.
Para configuração do Banco de Dados, antes de executar o projeto você precisará configurar o arquivo application.properties com as seguintes propriedades:

spring.datasource.url=jdbc:mysql://localhost/buscacep 

spring.datasource.username=seu_usuario 

spring.datasource.password=sua_senha 

spring.jpa.hibernate.ddl-auto=update 

spring.jpa.show-sql=true

logging.level.root=WARN

Substitua seu_usuario e sua_senha pelas credênciais do seu banco de dados MySQL. 
O nome do banco de dados é 'busca cep', mas você pode alterá-lo conforme necessário.


* Instalação e Uso
  
Clone este repositório. 
Navegue até o diretório do projeto. 


* Após a compilação, você pode executar o aplicativo Spring Boot usando o seguinte comando:
  
RUN 'BuscacepApplication' 


* Principais Dependências
  
Spring Boot Starter Data JPA

Spring Boot Starter Validation 

Spring Boot DevTools 

MySQL Connector Java 

Spring Boot Starter Web 

Gson 

Spring Boot Starter 

Test JUnit 

Mockito Core 


**Contribuições são bem-vindas!**

Sinta-se à vontade para abrir um problema ou enviar uma solicitação pull.

Autor

*Kelly Cristina*
