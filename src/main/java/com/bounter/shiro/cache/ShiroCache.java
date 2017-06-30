package com.bounter.shiro.cache;

import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * 自定义shiro缓存，基于Spring Cache
 * @author simon
 *
 */
public class ShiroCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {

	private final org.springframework.cache.Cache cache;
	
	public ShiroCache(Cache cache) {
		if (cache == null) {
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) throws CacheException {
		ValueWrapper valueWrapper = cache.get(key);
		return valueWrapper == null ? null : (V) valueWrapper.get();
	}

	@Override
	public V put(K key, V value) throws CacheException {
		V previous = get(key);
		cache.put(key, value);
		return previous;
	}

	@Override
	public V remove(K key) throws CacheException {
		V previous = get(key);
		cache.evict(key);
		return previous;
	}

	@Override
	public void clear() throws CacheException {
		cache.clear();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Set<K> keys() {
		return Collections.emptySet();
	}

	@Override
	public Collection<V> values() {
		return Collections.emptySet();
	}

	@Override
	public String toString() {
		return "ShiroCache [" + this.cache.getName() + "]";
	}
}
