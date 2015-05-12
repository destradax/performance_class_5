# Clase 5

## Ejercicio 1
Implementar la entidad de usuario y de asistencia a películas que trabajamos en SQL Server y los controladores REST para hacer su CRUD.

- All users: http://localhost:8080/rest/users
- User by id: http://localhost:8080/rest/users/1


- All movie assistances: http://localhost:8080/rest/assistance
- Movie assistance by id: http://localhost:8080/rest/assistance/1

## Ejercicio 2
Usar un caché para obtener todos los usuarios y un usuario por id. El caché debe funcionar de manera manual, es decir, sin la abstracción de Spring.

- ehcache.xml

## Ejercicio 3
Realizar un servicio que entrega un usuario y las películas a las que ha asistido. El query debe obtener el user y la colección de asistencia de manera eficiente (un solo query). Debe usarse JPA y solo obtener el id y el nombre del usuario.

- http://localhost:8080/rest/users/1/movies

## Ejercicio 4
Crear un Script con JDBC que llene de 500 usuarios el sistema. Hacerlo usando estrategias eficientes de manejo de conexiones.

- InsertUsers.java
