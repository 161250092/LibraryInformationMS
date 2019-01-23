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
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long permissionId;

    private String type;

    @ManyToMany
    private List<Department> permittedDepartments = new ArrayList<>();

    @ManyToMany
    private List<User> permittedIndividuals = new ArrayList<>();

    public boolean authorize(long userId){
        for (User permittedIndividual : permittedIndividuals) {
            if (permittedIndividual.getUserId() == userId){
                return true;
            }
        }
        for (Department department : permittedDepartments) {
            for (User user : department.getMembers()) {
                if (user.getUserId() == userId){
                    return true;
                }
            }
        }
        return false;
    }
}
