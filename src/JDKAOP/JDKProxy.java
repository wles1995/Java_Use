package JDKAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private Object targetObject;

    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;        //返回代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                                      targetObject.getClass().getInterfaces(),
                                      this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("======检查权限checkPopedom()======");
        Object ret = null;
        ret  = method.invoke(targetObject, args);
        return ret;
    }

}