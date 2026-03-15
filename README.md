GitHub Access Report Service
Overview

This Spring Boot application connects to the GitHub API and generates a report showing which users have access to which repositories in a given organization. The service aggregates contributor data and exposes it through a REST API.

Tech Stack

Java

Spring Boot

Maven

GitHub REST API

Authentication

Uses a GitHub Personal Access Token (PAT) for authentication.

The token is stored in src/main/resources/application.properties:

github.token=YOUR_PERSONAL_ACCESS_TOKEN
github.org=github

The token is added in the Authorization header for all GitHub API requests to avoid rate-limit errors.

How to Run

Clone the repository:

git clone https://github.com/YOUR_USERNAME/github-access-report.git
cd github-access-report

Add your GitHub Personal Access Token in application.properties:

github.token=YOUR_PERSONAL_ACCESS_TOKEN
github.org=github

Run the project:

mvn spring-boot:run

The server will start on:

http://localhost:8080
API Endpoint

GET /api/access-report

Returns a JSON report mapping users to the repositories they have access to.

Example Response
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
Design Decisions

Service layer separates GitHub API calls from controller logic.

Null checks and error handling are implemented to manage failed requests.

JSON output is structured as a list of objects for clarity.

Only public repositories are considered.

Assumptions

Contributors are treated as users with repository access.

The application assumes the organization exists and contains repositories.

For large organizations, GraphQL API or caching could be used to reduce API calls (optional improvement).