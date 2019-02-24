package ee.ristoseene.raytracer.eyebased.core.vecmath.vector2;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector2;

public class ImmutableVector2 implements Vector2.Accessible {

	protected final double x;
	protected final double y;

	public ImmutableVector2(double v) {
		this.x = v;
		this.y = v;
	}

	public ImmutableVector2(Value.Accessible v) {
		this.x = v.get();
		this.y = v.get();
	}

	public ImmutableVector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public ImmutableVector2(Value.Accessible x, Value.Accessible y) {
		this.x = x.get();
		this.y = y.get();
	}

	public ImmutableVector2(Vector2.Accessible xy) {
		this.x = xy.x();
		this.y = xy.y();
	}

	@Override
	public double x() {
		return this.x;
	}

	@Override
	public double y() {
		return this.y;
	}

}

