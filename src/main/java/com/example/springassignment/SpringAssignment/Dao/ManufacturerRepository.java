package com.example.springassignment.SpringAssignment.Dao;

import com.example.springassignment.SpringAssignment.Entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>
{

}
