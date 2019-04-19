package codesquad.web;

import codesquad.domain.MenuCategory;
import codesquad.service.MenuCategoryService;
import codesquad.web.dto.MenuCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static support.web.ResponseGenerator.makeCreatedResponseEntity;

@RestController
@RequestMapping("/api/menuCategory")
public class ApiMenuCategoryController {

    @Autowired
    MenuCategoryService menuCategoryService;

    @GetMapping("")
    public MenuCategory getCategories(){
        return menuCategoryService.findRoot();
    }

    @PostMapping("")
    public ResponseEntity<MenuCategory> create(MenuCategoryDTO menuCategoryDTO) {
        MenuCategory createdCategory=menuCategoryService.create(menuCategoryDTO);
        return makeCreatedResponseEntity(createdCategory);
    }

    @DeleteMapping("/{id}")
    public MenuCategory delete(@PathVariable Long id){
        return menuCategoryService.deleteById(id);
    }
}
