package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import support.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MenuCategory extends AbstractEntity {

    @Size(min = 1, max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private MenuCategory parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private List<MenuCategory> children = new ArrayList<>();

    public MenuCategory(Long id, String name) {
        super(id);
        this.name = name;
    }

    public MenuCategory(Long id, @Size(min = 1, max = 50) String name, MenuCategory parent, List<MenuCategory> children) {
        super(id);
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public MenuCategory addChild(MenuCategory child) {
        children.add(child);
        child.setParent(this);
        return this;
    }

    public void removeChild(MenuCategory menuCategory) {
        children.remove(menuCategory);
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MenuCategory) {
            MenuCategory targetMenuCategory = (MenuCategory) o;
            return this.getId().equals(targetMenuCategory.getId());
        }
        return false;
    }

    public boolean isParentCategory() {
        return parent == null;
    }
}
