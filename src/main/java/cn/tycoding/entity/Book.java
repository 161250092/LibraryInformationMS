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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String title;

    private int totalNum;

    private int borrowedNum;

    @ManyToMany
    @JoinTable(name="Borrowing",
            joinColumns=@JoinColumn(name="bookId", referencedColumnName="bookId"),
            inverseJoinColumns=@JoinColumn(name="userId", referencedColumnName="userId"))
    private List<User> users = new ArrayList<>();
}
