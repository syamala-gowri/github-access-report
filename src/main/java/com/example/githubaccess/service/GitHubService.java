package com.example.githubaccess.service;

import com.example.githubaccess.model.UserRepoReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GitHubService {

    @Value("${github.token}")
    private String token;

    @Value("${github.org}")
    private String org;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<UserRepoReport> generateReport() {

        Map<String, List<String>> report = new HashMap<>();

       
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/vnd.github+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

      
        String reposUrl = "https://api.github.com/orgs/" + org + "/repos";
        List<Map<String, Object>> repos;
        try {
            ResponseEntity<List> repoResponse =
                    restTemplate.exchange(reposUrl, HttpMethod.GET, entity, List.class);
            repos = repoResponse.getBody();
        } catch (RestClientException e) {
            System.err.println("Error fetching repositories: " + e.getMessage());
            return Collections.emptyList(); // return empty list if repo fetch fails
        }

        if (repos == null) return Collections.emptyList();

       
        for (Map<String, Object> repo : repos) {
            String repoName = (String) repo.get("name");
            String contributorsUrl = "https://api.github.com/repos/" + org + "/" + repoName + "/contributors";

            List<Map<String, Object>> users;
            try {
                ResponseEntity<List> userResponse =
                        restTemplate.exchange(contributorsUrl, HttpMethod.GET, entity, List.class);
                users = userResponse.getBody();
            } catch (RestClientException e) {
                System.err.println("Error fetching contributors for " + repoName + ": " + e.getMessage());
                continue; 
            }

            if (users == null) continue;

            for (Map<String, Object> user : users) {
                String username = (String) user.get("login");
                if (username == null) continue;

                report.putIfAbsent(username, new ArrayList<>());
                report.get(username).add(repoName);
            }
        }

       
        List<UserRepoReport> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : report.entrySet()) {
            result.add(new UserRepoReport(entry.getKey(), entry.getValue()));
        }

        return result;
    }
}
