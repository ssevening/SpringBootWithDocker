package hello.cache;

public class MemCache {

    public static LRUCache lruCache = new LRUCache(10000);

    public static LRUCache getInstance() {
        return lruCache;
    }

}
