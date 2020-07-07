package ee.ristoseene.raytracer.eyebased.demo.scene;

import ee.ristoseene.raytracer.eyebased.geometry.CompilableGeometry;

import java.util.stream.Stream;

public interface Scenery {

    Camera createDefaultCamera();

    Stream<CompilableGeometry> getGeometry();

}
