package cn.tycoding.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author miaomuzhi
 * @since 2019/1/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report implements Serializable {
    private String content;
}
