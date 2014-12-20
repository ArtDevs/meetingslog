package artdevs.meetingslog.services.tests;

import junit.framework.Assert;
import org.artdevs.meetingslog.services.ServiceComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Artem L.V. on 20.12.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/custom-config/services-custom-spring.xml")
public class MyTest {

    @Autowired
    private ServiceComponent serviceComponent;

    @Test
    public void testSomething() {
        assertNotNull(serviceComponent);
        assertEquals("Message from Some Service component", serviceComponent.getMessage());
    }

}
