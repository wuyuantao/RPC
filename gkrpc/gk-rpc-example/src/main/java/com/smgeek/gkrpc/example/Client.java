package com.smgeek.gkrpc.example;

import com.smgeek.gkrpc.client.RpcClient;
import com.smgeek.gkrpc.client.RpcClientConfig;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service=client.getProxy(CalcService.class);
        int add= service.add(1,3);
        int minus=service.minus(10,5);
        System.out.println(add);
        System.out.println(minus);
    }
}
