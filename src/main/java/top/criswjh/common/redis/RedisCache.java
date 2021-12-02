package top.criswjh.common.redis;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wjh
 * @date 2021/12/2 10:51 下午
 */
@Component
public class RedisCache {

    @Autowired
    private RedissonClient redissonClient;

    public RedisCache() {
    }

    public <T> void setCacheObject(String key, T value) {
        this.redissonClient.getBucket(key).set(value);
    }

    public <T> void setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        RBucket<T> result = this.redissonClient.getBucket(key);
        result.set(value);
        result.expire((long)timeout, timeUnit);
    }

    public boolean expire(String key, long timeout) {
        return this.expire(key, timeout, TimeUnit.SECONDS);
    }

    public boolean expire(String key, long timeout, TimeUnit unit) {
        RBucket rBucket = this.redissonClient.getBucket(key);
        return rBucket.expire(timeout, unit);
    }

    public <T> T getCacheObject(String key) {
        RBucket<T> rBucket = this.redissonClient.getBucket(key);
        return rBucket.get();
    }

    public boolean deleteObject(String key) {
        return this.redissonClient.getBucket(key).delete();
    }

    public void deleteObject(Collection collection) {
        RBatch batch = this.redissonClient.createBatch();
        collection.forEach((t) -> {
            batch.getBucket(t.toString()).deleteAsync();
        });
        batch.execute();
    }

    public <T> boolean setCacheList(String key, List<T> dataList) {
        RList<T> rList = this.redissonClient.getList(key);
        return rList.addAll(dataList);
    }

    public <T> List<T> getCacheList(String key) {
        RList<T> rList = this.redissonClient.getList(key);
        return rList.readAll();
    }

    public <T> boolean setCacheSet(String key, Set<T> dataSet) {
        RSet<T> rSet = this.redissonClient.getSet(key);
        return rSet.addAll(dataSet);
    }

    public <T> Set<T> getCacheSet(String key) {
        RSet<T> rSet = this.redissonClient.getSet(key);
        return rSet.readAll();
    }

    public <T> void setCacheMap(String key, Map<String, T> dataMap) {
        if (dataMap != null) {
            RMap<String, T> rMap = this.redissonClient.getMap(key);
            rMap.putAll(dataMap);
        }

    }

    public <T> Map<String, T> getCacheMap(String key) {
        RMap<String, T> rMap = this.redissonClient.getMap(key);
        return rMap.getAll(rMap.keySet());
    }

    public <T> void setCacheMapValue(String key, String hKey, T value) {
        RMap<String, T> rMap = this.redissonClient.getMap(key);
        rMap.put(hKey, value);
    }

    public <T> T getCacheMapValue(String key, String hKey) {
        RMap<String, T> rMap = this.redissonClient.getMap(key);
        return rMap.get(hKey);
    }

    public <K, V> Map<K, V> getMultiCacheMapValue(String key, Set<K> hKeys) {
        RMap<K, V> rMap = this.redissonClient.getMap(key);
        return rMap.getAll(hKeys);
    }

    public Collection<String> keys(String pattern) {
        Iterable<String> iterable = this.redissonClient.getKeys().getKeysByPattern(pattern);
        return Lists.newArrayList(iterable);
    }
}
