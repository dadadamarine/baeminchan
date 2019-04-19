package codesquad.service;

import codesquad.domain.MenuCategory;
import codesquad.domain.MenuCategoryRepository;
import codesquad.exception.category.CategoryNotFoundException;
import codesquad.web.dto.MenuCategoryDTO;
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

    public MenuCategory create(MenuCategoryDTO menuCategoryDTO) {
        //TODO 서비스 생성, 부모가 있을때 없을때 구분할것.
        return null;
    }

    public MenuCategory deleteById(Long id) {
        MenuCategory menuCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return deleteWithChilderen(menuCategory);
    }

    private MenuCategory deleteWithChilderen(MenuCategory menuCategory) {
        categoryRepository.delete(menuCategory);
        return menuCategory;
    }
}
