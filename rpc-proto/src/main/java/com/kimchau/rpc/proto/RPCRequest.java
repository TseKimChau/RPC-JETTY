package com.kimchau.rpc.proto;

import lombok.Data;

/**
 * @description 表示rpc的一个请求
 */
@Data
public class RPCRequest {

    private ServiceDescription serviceDescription;
    private Object[] parameters;

}
