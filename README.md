# WorkAcademy
# Student register REST API
## General assumptions
The goal of the task is to create a basic REST API that collects information about students.
The API should give the possibility to perform [CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) operations.

As an additional feature, the REST API should return results about the current collection:

- the current amount of all students
- the current amount of all students for a particular course
- filtering students by name & surname pattern
- filtering students by course name
- all students
- an average student's age

## Examples of HTTP bodies (data models accepted by the API)

#### Student
```
{
  "student": {
    "name": "John",
    "surname": "Doe",
    "dateBirth": "1995-05-10",
    "course": "jjdzr9"
  }
}
```
:warning: *There should be a possibility to create a couple of students at one request*

#### Result - students filtering
```
{
  "result": {
    "students": [
      {
        "name": "John",
        "surname": "Doe",
        "dateBirth": "1995-05-10",
        "course": "jjdzr8"
      },
      {
        "name": "Jane",
        "surname": "Smith",
        "dateBirth": "1998-08-15",
        "course": "jjdzr8"
      }
    ]
  },
  "reportDate": "2023-06-16"
}
```

#### Result - simple report (age average/amount of students)
```
{
  "result": "45",
  "reportDate": "2023-06-16"
}
```

## Non-functional requirements:

- the application should be created using Java 17, Spring Boot, and Maven
- entire code should be stored on a private GitHub repository
- create a 'main' branch, the implementation should be created on a 'feature' branch
- the finished task should be provided as a Pull Request, from a 'feature' branch to the 'main'
- the commits should show the entire process of creation. Names should be meaningful. One commit is prohibited
- the application should have at least one unit test, and one integration test
- the implementation should be created to align with best practices (REST API, JAVA), and clean code principles
- errors should be properly handled and logged
- the application must pass the maven builds processes (from clean to install)
- database is not necessary

#### Optional:

- Swagger
- A Dockerfile to build an image of the application

