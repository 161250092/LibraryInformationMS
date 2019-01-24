package cn.tycoding.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
@Entity
@Data
public class Graduate extends User {

    private String major;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Graduate graduate = (Graduate) o;
        return Objects.equals(major, graduate.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), major);
    }
}
