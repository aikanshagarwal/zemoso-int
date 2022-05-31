package com.example.springassignment.SpringAssignment;

import com.example.springassignment.SpringAssignment.Dao.ManufacturerRepository;
import com.example.springassignment.SpringAssignment.Dao.ModelsRepository;
import com.example.springassignment.SpringAssignment.Entity.Manufacturer;
import com.example.springassignment.SpringAssignment.Entity.Models;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModelsServiceTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ModelsRepository theModelsRepository;

    @Test
    public void should_store_a_model()
    {
        Models theModel  = theModelsRepository.save(new Models("name", "variant"));
        assertThat(theModel).hasFieldOrPropertyWithValue("name", "name");
        assertThat(theModel).hasFieldOrPropertyWithValue("variant", "variant");
    }

     @Test
    public void should_find_all_models() {
         Models theModels1 = new Models("name1", "variant1");
         entityManager.persist(theModels1);
         Models theModels2 = new Models("name2", "variant2");
         entityManager.persist(theModels2);
         Models theModels3 = new Models("name3", "variant3");
         entityManager.persist(theModels3);
        Iterable tutorials = theModelsRepository.findAll();
        assertThat(tutorials).hasSize(17).contains(theModels1,theModels2,theModels3);
    }

    @Test
    public void should_find_model_by_id() {
        Models theModels1 = new Models("name1", "variant1");
        entityManager.persist(theModels1);
        Models theModels2 = new Models("name2", "variant2");
        entityManager.persist(theModels2);
        Models theModels3 = new Models("name3", "variant3");
        entityManager.persist(theModels3);
        Models theModels = theModelsRepository.findById(theModels1.getId()).get();
        assertThat(theModels).isEqualTo(theModels1);
    }

    @Test
    public void should_update_model_by_id() {
        Models theModels1 = new Models("name1", "variant1");
        entityManager.persist(theModels1);
        Models theModels2 = new Models("name2", "variant2");
        entityManager.persist(theModels2);
        Models theModels3 = new Models("name3", "variant3");
        entityManager.persist(theModels3);

        Models updatedModels = new Models("name4", "variant4");
        Models theModels = theModelsRepository.findById(theModels1.getId()).get();
        theModels.setName(updatedModels.getName());
        theModels.setVariant(updatedModels.getVariant());
        theModelsRepository.save(theModels);
        Models checkModels = theModelsRepository.findById(theModels1.getId()).get();

        assertThat(checkModels.getId()).isEqualTo(theModels1.getId());
        assertThat(checkModels.getName()).isEqualTo(updatedModels.getName());
        assertThat(checkModels.getVariant()).isEqualTo(updatedModels.getVariant());


    }

    @Test
    public void should_delete_manufacturer_by_id() {
        Models theModels1 = new Models("name1", "variant1");
        entityManager.persist(theModels1);
        Models theModels2 = new Models("name2", "variant2");
        entityManager.persist(theModels2);
        Models theModels3 = new Models("name3", "variant3");
        entityManager.persist(theModels3);

        theModelsRepository.deleteById(theModels1.getId());
        Iterable tutorials = theModelsRepository.findAll();
        assertThat(tutorials).hasSize(16).contains(theModels2, theModels3);

    }
}
