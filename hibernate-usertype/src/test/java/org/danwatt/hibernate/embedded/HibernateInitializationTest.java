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
import java.sql.Date;
import java.time.Month;
import java.time.MonthDay;

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
		p.setAnniversary(MonthDay.of(Month.JULY, 15));
		entityManager.persist(p);
		assertThat(p.getId()).isGreaterThanOrEqualTo(0);

		Date anniversary = (Date) entityManager.getEntityManager()
				.createNativeQuery("select anniversary from person where name=:name")
				.setParameter("name", "Bob")
				.getSingleResult();

		assertThat(anniversary).hasDayOfMonth(15);
		assertThat(anniversary).hasMonth(7);
	}

	@Test
	public void samplePerson() {
		Person person = (Person) entityManager.getEntityManager()
				.createQuery("FROM Person WHERE name=:name")
				.setParameter("name", "Demo Person")
				.getSingleResult();
		assertThat(person).isNotNull();
		assertThat(person.getAnniversary()).isEqualTo(MonthDay.of(Month.DECEMBER,25));
	}


}
