# PetStore API Testing Project

This is a **automation project** for the [PetStore API](https://petstore.swagger.io/), built as a demonstration of best practices in API test automation.

It is fully developed and maintained by **Jakub Redzinski** as a personal intellectual property.

---

## Tech Stack

-  Java 21
-  Spring Boot
-  REST Assured
-  Cucumber BDD
-  JUnit 5
- Allure Reports
-  Maven
-  Jenkins

---

## Project Structure
<pre><code> rest-assured-sample-project
├── Jenkinsfile
├── pom.xml
├── README.md
└── src
├── main
│ └── java
│ └── rest.assured.spring.project.demo
│ ├── clients # API clients per endpoint (e.g., PetClient, OrderClient)
│ ├── config # Property loaders & configuration
│ ├── context # Scenario-scoped objects (PetContext, OrderContext)
│ ├── model
│ │ ├── order
│ │ └── pet
│ └── utils # Common utils (parsers, helpers)
└── test
├── java
│ └── rest.assured.spring.project.demo
│ ├── stepDefinition
│ │ ├── common
│ │ ├── order
│ │ └── pet
│ └── runner # Cucumber test runner
└── resources
└── features
├── order
└── pet
</code></pre>
---
## How to Run Tests

### Run all regression tests

```bash
mvn clean test -Dcucumber.filter.tags="@regression"
```

### Run only smoke tests

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Run specific test by tag combination
```bash
mvn test -Dcucumber.filter.tags="@order and @negative"
```

---

## Allure Reporting
### Generate the Allure report locally

```bash
allure serve target/allure-results
```
If Allure is integrated with Jenkins, the report will be generated automatically after the pipeline completes.


---
## Jenkins Integration

### This project includes a Jenkinsfile for CI execution.

Run tests with specified tag (e.g. @smoke)

#### Example execution:

```bash
mvn test -Dcucumber.filter.tags="${TAG}"
```

---

## Author
### Jakub Redzinski
[LinkedIn – Jakub Redzinski](https://www.linkedin.com/in/kuba-redzinski)
