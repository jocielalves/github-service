package com.jocielalves.cloud_native.github;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public GitHub github() throws IOException {
        try{
            return new GitHubBuilder().build();
        } catch (IOException e){
            throw new IOException(e);
        }

    }

}
