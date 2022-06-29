package com.jocielalves.cloud_native.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/github")
public class GithubController {

    @Autowired
    private GithubService githubService;

    @GetMapping("/getRepository")
    public Integer getNumberRepo(@RequestParam("username") String username){
        try {
            return  githubService.getNumberRepo(username);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
