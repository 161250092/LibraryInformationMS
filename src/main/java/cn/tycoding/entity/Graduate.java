package cn.tycoding.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author miaomuzhi
 * @since 2019/1/23
 */
@Entity
@Data
public class Graduate extends User{
    private String major;
}
