package org.artdevs.meetingslog.services.tests;

import org.artdevs.meetingslog.services.ServiceComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath*:/META-INF/custom-config/*-custom-spring.xml",
                "classpath*:/META-INF/config/meetingslog-*-config.xml"
        }
)
public class MyTest {

    @Autowired
    private ServiceComponent serviceComponent;

    @Test
    public void testSomething() {
        assertNotNull(serviceComponent);
        assertEquals("Message from Some Service component", serviceComponent.getMessage());
    }

}
