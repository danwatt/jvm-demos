package org.danwatt.hibernate.embedded;

import org.danwatt.hibernate.embedded.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
@DataJpaTest
@Transactional
public class HibernateInitializationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void newPerson() {
        Person p = new Person();
        p.setName("Bob");
        entityManager.persist(p);
    }

}
