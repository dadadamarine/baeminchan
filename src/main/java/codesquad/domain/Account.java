package codesquad.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String userId;

    @Size(min = 6, max = 20)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long type = 1l;

}
