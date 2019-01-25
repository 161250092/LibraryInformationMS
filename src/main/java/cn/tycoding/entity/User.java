package cn.tycoding.entity;

import cn.tycoding.service.borrow.Borrower;
import cn.tycoding.util.ResultMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Observable, Serializable{
    private static final long serialVersionUID = 6529685098267757690L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique=true)
    private String userName;

    private String password;

    private double balance;

    private int borrowingNum;

    @ManyToMany(mappedBy="members")
    private List<Department> belongedDepartments = new ArrayList<>();

    @ManyToMany(mappedBy = "permittedIndividuals")
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Book> borrowedBooks = new ArrayList<>();

    @Transient
    //观察者数组,不与数据库映射
    private List<Administrator> observers = new LinkedList<>();

    /**
     * 可以在实例化时把所有的Admin注册上
     */
    @Override
    public void attach(Administrator admin){
       observers.add(admin);
    }

    @Override
    public void detach(Administrator admin){
         if(observers.contains(admin))
             observers.remove(admin);
         else
             System.out.println("the admin isn't one of the observers");
    }

    @Override
    public void notifyObservers(){
        for(Administrator admin:observers){
            admin.update();
        }
    }

    public void setUserId(long userId) {
        this.userId = userId;
        notifyObservers();
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyObservers();
    }

    public void setPassword(String password) {
        this.password = password;
        notifyObservers();
    }

    public void setBalance(double balance) {
        this.balance = balance;
        notifyObservers();
    }

    public ResultMessage accept(Borrower borrower, List<Book> books){
        return borrower.borrow(this, books);
    }
}
