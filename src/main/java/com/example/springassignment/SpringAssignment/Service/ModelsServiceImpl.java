package com.example.springassignment.SpringAssignment.Service;

import com.example.springassignment.SpringAssignment.Dao.ModelsRepository;
import com.example.springassignment.SpringAssignment.Entity.Models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ModelsServiceImpl implements ModelsService
{
    private ModelsRepository modelsRepository;

    @Autowired
    public ModelsServiceImpl(ModelsRepository theModelsRepository) {
        modelsRepository = theModelsRepository;
    }

    @Override
    public List<Models> findAll() {
        return modelsRepository.findAll();
    }

    @Override
    public List<Models> findByManufacturerId(int theId) {
        return modelsRepository.findByManufacturerId(theId);
    }

    @Override
    public Models findById(int theId) {
        Optional<Models> result = modelsRepository.findById(theId);

        Models theModels = null;

        if (result.isPresent()) {
            theModels = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find model id - " + theId);
        }

        return theModels;
    }

    @Override
    public void save(Models theModel) {
            modelsRepository.save(theModel);
    }

    @Override
    public void deleteById(int theId) {
            modelsRepository.deleteById(theId);
    }
}
