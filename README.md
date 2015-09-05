# Codegen 

#### Gerado de código CRUD, baseado em Spring, com Restful e Spring-Data.

##### Passos para utilizar o gerador:

O projeto é baseado em maven, o módulo codegen contem os templates Velocity que geram o código Spring

1 - Acessar a pasta do projeto **codegen** e executar o comando: 

	mvn install

2 - Acessar a pasta do plugin maven que será chamado para gerar os códigos baseado nas entidades mapeadas no arquivo *model.xml* que é gerado após a chamada do **Archetype**.

	mvn install

3 - Acessar a pasta do Archetype e executar o comando:

	mvn install
	
Pronto, nesse ponto todos os componentes do gerado de código estão compilados e prontos para uso

4 - Criar o projeto a partir do Archetype Maven abaixo:

	mvn archetype:generate -DarchetypeGroupId=br.com.codegen.archetypes -DarchetypeArtifactId=spring-template 

Uma vez gerado o archetype o próximo passo é gerar as classes do modelo, para isso acessar a pasta do projeto que acabou de ser criado e editar o arquivo **model.xml** e adicionar as entidades que serão geradas pelo plugin do codegen.

5 - Gerando as classes do sistema a partir do model.xml (Editar o model e configurar as entidades)

	mvn br.com.codegen.plugins:spring-template:1.0:generate

6 - Compilar o projeto gerado

	mvn install

7 - Executar o projeto

O projeto se conecta a uma base mysql, antes de executá-lo, editar as configurações de banco na pasta resource do Projeto, acertar o usuário e senha e executar o comando abaixo:

	mvn spring-boot:run
	
**Obs: ** O projeto não está 100% concluído e ainda tem algumas implementações para fazer, mas é possivel ter uma ideia do que pode ser feito com Velocity e recursos do Maven como Archetypes e plugins


