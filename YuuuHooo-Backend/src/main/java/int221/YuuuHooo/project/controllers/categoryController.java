package int221.YuuuHooo.project.controllers;

import int221.YuuuHooo.project.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import int221.YuuuHooo.project.services.categoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"http://localhost:5173", "http://intproj22.sit.kmutt.ac.th"})
public class categoryController {
    @Autowired
    private categoryService categoryService;

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public int addCategory(@RequestBody Category newCategory) {
        return categoryService.addCategory(newCategory);
    }

//    @GetMapping("/por/{id}")
//    public String getCategoryName(@PathVariable int id){
//        return  categoryService.getCategoryName(id);
//    }
}
