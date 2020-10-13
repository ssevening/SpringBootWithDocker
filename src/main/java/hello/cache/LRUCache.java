package hello.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * LRUCache链表+hashmap的实现方式
 */
public class LRUCache<K, V> {

    public final int CACHE_SIZE;
    public Entry first;
    public Entry last;
    public HashMap<K, Entry<K, V>> hashMap;

    public LRUCache(int cacheSize) {
        this.CACHE_SIZE = cacheSize;
        hashMap = new HashMap<K, Entry<K, V>>();
    }

    public void put(K key, V value) {
        Entry entry = getEntry(key);
        if (entry == null) {
            if (hashMap.size() >= CACHE_SIZE) {
                hashMap.remove(last.key);
                removeLast();
            }
            entry = new Entry();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        hashMap.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null)
            return null;
        moveToFirst(entry);
        return entry.value;
    }


    private void moveToFirst(Entry entry) {
        if (entry == first)
            return;
        if (entry.pre != null)
            entry.pre.next = entry.next;
        if (entry.next != null)
            entry.next.pre = entry.pre;
        if (last == entry)
            last = last.pre;

        if (first == null || last == null) {
            first = last = entry;
            return;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        first.pre = null;
    }

    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null)
                first = null;
            else
                last.next = null;
        }
    }


    public Entry getEntry(K key) {
        return hashMap.get(key);
    }

    public void print(HashMap<K, Entry<K, V>> map) {
        Collection<Entry<K, V>> entry = map.values();
        Iterator it = entry.iterator();
        while (it.hasNext()) {
            Entry<K, V> e = (LRUCache<K, V>.Entry<K, V>) it.next();
            System.out.println(e.key + "  :  " + e.value);
        }
    }

    class Entry<K, V> {
        Entry pre;
        Entry next;
        K key;
        V value;
    }


    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("one", "1");
        cache.put("tow", "2");
        cache.put("three", "3");
        System.out.println("first entry: " + cache.first.key);
        cache.print(cache.hashMap);
        cache.put("four", "4");
        System.out.println("============================");
        System.out.println("first entry: " + cache.first.key);
        cache.print(cache.hashMap);
        cache.put("five", "5");
        System.out.println("============================");
        System.out.println("first entry: " + cache.first.key);
        cache.print(cache.hashMap);
    }
}

