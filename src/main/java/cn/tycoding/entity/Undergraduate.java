package cn.tycoding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Undergraduate extends User {
    private String major;

    public Undergraduate(long userId,String userName,String password,double balance,String major){
        super.setUserId(userId);
        super.setUserName(userName);
        super.setPassword(password);
        super.setBalance(balance);
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Undergraduate that = (Undergraduate) o;
        return Objects.equals(major, that.major);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), major);
    }
}
