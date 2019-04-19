package codesquad.web;

import codesquad.domain.MenuCategory;
import codesquad.service.MenuCategoryService;
import codesquad.web.dto.MenuCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static support.web.ResponseGenerator.makeCreatedResponseEntity;

@RestController
@RequestMapping("/api/menuCategory")
public class ApiMenuCategoryController {

    @Autowired
    MenuCategoryService menuCategoryService;

    @GetMapping("")
    public List<MenuCategory> getCategories() {
        return menuCategoryService.findCategories();
    }

    @PostMapping("")
    public ResponseEntity<MenuCategory> create(@RequestBody MenuCategoryDTO menuCategoryDTO) {
        MenuCategory createdCategory = menuCategoryService.create(menuCategoryDTO);
        return makeCreatedResponseEntity(createdCategory);
    }

    @DeleteMapping("/{id}")
    public MenuCategory delete(@PathVariable Long id) {
        return menuCategoryService.deleteById(id);
    }
}
