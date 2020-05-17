package org.olegv.reactiveclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.olegv.reactiveclient.services.ExampleLowLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestExampleControllerJava {

    @Autowired
    private TestRestTemplate restTemplate;

    @SpyBean
    private ExampleLowLevelService exampleLowLevelService;

    @BeforeEach
    public void beforeClass() {
        doReturn(42).when(exampleLowLevelService).getValue();
    }

    @Test
    public void testCallLowLevelService() {
        ResponseEntity<Integer> entity = restTemplate.getForEntity("/test-handler", Integer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo(42);
    }
}
