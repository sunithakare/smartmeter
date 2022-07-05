package com.envision.common.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomCacheListener implements CacheEventListener<Object, Object> {
 
 
//    @Override
//    public void onEvent(CacheEvent<Object, Object> cacheEvent) {
//        log.info("Cache event = {}, Key = {},  Old value = {}, New value = {}", cacheEvent.getType(),
//                cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
//    }

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		// TODO Auto-generated method stub

        log.info("Cache event = {}, Key = {},  Old value = {}, New value = {}", cacheEvent.getType(),
                cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
	}
}