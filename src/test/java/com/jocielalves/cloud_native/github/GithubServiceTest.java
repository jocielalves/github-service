package com.jocielalves.cloud_native.github;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GithubServiceTest {

    @Mock
    GitHub gitHub;

    @Autowired
    @InjectMocks
    @Qualifier("service")
    private GithubService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    public void shouldReturnRepositoriesCount() throws Exception {
        String username = "test";
        Integer expectedCount = 0;
        when(gitHub.getUser(username)).thenReturn(new GHUser());

        assertEquals(expectedCount, service.getNumberRepo(username));
    }

    @Test
    public void shouldReturnInvalidUserIfUsernameNotExist() throws IOException {
        String username = "test";
        when(gitHub.getUser(username)).thenThrow(IOException.class);
        assertThrows(Exception.class, ()->service.getNumberRepo(username));
    }

    @Test
    public void shouldReturnInvalidUserIfUsernameIsBlank() {
        String username = "";
        assertThrows(Exception.class, ()->service.getNumberRepo(username));
    }

    @Test
    public void shouldReturnInvalidUserIfUsernameIsNull() {
        assertThrows(Exception.class, ()->service.getNumberRepo(null));
    }

    @Test
    public void shouldReturnInternalErrorIfAnInternalErrorOccurred() throws IOException {
        String username = "test";
        when(gitHub.getUser(username)).thenThrow(NullPointerException.class);
        assertThrows(Exception.class, ()->service.getNumberRepo(username));
    }
}
