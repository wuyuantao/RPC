package com.smgeec.gkrpc;

import lombok.Data;

/**
 * 表示响应
 */
@Data
public class Response {
    /**
     * 服务编码
     */
    private int code = 0;
    /**
     * 具体错误信息
     */
    private String message = "ok";
    /**
     * 返回数据
     */
    private Object data;
}
