package ee.ristoseene.raytracer.eyebased.core.compilation.cache;

import ee.ristoseene.raytracer.eyebased.core.compilation.Compilable;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompilationCache;
import ee.ristoseene.raytracer.eyebased.core.compilation.CompiledObject;

import java.util.Map;
import java.util.Objects;

public class MapBasedCompilationCache implements CompilationCache {

    private final Map<Compilable, CompiledObject> backingMap;

    public MapBasedCompilationCache(Map<Compilable, CompiledObject> backingMap) {
        this.backingMap = Objects.requireNonNull(backingMap, "Map cannot be null");
    }

    @Override
    public void put(Compilable compilable, CompiledObject compilationResult) {
        backingMap.put(compilable, compilationResult);
    }

    @Override
    public CompiledObject get(Compilable compilable) {
        return backingMap.get(compilable);
    }

}
