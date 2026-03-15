package com.example.githubaccess.controller;

import com.example.githubaccess.model.UserRepoReport;
import com.example.githubaccess.service.GitHubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccessReportController {

    private final GitHubService gitHubService;

    public AccessReportController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/access-report")
    public List<UserRepoReport> getReport() {
        return gitHubService.generateReport();
    }
}