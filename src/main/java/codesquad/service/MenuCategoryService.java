package codesquad.service;

import codesquad.domain.MenuCategory;
import codesquad.domain.MenuCategoryRepository;
import codesquad.exception.category.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuCategoryService {

    @Autowired
    private MenuCategoryRepository categoryRepository;

    MenuCategoryService(MenuCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public MenuCategory findRoot() {
        return categoryRepository.findByParent(null)
                .orElseThrow(CategoryNotFoundException::new);
    }

}
