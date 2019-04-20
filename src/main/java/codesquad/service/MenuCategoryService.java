package codesquad.service;

import codesquad.domain.MenuCategory;
import codesquad.domain.MenuCategoryRepository;
import codesquad.exception.category.CategoryNotFoundException;
import codesquad.web.dto.MenuCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryService {

    @Autowired
    private MenuCategoryRepository categoryRepository;

    MenuCategoryService(MenuCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<MenuCategory> findCategories() {
        return categoryRepository.findByParent(null);
    }

    public MenuCategory create(MenuCategoryDTO menuCategoryDTO) {
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName(menuCategoryDTO.getName());
        if (menuCategoryDTO.hasParent()) {
            menuCategory.setParent(findById(menuCategoryDTO.getParentId()));
            return categoryRepository.save(menuCategory);
        }
        menuCategory.setParent(null);
        return categoryRepository.save(menuCategory);
    }

    public MenuCategory deleteById(Long id) {
        MenuCategory menuCategory = findById(id);
        return deleteWithChildren(menuCategory);
    }

    public MenuCategory findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    private MenuCategory deleteWithChildren(MenuCategory menuCategory) {
        categoryRepository.delete(menuCategory);
        return menuCategory;
    }
}
