## Documentação para Configuração do MailHog com Docker e Configuração SMTP
 Esta documentação descreve como configurar o MailHog, um servidor SMTP de teste, usando Docker e como configurá-lo em um arquivo application.properties para capturar e visualizar e-mails durante o desenvolvimento.

### Passo 1: Configurar MailHog no Docker Compose
Crie ou edite o arquivo docker-compose.yml no seu projeto e adicione a seguinte configuração para o MailHog:

```
version: '3.8'

services:
    mailhog:
        image: mailhog/mailhog
        container_name: mailhog
        ports:
            - "1025:1025"  # SMTP
            - "8025:8025"  # Web UI
```

### Passo 2: Executar o Docker Compose
No terminal, navegue até o diretório onde o arquivo docker-compose.yml está localizado e execute o seguinte comando para iniciar o MailHog:

```
docker-compose up -d
```

Este comando inicia o MailHog em segundo plano. Você verá que os serviços definidos no docker-compose.yml estão em execução.

### Passo 3: Configurar o Arquivo application.properties
Edite o arquivo application.properties da sua aplicação para configurar o SMTP com MailHog:

```
spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=false
spring.mail.properties.mail.smtp.starttls.enable=false
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.test-connection=true
```

Estas configurações instruem sua aplicação a usar o servidor SMTP do MailHog para enviar e-mails.

### Passo 4: Verificar E-mails na Interface Web do MailHog
#### Acesse a Interface Web do MailHog:
Abra um navegador web e vá para http://localhost:8025. Esta é a interface web do MailHog onde você pode visualizar os e-mails capturados.

#### Verifique os E-mails Capturados:
Na interface web do MailHog, você verá uma lista de e-mails que foram enviados pela sua aplicação. Você pode clicar em qualquer e-mail para ver os detalhes.
