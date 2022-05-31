package com.example.springassignment.SpringAssignment.Entity;

import org.springframework.ui.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="manufacturer")
public class Manufacturer
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    @NotEmpty(message = "Field cannot be empty!")
    private String name;

    @Column(name="country")
    @NotEmpty(message = "Field cannot be empty!")
    private String country;

    @Column(name="email")
    @Email
    @NotEmpty(message = "Field cannot be empty!")
    private String email;

    @OneToMany(mappedBy = "manufacturer",cascade = CascadeType.ALL)
    private List<Models> models;

    public List<Models> getModels() {
        return models;
    }

    public void setModels(Models theModel)
    {
        if (models == null)
        {
            models = new ArrayList<Models>();
        }
        models.add(theModel);
        theModel.setManufacturer(this);
        this.models = models;
    }


    public Manufacturer(int id, String name, String country, String email) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.email = email;
    }

    public Manufacturer(String name, String country, String email) {
        this.name = name;
        this.country = country;
        this.email = email;
    }

    public Manufacturer()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


