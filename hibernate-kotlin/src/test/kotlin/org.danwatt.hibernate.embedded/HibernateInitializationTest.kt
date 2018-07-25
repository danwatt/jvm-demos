package org.danwatt.hibernate.embedded

import org.assertj.core.api.Assertions.assertThat
import org.danwatt.hibernate.embedded.model.Person
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestConfiguration::class])
@DataJpaTest
@Transactional
open class HibernateInitializationTest {
    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Test
    fun newPerson() {
        val person = Person(name="Bob")
        testEntityManager.persist(person)
        assertThat(person.id).isGreaterThanOrEqualTo(0)
    }

    @Test
    fun samplePerson() {
        val person = testEntityManager.entityManager
                .createQuery("FROM Person WHERE name=:name")
                .setParameter("name", "Demo Person")
                .singleResult as Person
        assertThat(person).isNotNull
    }

}
