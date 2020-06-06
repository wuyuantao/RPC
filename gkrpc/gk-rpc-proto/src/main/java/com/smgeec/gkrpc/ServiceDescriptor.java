package com.smgeec.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 表示服务
 *
 */
@Data   //set,get 方法
@AllArgsConstructor //带所有字段的构造方法
@NoArgsConstructor //默认构造方法
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp =new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());
        Class[] paramterClasses =  method.getParameterTypes();
        String[] paramterTypes = new String[paramterClasses.length];
        for(int i=0;i<paramterClasses.length;i++){
            paramterTypes[i]=paramterClasses[i].getName();
        }
        sdp.setParameterTypes(paramterTypes);
        return sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this ==o) return true;
        if(o==null || getClass()!=o.getClass()) return  false;

        ServiceDescriptor that=(ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "clazz="+clazz
                +",method="+method
               +",rturnType="+returnType
                +",parameterTypes="+ Arrays.toString(parameterTypes);
    }
}
