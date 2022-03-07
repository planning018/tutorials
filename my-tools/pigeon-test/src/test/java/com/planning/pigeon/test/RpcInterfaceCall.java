package com.planning.pigeon.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

import static com.planning.pigeon.test.ConvertUtils.toObject;

/**
 * @author yxc
 * @date 2021/12/15 3:17 下午
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class RpcInterfaceCall {

/*    @Test
    public void test() throws Exception {
        Object response = this.proxyInvoke(
                "http://service.abc.com/predict-service/enquiry_rec_model",
                "getModelName",
                null,
                null,
                5000);
        System.out.println(JSON.toJSONString(response));
    }*/

    // 获取目标 service 的过程
/*    private Object proxyInvoke(String serviceName, String methodName, String[] types, String[] values, int timeout)
            throws Exception {
        ProviderConfig<?> service = ServiceFactory.getAllServiceProviders().get(serviceName);
        if (service == null) {
            return null;
        }

        ReferenceBean bean = new ReferenceBean();
        bean.setUrl(serviceName);
        bean.setInterfaceName(service.getServiceInterface().getName());
        bean.setVip("console:" + service.getServerConfig().getActualPort());
        bean.setTimeout(timeout);
        bean.init();
        Object proxy = bean.getObject();

        return invoke(serviceName, methodName, types, values, proxy);
    }*/



    // 执行调用过程
    private Object invoke(String url, String methodName, String[] types, String[] values, Object service) throws Exception {
        Class<?> serviceClz = service.getClass();
        Class<?>[] typesClz = null;
        if (types != null) {
            typesClz = new Class<?>[types.length];
            for (int i = 0; i < types.length; i++) {
                String className = types[i];
                typesClz[i] = Class.forName(className);
            }
        }
        Method method = serviceClz.getMethod(methodName, typesClz);
        method.setAccessible(true);

        return method.invoke(service, formParameters(typesClz, values));
    }

    private Object[] formParameters(Class<?>[] types, String[] values) throws Exception,
            ClassNotFoundException {
        if (types == null || types.length == 0) {
            return new Object[0];
        }
        Object[] valueObjs = new Object[types.length];
        ;
        if (values == null) {
            valueObjs = new Object[0];
        } else {
            for (int i = 0; i < values.length; i++) {
                valueObjs[i] = toObject(types[i], values[i]);
            }
        }
        return valueObjs;
    }


}
