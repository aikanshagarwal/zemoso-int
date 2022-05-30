package com.example.springassignment.SpringAssignment.Controller;

import com.example.springassignment.SpringAssignment.Entity.Manufacturer;
import com.example.springassignment.SpringAssignment.Service.ManufacturerService;
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
@RequestMapping("/manufacturer")
public class ManufacturerController implements WebMvcConfigurer
{
    Logger logger = LoggerFactory.getLogger(ManufacturerController.class);
    private ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService theManufacturerService,ModelsService theModelsService)
    {
        manufacturerService = theManufacturerService;
    }

    //add mapping for /list
    @GetMapping("/list")
    public String listManufacturers(Model theModel)
    {
        //get manufacturers from database
        List<Manufacturer> theManufacturer =  manufacturerService.findAll();

        //add to the spring model
        theModel.addAttribute("manufacturers",theManufacturer);

        logger.info("Getting the list of Manufacturers");

        return "list-manufacturers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Manufacturer theManufacturer = new Manufacturer();

        logger.info("Calling manufacturer-form for adding a manufacturer.");

        theModel.addAttribute("manufacturer", theManufacturer);

        return "manufacturer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("manufacturerId") int theId , Model theModel)
    {
        //get the manufacturer from the db using the id passed
        Manufacturer theManufacturer = manufacturerService.findById(theId);

        //set manufacturer as a model attribute to prepopulate the form
        theModel.addAttribute("manufacturer",theManufacturer);

        logger.info("Calling manufacturer-form for updating a manufacturer.");

        //send over to our form
        return "manufacturer-form";
    }

    @PostMapping("/save")
    public String saveManufacturer(@Valid @ModelAttribute("manufacturer") Manufacturer theManufacturer, BindingResult theBindingResult)
    {
        if (theBindingResult.hasErrors()) {
            return "manufacturer-form";
        }

        //save the manufacturer
        manufacturerService.save(theManufacturer);

        logger.info("Saving a new manufacturer and redirecting to manufacturer list.");

        //use a redirect to prevent duplicate submissions
        return "redirect:/manufacturer/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("manufacturerId") int theId)
    {
        manufacturerService.deleteById(theId);

        logger.info("Deleting a new manufacturer and redirecting to manufacturer list.");

        return "redirect:/manufacturer/list";
    }

    @GetMapping("/showWebsite")
    public String showWebsite(@RequestParam("manufacturerId") int theId)
    {
        logger.info("Redirecting to provided manufacturer's website.");
        if(theId==1)
        {
            return "redirect:https://www.hyundai.com/in/en";
        }
        else if(theId==2)
        {
            return "redirect:https://www.marutisuzuki.com/";
        }
        else if(theId==3)
        {
            return "redirect:https://www.tatamotors.com/";
        }
        else if(theId==4)
        {
            return "redirect:https://www.volkswagen.co.in/en.html";
        }
        else if(theId==8)
        {
            return "redirect:https://www.renault.co.in/";
        }
        else
        {
            return "redirect:/manufacturer/list";
        }
    }

}
