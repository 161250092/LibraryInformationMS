package cn.tycoding.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/9/28
 */
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    //判断结果
    private boolean success;
    //返回信息
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
