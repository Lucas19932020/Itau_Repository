# USCS/Itaú Project
<p align="center">
<img alt="CaseItau" src="https://github.com/Lucas19932020/Itau_Repository/blob/master/Images/Ita%C3%BA%20image.PNG" width="400" />
</p>

Used technologies in the project:

- SpringBoot
  - Web
  - DevTools
  - Lombok
  - SpringData Cassandra
  - Spring Kafka

- Database
  - Cassandra
 
- Docker
  - Cassandra
  - Kafka
  - Zookeeper

## Steps to install

**1. Clone the repository**

```bash
https://github.com/Lucas19932020/Itau_Repository
```

**2. Install Docker**

Site to download [Docker](https://docs.docker.com/get-docker/).

**3. Install and configure *Cassandra***

  **3.1. Pull the repository from *Cassandra***

  `docker pull datastax/dse-server:5.1.18`

  **3.2. To create a *Cassandra* conteiner, execute the command:**
  
  `docker run -e DS_LICENSE=accept --memory 4g --name cassandra -p 9042:9042 -d datastax/dse-server:5.1.18`

  **3.3. To copy the file *cassandra.yaml* inside the container, execute the command:**
 
  `docker cp <FILE_CASSANDRA> cassandra:/opt/dse/resources/cassandra/conf/`

  Obs.: Replace the ***<FILE_CASSANDRA>*** through the file directory *cassandra.yaml*, which is located in the project repository `"_/uscsitau/src/main/resources/config/cassandra.yaml_"`.

  **3.4. Stop and Start the container *Cassandra***
  
  `docker stop cassandra`

  `docker start cassandra`

  **3.5. To configure and create the tables, run the command:**
  
  `docker exec -it cassandra bash`

  **3.5.1. To log in *Cassandra*, run the command:**
  
  `cqlsh -u cassandra -p cassandra`

  **3.5.2. To create an user**

  `CREATE ROLE root with SUPERUSER = true AND LOGIN = true and PASSWORD = 'root';`

  **3.5.3. Create the Keyspace**

  `CREATE KEYSPACE dbo WITH REPLICATION = {'class': 'SimpleStrategy','replication_factor' : 1};`

  `USE dbo;`

  **3.5.1. Create the tables**

```bash
  CREATE TABLE cliente (
      nome VARCHAR,
      cpf_cnpj VARCHAR PRIMARY KEY,
      tipo_de_cliente VARCHAR,
      endereco VARCHAR,
      renda DOUBLE,
      razao_social VARCHAR,
      incr_estadual VARCHAR,
      num_conta VARCHAR
  );
```
```bash
  CREATE TABLE conta (
      num_conta VARCHAR PRIMARY KEY,
      agencia VARCHAR,
      dac INT,
      saldo DOUBLE
  );
```
```bash
  CREATE TABLE historico (	
      id UUID PRIMARY KEY,
      num_conta VARCHAR,
      tipo_de_transacao VARCHAR,
      data TIMESTAMP,
      status INT
  );
```

**4. Install and configure the *Kafka* and *Zookeeper***

  **4.1. Clone the repository that contains the *Kafka* and the *Zookeeper***

  `git clone https://github.com/confluentinc/cp-docker-images`

  **4.2. After cloned, navigate to the folder cp-docker-images/examples/kafka-single-node and run the command:**
  
  `docker-compose up -d`

  **4.3. To list the Kafka and Zookeeper services, run the command:**
  
  `docker-compose ps`

  **4.4. To create a Topic in Kafka, execute the command:**

  ```bash
    docker-compose exec kafka  \
    kafka-topics --create --topic bank-listener --partitions 3 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181
  ```
    
  **4.5. To validate that the Topic was created, run the command:**

  ```bash
  docker-compose exec kafka  \
      kafka-topics --describe --topic bank-listener  --zookeeper zookeeper:2181
  ```

**5. Import the project into the IDE**

Execute the class `StartApplication`.

## Routing

```bash
  How to Register a Client.
  POST    http://localhost:8080/clientes/salvar
 
  Register the client's informations as the image below:
  
  <p align="center">
  <img alt="Post Model Example" src="https://github.com/Lucas19932020/Itau_Repository/blob/master/Images/Images%20Case%20Itau%20-%20USCS%20Readme/1.%20Post%20Model%20Example.PNG" width="400" />
  </p>
  
  Registration Examples:
  
  <p align="center">
  <img alt="Registering a client Example" src="https://github.com/Lucas19932020/Itau_Repository/blob/master/Images/Images%20Case%20Itau%20-%20USCS%20Readme/2%20Registering%20a%20new%20client.PNG" width="400" />
  <img alt="Registering a new client" src="https://github.com/Lucas19932020/Itau_Repository/blob/master/Images/Images%20Case%20Itau%20-%20USCS%20Readme/3%20Registering%20a%20client%20Example.PNG" width="400" />
  </p>
  
  
```bash  
  GET     http://localhost:8080/clientes/lista
``` 
