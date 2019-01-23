package cn.tycoding.entity;

import cn.tycoding.service.borrow.Borrower;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String userName;

    private String password;

    private double balance;

    @ManyToMany(mappedBy="members")
    private List<Department> belongedDepartments = new ArrayList<>();

    @ManyToMany(mappedBy = "permittedIndividuals")
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Book> borrowedBooks = new ArrayList<>();

    public void accept(Borrower borrower){
        borrower.borrow(this);
    }
}
