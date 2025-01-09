package meryem.categoryservice.Controller;

import meryem.categoryservice.Entity.Category;
import meryem.categoryservice.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryRepo.findById(id).orElseThrow(()->new RuntimeException(String.format("La catégorie %d n'existe pas",id)));
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }
}
