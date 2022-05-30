package com.example.springassignment.SpringAssignment.Service;

import java.util.List;

import com.example.springassignment.SpringAssignment.Entity.Manufacturer;

public interface ManufacturerService {

	public List<Manufacturer> findAll();
	
	public Manufacturer findById(int theId);
	
	public void save(Manufacturer theManufacturer);
	
	public void deleteById(int theId);
	
}
