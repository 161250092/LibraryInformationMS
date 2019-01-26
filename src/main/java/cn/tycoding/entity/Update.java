package cn.tycoding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@Entity
@Table(name="updating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Update implements Serializable {
    @Id
    private long updateId;
    @ManyToOne
    private User user;
    private String content;
    private LocalDateTime updateTime;
    private boolean confirmed;
}
