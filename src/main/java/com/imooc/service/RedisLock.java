package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate template;

    public boolean lock(String key, String value) {
        // SETNX
        if (template.opsForValue().setIfAbsent(key, value))
            return true;
        String currentValue = template.opsForValue().get(key);
        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            String oldValue = template.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue))
                return true;
        }
        return false;
    }

    public void unlock(String key, String value) {
        try {
            String currentValue = template.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value))
                template.opsForValue().getOperations().delete(key);
        } catch (Exception e) {
            log.error("[redis分布式锁] 解锁异常");
        }
    }
}
