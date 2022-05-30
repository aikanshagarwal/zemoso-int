package com.example.springassignment.SpringAssignment.Service;

import com.example.springassignment.SpringAssignment.Entity.Models;
import java.util.List;

public interface ModelsService
{
    public List<Models> findAll();

    public List<Models> findByManufacturerId(int theId);

    public Models findById(int theId);

    public void save(Models theModel);

    public void deleteById(int theId);

}
