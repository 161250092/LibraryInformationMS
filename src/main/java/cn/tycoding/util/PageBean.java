package cn.tycoding.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/7/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean implements Serializable {
    /**
     * 当前页
     * （如所有记录书籍的数量）
     */
    private long total;
    /**
     * 当前页记录
     */
    private List rows;
}
