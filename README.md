GitHub Access Report Service

## **Overview**

This Spring Boot application connects to the GitHub API and generates a **structured report** showing which users have access to which repositories in a given organization.
It aggregates contributor data and exposes it via a **REST API endpoint**.

---

## **Tech Stack**

* **Java 17**
* **Spring Boot 3**
* **Maven**
* **GitHub REST API**
* **JSON** for API responses

---

## **Authentication**

* Uses a **GitHub Personal Access Token (PAT)**.
* Token is stored in `src/main/resources/application.properties`:

```properties
github.token=YOUR_PERSONAL_ACCESS_TOKEN
github.org=github
```

* Token is included in the **Authorization header** for all API requests to avoid GitHub API rate-limit errors (5000 requests/hour instead of 60 for unauthenticated requests).

---

## **How to Run**

1. **Clone the repository:**

```bash
git clone https://github.com/YOUR_USERNAME/github-access-report.git
cd github-access-report
```

2. **Add your GitHub token** to `application.properties`:

```properties
github.token=YOUR_PERSONAL_ACCESS_TOKEN
github.org=github
```

3. **Run the project:**

```bash
mvn spring-boot:run
```

4. **Server starts at:**

```
http://localhost:8080
```

---

## **API Endpoint**

* **GET /api/access-report**
* Returns a JSON report mapping users to repositories they have access to.

### **Example Request**

```
http://localhost:8080/api/access-report
```

### **Example Response**

```json
[
  {
    "username": "pope",
    "repositories": ["version_sorter"]
  },
  {
    "username": "mprins",
    "repositories": ["maven-plugins"]
  }
]
```

---

## **Design Decisions**

* **Service Layer** separates GitHub API calls from controller logic.
* **Error Handling**: Null checks and `try-catch` blocks prevent crashes if API fails.
* **Structured JSON**: Each user appears once with a list of all repositories.
* **Scalability Consideration**: For large orgs, caching or GitHub GraphQL API can be used to reduce API calls.

---

## **Assumptions**

* Only **public repositories** are considered.
* **Contributors** are treated as users with repository access.
* Organization exists and contains repositories.

---

## **Optional Improvements (Future)**

* Sort **users and repositories alphabetically** for readability.
* Implement **GraphQL queries** for large organizations to reduce number of API calls.
* Add **unit tests** for service and controller.

---
---

If you want, I can **also create a quick checklist of everything you need to verify** before submitting to make sure **you don’t miss anything**. This ensures your GitHub repository is 100% submission-ready.

Do you want me to make that checklist?
