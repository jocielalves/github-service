package com.jocielalves.cloud_native.github;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("service")
public class GithubService {

    @Autowired
    private  GitHub gitHub;

    public Integer getNumberRepo( String username) throws Exception {
        if( username.isBlank() || username == null) throw new Exception("Invalid username.");

        try {
            GHUser user = gitHub.getUser( username );
            return  user.getPublicRepoCount();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
