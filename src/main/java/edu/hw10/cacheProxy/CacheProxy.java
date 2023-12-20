package edu.hw10.cacheProxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class CacheProxy implements InvocationHandler {
    private Path cachingPath;
    private final Object instance;
    private final Map<Object[], Object> cacheMap;
    private final Map<Method, Cache> cachedMethods;

    private CacheProxy(Object instance, Path cachingPath) {
        this.cachingPath = Path.of(cachingPath.toString());
        this.instance = instance;
        cacheMap = new HashMap<>();
        cachedMethods = new HashMap<>();
    }

    public static Object create(Object object, Class<?> cls, Path dir) {
        return Proxy.newProxyInstance(
            cls.getClassLoader(),
            new Class<?>[] {cls},
            new CacheProxy(object, dir)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException {
        for (var entry : cacheMap.entrySet()) {
            if (Arrays.equals(entry.getKey(), args)) {
                return entry.getValue();
            }
        }

        Object result;
        synchronized (instance) {
            result = method.invoke(instance, args);
        }
        // check annotation and write to file
        Cache annotation = cachedMethods.getOrDefault(method, method.getAnnotation(Cache.class));
        if (annotation != null) {
            cacheMap.put(args, result);
            cachedMethods.putIfAbsent(method, annotation);
            if (annotation.persist()) {
                try {
                    writeCacheToFile(method, args, result);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            cachedMethods.put(method, null);
        }

        return result;
    }

    private void writeCacheToFile(Method method, Object[] args, Object result) throws IOException {
        if (Files.isDirectory(cachingPath)) {
            cachingPath = Files.createTempFile(cachingPath, instance.toString(), ".tmp");
        }
        if (!Files.exists(cachingPath)) {
            Files.createFile(cachingPath);
        }
        Files.write(
            cachingPath,
            ("Method=" + method.toString().substring(method.toString().lastIndexOf(".") + 1)
                + " args=" + Arrays.toString(args)
                + " returned=" + result.toString() + "\n"
            ).getBytes(),
            StandardOpenOption.APPEND
        );
    }
}
