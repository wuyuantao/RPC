package com.smgeek.gkrpc.example;


import com.smgeek.gkrpc.server.RpcServer;
import com.smgeek.gkrpc.server.RpcServerConfig;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServerImpl());
        server.start();
    }
}
