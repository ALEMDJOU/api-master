# 💬 Yowyob Feedback · REST API

Backend of **Yowyob Feedback**, a platform where a company's interns share feedback about their internship and where administrators validate feedback and achievements before publishing them.

![Java](https://img.shields.io/badge/Java_8-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_2.5-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Apache Cassandra](https://img.shields.io/badge/Apache_Cassandra-1287B1?style=flat-square&logo=apachecassandra&logoColor=white)
![Swagger](https://img.shields.io/badge/OpenAPI-85EA2D?style=flat-square&logo=swagger&logoColor=black)

> Frontend: [yowyob_feedback_frontend](https://github.com/ALEMDJOU/yowyob_feedback_frontend) · [Live demo](https://yowyob-feedback-frontend.vercel.app)

---

## ✨ Features

- **Members**: registration, profile update, admin role management
- **Feedback**: interns submit feedback, admins mark it as *valid*, *not valid* or *waiting*
- **Achievements**: publication and moderation of interns' achievements
- **Interactive API documentation** with Swagger UI (springdoc-openapi)

## 🧱 Architecture

```
src/main/java/boms/yowyobFeedBack/
├── controller/    # REST endpoints (Member, Feedback, Achievement)
├── model/         # Cassandra entities
├── repository/    # Spring Data Cassandra repositories
└── YowyobFeedBackApplication.java
cassandra_database/  # Keyspace schema and sample data (CSV)
```

## 🔌 Main endpoints

Base path: `/yowyob-feedback/api`

| Resource | Endpoints |
|---|---|
| Members | `POST /member` · `GET /members` · `GET /member/{id}` · `PUT /member/{id}` · `DELETE /member/{id}` · `PUT /member/admin/{id}` · `PUT /member/notAdmin/{id}` |
| Feedback | `POST /feedback` · `GET /feedbacks` · `GET /feedback/{id}` · `DELETE /feedback/{id}` · `PUT /feedback/valid/{id}` · `PUT /feedback/notvalid/{id}` · `PUT /feedback/waiting/{id}` |
| Achievements | `POST /achievement` · `GET /achievements` · `GET /achievement/{id}` · `PUT /achievement/{id}` · `DELETE /achievement/{id}` · `PUT /achievement/valid/{id}` · `PUT /achievement/notvalid/{id}` |

## 🚀 Getting started

**Prerequisites:** JDK 8+, Maven, Apache Cassandra running on `127.0.0.1:9042`.

1. Create the `yowyob_feedback_ks` keyspace and its tables (see `cassandra_database/`).
2. Run the API:

```bash
git clone https://github.com/ALEMDJOU/api-master.git
cd api-master
./mvnw spring-boot:run
```

3. Open the documentation: <http://localhost:8080/yowyob-feedback/swagger-ui-yowyob_feedback.html>

Configuration lives in `src/main/resources/application.properties` (Cassandra host, port, keyspace and server port).

---

👤 **Henri Joël Fofack Alemdjou** · [Portfolio](https://portfoliofofackhenri.vercel.app/) · [LinkedIn](https://linkedin.com/in/henri-fofack-250b1b320)
