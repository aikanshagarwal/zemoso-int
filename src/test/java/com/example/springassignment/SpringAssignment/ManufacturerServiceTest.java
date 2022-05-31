package com.example.springassignment.SpringAssignment;

import com.example.springassignment.SpringAssignment.Dao.ManufacturerRepository;
import com.example.springassignment.SpringAssignment.Entity.Manufacturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ManufacturerServiceTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ManufacturerRepository theManufacturerRepository;

    @Test
    public void should_store_a_manufacturer()
    {
        Manufacturer theManufacturer  = theManufacturerRepository.save(new Manufacturer(1,"name", "country", "abcd@gmail.com"));
        assertThat(theManufacturer).hasFieldOrPropertyWithValue("name", "name");
        assertThat(theManufacturer).hasFieldOrPropertyWithValue("country", "country");
        assertThat(theManufacturer).hasFieldOrPropertyWithValue("email", "abcd@gmail.com");
    }

    @Test
    public void should_find_all_manufacturers() {
        Manufacturer theManufacturer1 = new Manufacturer("name1", "country1", "abcd1@gmail.com");
        entityManager.persist(theManufacturer1);
        Manufacturer theManufacturer2 = new Manufacturer("name2", "country2", "abcd2@gmail.com");
        entityManager.persist(theManufacturer2);
        Manufacturer theManufacturer3 = new Manufacturer("name3", "country3", "abcd3@gmail.com");
        entityManager.persist(theManufacturer3);
        Iterable tutorials = theManufacturerRepository.findAll();
        assertThat(tutorials).hasSize(8).contains(theManufacturer1,theManufacturer2,theManufacturer3);
    }

    @Test
    public void should_find_manufacturer_by_id() {
        Manufacturer theManufacturer1 = new Manufacturer("name1", "country1", "abcd1@gmail.com");
        entityManager.persist(theManufacturer1);
        Manufacturer theManufacturer2 = new Manufacturer("name2", "country2", "abcd2@gmail.com");
        entityManager.persist(theManufacturer2);
        Manufacturer theManufacturer3 = new Manufacturer("name3", "country3", "abcd3@gmail.com");
        entityManager.persist(theManufacturer3);
        Manufacturer theManufacturer = theManufacturerRepository.findById(theManufacturer1.getId()).get();
        assertThat(theManufacturer).isEqualTo(theManufacturer1);
    }

    @Test
    public void should_update_manufacturer_by_id() {
        Manufacturer theManufacturer1 = new Manufacturer("name1", "country1", "abcd1@gmail.com");
        entityManager.persist(theManufacturer1);
        Manufacturer theManufacturer2 = new Manufacturer("name2", "country2", "abcd2@gmail.com");
        entityManager.persist(theManufacturer2);
        Manufacturer theManufacturer3 = new Manufacturer("name3", "country3", "abcd3@gmail.com");
        entityManager.persist(theManufacturer3);

        Manufacturer updatedManufacturer = new Manufacturer("name4", "country4", "abcd4@gmail.com");
        Manufacturer theManufacturer = theManufacturerRepository.findById(theManufacturer1.getId()).get();
        theManufacturer.setName(updatedManufacturer.getName());
        theManufacturer.setCountry(updatedManufacturer.getCountry());
        theManufacturer.setEmail(updatedManufacturer.getEmail());
        theManufacturerRepository.save(theManufacturer);
        Manufacturer checkManufacturer = theManufacturerRepository.findById(theManufacturer1.getId()).get();

        assertThat(checkManufacturer.getId()).isEqualTo(theManufacturer1.getId());
        assertThat(checkManufacturer.getName()).isEqualTo(updatedManufacturer.getName());
        assertThat(checkManufacturer.getCountry()).isEqualTo(updatedManufacturer.getCountry());
        assertThat(checkManufacturer.getEmail()).isEqualTo(updatedManufacturer.getEmail());
    }

    @Test
    public void should_delete_manufacturer_by_id() {
        Manufacturer theManufacturer1 = new Manufacturer("name1", "country1", "abcd1@gmail.com");
        entityManager.persist(theManufacturer1);
        Manufacturer theManufacturer2 = new Manufacturer("name2", "country2", "abcd2@gmail.com");
        entityManager.persist(theManufacturer2);
        Manufacturer theManufacturer3 = new Manufacturer("name3", "country3", "abcd3@gmail.com");
        entityManager.persist(theManufacturer3);
        theManufacturerRepository.deleteById(theManufacturer1.getId());
        Iterable tutorials = theManufacturerRepository.findAll();
        assertThat(tutorials).hasSize(7).contains(theManufacturer2, theManufacturer3);
    }
}
