package com.example.springassignment.SpringAssignment.Service;

import java.util.List;
import java.util.Optional;

import com.example.springassignment.SpringAssignment.Dao.ManufacturerRepository;
import com.example.springassignment.SpringAssignment.Entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	public ManufacturerServiceImpl(ManufacturerRepository theManufacturerRepository) {
		manufacturerRepository = theManufacturerRepository;
	}
	
	@Override
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}


	@Override
	public Manufacturer findById(int theId) {
		Optional<Manufacturer> result = manufacturerRepository.findById(theId);
		
		Manufacturer theManufacturer = null;
		
		if (result.isPresent()) {
			theManufacturer = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find manufacturer id - " + theId);
		}
		
		return theManufacturer;
	}

	@Override
	public void save(Manufacturer theManufacturer) {
		manufacturerRepository.save(theManufacturer);
	}

	@Override
	public void deleteById(int theId) {
		manufacturerRepository.deleteById(theId);
	}

}






