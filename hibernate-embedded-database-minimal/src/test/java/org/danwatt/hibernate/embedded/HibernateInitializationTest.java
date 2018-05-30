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

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(p.getId()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void samplePerson() {
        Person person = (Person) entityManager.getEntityManager()
                .createQuery("FROM Person WHERE name=:name")
                .setParameter("name", "Demo Person")
                .getSingleResult();
        assertThat(person).isNotNull();
    }

}
