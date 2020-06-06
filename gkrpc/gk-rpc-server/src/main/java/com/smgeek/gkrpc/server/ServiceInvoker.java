package com.smgeek.gkrpc.server;

import com.segeec.gkrpc.commom.utils.ReflectionUtils;
import com.smgeec.gkrpc.Request;

public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
