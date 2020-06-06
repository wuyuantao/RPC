package com.smgeek.gkrpc.example;

public class CalcServerImpl implements CalcService{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int c, int d) {
        return c-d;
    }
}
