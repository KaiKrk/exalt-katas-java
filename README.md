# eXalt IT Java Dojo

Application Bank Account :

cette application effectue les opérations bancaire simple comme le depot et le retrait d'argent du compte d'un utilisateur préinscrit, la consultation de son compte et de ses transactions.

Technologies 
Architecture hexagonale

Languages : Java 8.
Framework : Spring, Hibernate, Maven, Log4j
Base de données : Postgresql.

Installation 

Base de données :

Script : 
création de la base : [bank_account_kata_schema.zip](https://github.com/KaiKrk/exalt-katas-java/files/9951234/bank_account_kata_schema.zip)
jeu de donnée : [bank_account_kata_datas.zip](https://github.com/KaiKrk/exalt-katas-java/files/9951232/bank_account_kata_datas.zip)

Pour generer l'executable de l'application effectuez un mvn package. 
Modifiez le fichier application.properties

```
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url = jdbc:postgresql://localhost:5432/bank_account_kata
spring.datasource.username = postgres
spring.datasource.password = 123456


```

## Test

pour tester l'application, allez dans la racine de la couche back ou batch et effectuez un mvn test



