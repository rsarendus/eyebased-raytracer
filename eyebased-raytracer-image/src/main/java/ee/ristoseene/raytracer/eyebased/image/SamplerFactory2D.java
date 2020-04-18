package ee.ristoseene.raytracer.eyebased.image;

@FunctionalInterface
public interface SamplerFactory2D {

    Sampler2D createSamplerFor(Image2D.Readable image);

}
