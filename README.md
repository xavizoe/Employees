# Employees
Test for IronBit

This is a sample Java / Maven / Spring Boot (version 2.7.0) application that can be used as for manage data employee. I hope it helps you.

## How to Run 

This application is packaged as ja  which has Netty 8 embedded. No Server is necessary. You run it using the mvn spring-boot:run

* Clone this repository : 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run by running mvn spring-boot:run

Once the application runs you should see something like this

```
2023-10-16 03:02:44.086  INFO 20392 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port 8080
2023-10-16 03:02:44.260  INFO 20392 --- [           main] com.ironbit.test.crud.CrudApplication    : Started CrudApplication in 3.526 seconds (JVM running for 3.957)

```

## About the Service

The service is just a simple CRUD employee REST service. It uses an in-memory database (H2) to store the data.
 
Here is what this little application demonstrates: 

Here are some endpoints you can call:

### Manage information about an emplpyee.

```
http://localhost:8080/api/saveEmployees
http://localhost:8080/api/findAll
http://localhost:8080/api/update/employee/{id}
http://localhost:8080/api/delete/employee/{id}
http://localhost:8080/api/getBitacora
```

### Create an employee

```
POST /example/api/saveEmployees
Accept: application/json
Content-Type: application/json

{
    "employees": [
        {
            "firstName": "Carolina",
            "secondName": "",
            "lastName": "Vixtha",
            "secondLastName": "Quiterio",
            "age": "30",
            "sex": "FEMALE",
            "birthDate": "12-02-1994",
            "job": "Dev"
        },
        {
            "firstName": "Francisco",
            "secondName": "Javier",
            "lastName": "Dominguez",
            "secondLastName": "Gomez",
            "age": "30",
            "sex": "MALE",
            "birthDate": "27-12-1992",
            "job": "Develop"
        }
    ]
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/saveEmployees
```

### Retrieve all employees

```
http://localhost:8080/api/findAll

Response: HTTP 200
Content:

[
    {
        "id": 1,
        "firstName": "Carolina",
        "secondName": "",
        "lastName": "Vixtha",
        "secondLastName": "Quiterio",
        "age": "30",
        "sex": "FEMALE",
        "birthDate": "12-02-1994",
        "job": "Dev"
    },
    {
        "id": 2,
        "firstName": "Francisco",
        "secondName": "Javier",
        "lastName": "Dominguez",
        "secondLastName": "Gomez",
        "age": "30",
        "sex": "MALE",
        "birthDate": "27-12-1992",
        "job": "Develop"
    },
    {
        "id": 3,
        "firstName": "Renata",
        "secondName": "Zoé",
        "lastName": "Dominguez",
        "secondLastName": "VIxtha",
        "age": "2",
        "sex": "FEMALE",
        "birthDate": "18-02-2023",
        "job": "Kid"
    }
]

```

### Update an employee

```
PUT http://localhost:8080/api/update/employee/3
Accept: application/json
Content-Type: application/json

{
    "id": 3,
    "firstName": "New first",
    "secondName": "",
    "lastName": "New lastName",
    "secondLastName": "Quiterio",
    "age": "30",
    "sex": "FEMALE",
    "birthDate": "12/02/1994",
    "job": "Dev"
}
RESPONSE: HTTP 200 (OK)
```

### Delete an employee

```
DELETE http://localhost:8080/api/delete/employee/3
Accept: application/json
Content-Type: application/json

{
    "message": "Employee deleted",
    "status": "NO_CONTENT"
}

RESPONSE: HTTP 200 (OK)
```

### Get bitacora

```
GET http://localhost:8080/api/getBitacora
Accept: application/json
Content-Type: application/json

[
    {
        "operation": "POST",
        "data": "[EmployeeDTO(id=1, firstName=Carolina, secondName=, lastName=Vixtha, secondLastName=Quiterio, age=30, sex=FEMALE, birthDate=12-02-1994, job=Dev), EmployeeDTO(id=2, firstName=Francisco, secondName=Javier, lastName=Dominguez, secondLastName=Gomez, age=30, sex=MALE, birthDate=27-12-1992, job=Develop), EmployeeDTO(id=3, firstName=Renata, secondName=Zoé, lastName=Dominguez, secondLastName=VIxtha, age=2, sex=FEMALE, birthDate=18-02-2023, job=Kid)]"
    },
    {
        "operation": "GET",
        "data": "[Employee(id=1, firstName=Carolina, secondName=, lastName=Vixtha, secondLastName=Quiterio, age=30, sex=FEMALE, birthDate=12-02-1994, job=Dev), Employee(id=2, firstName=Francisco, secondName=Javier, lastName=Dominguez, secondLastName=Gomez, age=30, sex=MALE, birthDate=27-12-1992, job=Develop), Employee(id=3, firstName=Renata, secondName=Zoé, lastName=Dominguez, secondLastName=VIxtha, age=2, sex=FEMALE, birthDate=18-02-2023, job=Kid)]"
    },
    {
        "operation": "PUT",
        "data": "Employee(id=3, firstName=New first, secondName=, lastName=New lastName, secondLastName=Quiterio, age=30, sex=FEMALE, birthDate=12/02/1994, job=Dev)"
    },
    {
        "operation": "DELETE",
        "data": "EmployeeDTO(id=3, firstName=New first, secondName=, lastName=New lastName, secondLastName=Quiterio, age=30, sex=FEMALE, birthDate=12/02/1994, job=Dev)"
    }
]

RESPONSE: HTTP 200 (OK)
```

### To view Swagger 2 API docs

Run the server and browse to localhost:8080/swagger-ui.html

# Questions and Comments: fcoxavi27@hotmail.com
Developed by: Francisco Javier Domínguez Gómez
