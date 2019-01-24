package cn.tycoding.entity;

import cn.tycoding.util.BorrowState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated
    private BorrowState state;
}
