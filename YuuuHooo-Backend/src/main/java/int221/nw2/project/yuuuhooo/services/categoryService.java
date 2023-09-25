package int221.nw2.project.yuuuhooo.services;


import int221.nw2.project.yuuuhooo.entities.Category;
import int221.nw2.project.yuuuhooo.repositories.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class categoryService {

    @Autowired
    private categoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id){
        return categoryRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category id " + id + " does not exist"
                ));
    }

    public int addCategory(Category newCategory){
        categoryRepository.saveAndFlush(newCategory);
        return newCategory.getCategoryId();
    }

    public String getCategoryName(int id) {
            Category category = categoryRepository.findById(id).orElseThrow(
                    ()-> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Category id " + id + " does not exist"
                    ));
            return category.getCategoryName();
    }

}
