
package com.jocielalves.cloud_native.github;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GithubController.class)
public class GithubControllerTest {

    private final static String GET_COUNT_URL = "/github/getRepository";

    @MockBean
    private GithubService service;

    @Autowired
    private MockMvc mockMvc;

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
        Integer expectedCount = 10;
        when(service.getNumberRepo(anyString())).thenReturn(expectedCount);

        this.mockMvc.perform(get(GET_COUNT_URL)
                .param("username", anyString()))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestIfUserNotExist() throws Exception {
        when(service.getNumberRepo(anyString())).thenThrow(new Exception());

        this.mockMvc.perform(get(GET_COUNT_URL)
                        .param("username", anyString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnInternalErrorIfMethodFailed() throws Exception {
        when(service.getNumberRepo(anyString())).thenThrow(new Exception());

        this.mockMvc.perform(get(GET_COUNT_URL)
                        .param("username", anyString()))
                .andExpect(status().isBadRequest());
    }
}
