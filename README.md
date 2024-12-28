# Bulletin Board

- Java 17
- Spring Boot
- Maven
- Rest API
- Spring Data JPA
- Lombok
- Spring Validation
- Spring Security
- PostgreSQL
- SB Admin 2


![image](https://github.com/user-attachments/assets/9e17bdc3-ef07-4821-945b-2d056be1b1f0)


![image](https://github.com/user-attachments/assets/3e1b777d-7989-464d-bfa0-64b18713cdf9)

## How to run the application

### 1. **Clone the source code**

```
git clone https://github.com/n0tx/bulletin-board.git
```

**Information**:

```                       
┌──[~]
└─$ git clone https://github.com/n0tx/bulletin-board.git

┌──[~]
└─$ cd bulletin-board 

┌──[~/bulletin-board]
└─$ pwd
/home/n0tx/bulletin-board
```

### 2. **Creating a postgreSQL database**

```
create database bulletin_board  with owner = springboot;
```

**Information**:

```                       
┌──[~]
└─$ sudo -u postgres psql 

postgres=# create database bulletin_board  with owner = springboot;
CREATE DATABASE
```

### 3. **Set application.properties**
```
# datasource initialization
spring.datasource.url=jdbc:postgresql://localhost/bulletin_board
spring.datasource.username=springboot
spring.datasource.password=springboot
spring.sql.init.platform=postgres
```

**Information**:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/{{database_name}}
spring.datasource.username={{username_database}}
spring.datasource.password={{password_database}}
spring.sql.init.platform=postgres

Customize with your database settings
```

### 4. **Running applications**

```
./mvnw spring-boot:run
```

**Information**:

```
┌──[~]
└─$ java --version
java 17.0.5 2022-10-18 LTS
Java(TM) SE Runtime Environment (build 17.0.5+9-LTS-191)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.5+9-LTS-191, mixed mode, sharing)

┌──[~]
└─$ cd bulletin-board 

┌──[~/bulletin-board]
└─$ pwd
/home/n0tx/bulletin-board

┌──[~/bulletin-board]
└─$ ls
mvnw  mvnw.cmd  pom.xml  README.md  src  target

┌──[~/bulletin-board]
└─$ ./mvnw spring-boot:run

```

### 5. **Open Applications**

```
http://localhost:8080
```
