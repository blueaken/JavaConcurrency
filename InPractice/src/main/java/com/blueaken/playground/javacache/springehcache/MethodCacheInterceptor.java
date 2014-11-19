package com.blueaken.playground.javacache.springehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.io.Serializable;

/**
 * @author jianshen
 */
public class MethodCacheInterceptor implements MethodInterceptor{
    private static final Log logger = LogFactory.getLog(MethodCacheInterceptor.class);
    private Cache cache;
    public void setCache(Cache cache) { this.cache = cache; }

    public MethodCacheInterceptor() {
        super();
    }

    /**  * 拦截 Service/DAO 的方法，并查找该结果是否存在，如果存在就返回 cache 中的值，
     * 否则，返回数据库查询结果，并将查询结果放入 cache
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Object result;

        logger.debug("Find object from cache is " + cache.getName());
        String cacheKey = getCacheKey(targetName, methodName, arguments);
        Element element = cache.get(cacheKey);
        if (element == null) {
            logger.debug("Hold up method , Get method result and create cache........!");
            result = invocation.proceed();
            element = new Element(cacheKey, (Serializable) result);
            cache.put(element);
        }
        return element.getValue();
    }
}
