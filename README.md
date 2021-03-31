# spring-jpa-thymeleaf

Un esempio di applicazione Thymeleaf + SpringBoot con Liste oppure H2 o JPA per la persistenza dei dati.

Tecnologie usate: 
- IDE: Spring Tool Suite 4.1.2.RELEASE oppure Eclipse IDE Version: 2020-09 + Plugin di STS
- Database H2 oppure Mysql
- Docker

Il progetto può essere importato come MavenProject.

Tutte le configurazioni sono inserite nel file "application.yml", in cui si può scegliere se utilizzare per la persistenza dei dati delle semplici Liste
oppure un database: in questo caso il profilo viene scelto tra i database h2 o mysql.
Il file "messages_it.properties" contiene delle stringhe che possono essere lette direttamente dalle view.html.

Il database di riferimento è H2 oppure MySQL, si può creare un nuovo database "players" con dentro una tabella "player" manualmente tramite un client come MySqlWorkbench, oppure tramite tool Docker-Desktop su Windows si crea il container contenente il database: MySqlWorkbench classico: dal tool creare una nuova connessione "127.0.0.1:3308" sotto cui creare il database.

MySQL dockerizzato => Lanciare in una cartella vuota il file "docker-compose.yml" che si trova sotto src/main/resources. 
Questi creerà due nuove sotto-cartelle, "config" e "data" che rappresentano due volumi, rispettivamente le configurazioni di Mysql e i dati memorizzati. 
Su Docker-Desktop verrà installato il nuovo container, avviare l'immagine che contiene il database MySQL "players". 
Entrare da MySqlWorkbench sotto l'hostname 'http://127.0.0.1:3308' oppure dopo aver avviato l'applicazione Java da browser andare su 'http://localhost:8080' 
per entrare nel database 'players' con parametri => 
Server: mysql-db
utente: root
password: capone
Database: players

Info su come si crea un progetto con Thymeleaf qui:
https://www.baeldung.com/thymeleaf-in-spring-mvc
https://www.baeldung.com/spring-boot-crud-thymeleaf
 