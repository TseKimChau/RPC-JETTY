package com.kimchau.rpc.proto;

import lombok.Data;

@Data
public class RPCResponse {

    private int code;
    private String message;
    private Object data;

}
