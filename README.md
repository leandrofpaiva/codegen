# Codegen 

## Gerado de código CRUD, baseado em Spring, com Restful e Spring-Data.

### Passos para utilizar o gerador:

O projeto é baseado em maven, o módulo codegen contem os templates Velocity que geram o código Spring

1 - Acessar a pasta do projeto **codegen** e executar o comando: 

	mvn install


	
1 - Criar o projeto a partir do Archetype Maven abaixo:

mvn archetype:generate -DarchetypeGroupId=br.com.codegen.archetypes -DarchetypeArtifactId=spring-template -- template de projeto Spring Restful

2 - Gerando as classes do sistema a partir do model.xml (Editar o model e configurar as entidades)

mvn br.com.codegen.plugins:spring-template:1.0:generate

3 - Compilar o projeto

mvn install

4 - Executar o projeto

cd target
java -jar arquivo.jar
