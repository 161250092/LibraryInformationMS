package cn.tycoding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;

    private String departmentName;

    @ManyToMany
    private List<User> members = new ArrayList<>();

    @ManyToMany(mappedBy = "permittedDepartments")
    private List<Permission> permissions = new ArrayList<>();
}
