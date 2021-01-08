package com.sthanik.expensetrack.resource;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class HealthcheckResourceTest {

    @InjectMocks
    private HealthcheckResource resource;

    @Test
    public void testHealthcheck() {
        assertThat(resource.healthcheck()).isEqualTo(HttpStatus.OK);
    }
}
