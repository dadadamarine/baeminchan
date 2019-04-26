package codesquad.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategoryDTO {

    private Long id;

    private String name;

    private Long parentId;

    public boolean hasParent() {
        if (parentId == null) {
            return false;
        }
        if (parentId == 0L) {
            return false;
        }
        return true;
    }

}
