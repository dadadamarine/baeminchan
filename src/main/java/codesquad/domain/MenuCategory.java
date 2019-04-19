package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        this.id = id;
        this.name = name;
    }

    public MenuCategory(Long id, @Size(min = 1, max = 50) String name, MenuCategory parent, List<MenuCategory> children) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public MenuCategory addChild(MenuCategory child) {
        children.add(child);
        child.setParent(this);
        return this;
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
