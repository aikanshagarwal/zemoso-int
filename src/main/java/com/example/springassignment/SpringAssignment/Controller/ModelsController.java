package com.example.springassignment.SpringAssignment.Controller;

import com.example.springassignment.SpringAssignment.Entity.Manufacturer;
import com.example.springassignment.SpringAssignment.Entity.Models;
import com.example.springassignment.SpringAssignment.Service.ManufacturerService;
import com.example.springassignment.SpringAssignment.Service.ManufacturerServiceImpl;
import com.example.springassignment.SpringAssignment.Service.ModelsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/models")
public class ModelsController implements WebMvcConfigurer
{
    Logger logger = LoggerFactory.getLogger(ModelsController.class);
    private ModelsService modelsService;
    private ManufacturerService manufacturerService;

    @Autowired
    public ModelsController(ModelsService theModelsService,ManufacturerService theManufacturerService)
    {
        modelsService = theModelsService;
        manufacturerService = theManufacturerService;
    }

    //add mapping for /list
    @GetMapping("/list")
    public String listModels(Model theModel)
    {
        //get models from database
        List<Models> theModels =  modelsService.findAll();

        //add to the spring model
        theModel.addAttribute("models",theModels);

        logger.info("Getting the list of Models");

        return "list-models";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Models theModels = new Models();

        theModel.addAttribute("models", theModels);

        List<Manufacturer> theManufacturer =  manufacturerService.findAll();

        //add to the spring model
        theModel.addAttribute("manufacturers",theManufacturer);

        logger.info("Calling models-form for adding a model.");

        return "models-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("modelsId") int theId , Model theModel)
    {
        //get the model from the db using the id passed
        Models theModels = modelsService.findById(theId);

        //set model as a model attribute to prepopulate the form
        theModel.addAttribute("models",theModels);

        List<Manufacturer> theManufacturer =  manufacturerService.findAll();

        //add to the spring model
        theModel.addAttribute("manufacturers",theManufacturer);

        logger.info("Calling models-form for updating a model.");

        //send over to our form
        return "models-form";
    }

    @PostMapping("/save")
    public String saveModels(@Valid @ModelAttribute("models") Models theModels, BindingResult theBindingResult)
    {
        if (theBindingResult.hasErrors())
        {
            return "models-form";
        }

        //save the model
        modelsService.save(theModels);

        logger.info("Saving a new model and redirecting to models list.");

        //use a redirect to prevent duplicate submissions
        return "redirect:/models/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("modelsId") int theId)
    {

        modelsService.deleteById(theId);

        logger.info("Deleting a model and redirecting to models list.");

        return "redirect:/models/list";
    }

    @GetMapping("/showModels")
    public String listManufacturers(@RequestParam("manufacturerId") int theId,Model theModel)
    {
        List<Models> theModels = modelsService.findByManufacturerId(theId);
        theModel.addAttribute("models",theModels);

        logger.info("Showing models given a particular manufacturer.");

        return "list-models";
    }

}
