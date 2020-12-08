Retail Store Discount API
---

### Introduction

Retail Store Discount API is a simple project that is calculating discounts for different types of users, following specific Discount rules:

  - User of type "Employee" have 30% discount on the bill
  - User of type "Affiliate" have 10% discount on the bill
  - User of type "Loyalty" (or simple user), who is registered for over 2 years, is eligible for 5% discount on the bill
  - If the order contains groceries, no percentage discount is available.
  - User, who is eligible to several discounts, can take only one (with biggest percent).
  - There is another type of discount which is based on amount, not on the user role, as follows: for every 100 on the bill, there will be 5 discount, e.g. bill 990, 45 is the discount




### Technologies

Retail Store Discount uses a number of open source projects to work properly:

* [Java](https://www.java.com/en/) - is a class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
* [Maven](https://maven.apache.org/) - is a software project management and comprehension tool.
* [Spring boot](https://spring.io/projects/spring-boot) - makes it easy to create stand-alone, production-grade Spring based Applications.
* [Restful APIs](https://restfulapi.net/) - is architectural style for distributed hypermedia systems.
* [Hibernate JPA](https://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/html_single/) - is a framework for mapping an object-oriented domain model to a relational database.
* [postgresql](https://www.postgresql.org/) -  is a powerful, open source object-relational database system.
* [Docker](https://www.docker.com/) - is a set of platform as a service (PaaS) products that use OS-level virtualization.
* [Docker Compose](https://docs.docker.com/compose/) - is a tool for running multi-container applications on Docker.



And of course Retail Store Discount itself is open source with a [public repository](https://github.com/FadyAlfred/retail-store)
 on GitHub.

### Install and run



```sh
$ Clone the repository
$ go into the project folder and execute docker-compose up --build
$ The app should be up and running on localhost:8080
```








### Demo Data

Currently we do not have authentication system, So you should provide one of the users ids below 
```
5906873d-3507-4995-afbe-b731bd9121d1, Fady, Employee
17b034e8-d9d7-4b0e-826d-c5fa756110bb, John, Affiliate
d54f9b8a-4463-4859-8487-3e1aa836f62b, Maria, Loyalty (user for more than 2 years)
cceb42a0-9a27-47f8-bf4d-6c2362a56e6b, Remoon, New user
```


### Sample request flow
- Calculate discount for the logged in user
```sh
Request
POST 
'http://127.0.0.1:8080/bill/payable' 
--header 'Authorization: cceb42a0-9a27-47f8-bf4d-6c2362a56e6b'
--header 'Content-Type: application/json' 
'{
    "items": [
        {"productId": "698047e1-3f95-49bc-bdfa-1611276c12ab", "quantity": 2},
        {"productId": "aa66ff2f-acef-4bcc-ae9a-5a9a592dabf9", "quantity": 1}
    ]
}'
```
```
Response: success
{
    "amount": 127
}
```
```
Response: fail
{
    "errors": [
        {
            "error": "InvalidBill",
            "error_description": "invalid items"
        }
    ]
}
```
### UML Class Diagram
- [Go to the first diagram](https://drive.google.com/file/d/1vavLEQKj8yQuCMZzH5QhlEfhJXtdE575/view)
- [Go to the second diagram](https://drive.google.com/file/d/1-GRpeQeP8KitHsiosDflK2IZ7EKk3sXM/view)



### Todos

 - Writ Tests Cases
 - Replace current Auth system with OpenIdConnect or OAuth2





   