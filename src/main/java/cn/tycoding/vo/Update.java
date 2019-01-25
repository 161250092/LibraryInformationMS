package cn.tycoding.vo;

import cn.tycoding.entity.Observable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author miaomuzhi
 * @since 2019/1/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Update{
    private Observable observable;
    private Object content;
}
