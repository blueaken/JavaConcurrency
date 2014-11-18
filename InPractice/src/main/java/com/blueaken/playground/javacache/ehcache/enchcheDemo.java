package com.blueaken.playground.javacache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author jianshen
 */
public class enchcheDemo {
    public static void main(String[] args){
        //create cache manager with cache config name "sampleCache1"
        CacheManager singletonManager = CacheManager.create("src/main/resources/cache/ehcache.xml");
        Cache cache = singletonManager.getCache("sampleCache1");

        //add Object into cache and read and print
        User testUser1 = new User("andrew", "A");
        cache.put(new Element("keyA", testUser1));

        Element elementFromCache = cache.get("keyA");
        Object oValue = elementFromCache.getObjectValue();
        String userName = ((User)oValue).getName();
        System.out.println("elementFromCache User1 getName is " + userName);

        //close cache after usage
        singletonManager.removeCache("sampleCache1");
        singletonManager.shutdown();
    }
}
