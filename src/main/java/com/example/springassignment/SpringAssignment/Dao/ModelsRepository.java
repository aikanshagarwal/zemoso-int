package com.example.springassignment.SpringAssignment.Dao;

import com.example.springassignment.SpringAssignment.Entity.Models;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelsRepository extends JpaRepository<Models, Integer>
{

    List<Models> findByManufacturerId(int theId);
}
