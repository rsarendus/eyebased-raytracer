package ee.ristoseene.raytracer.eyebased.core.vecmath.vector2;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector2;

public class MutableVector2 implements Vector2.AccessibleAndMutable {

	protected double x;
	protected double y;

	public MutableVector2() {}

	public MutableVector2(double v) {
		this.x = v;
		this.y = v;
	}

	public MutableVector2(Value.Accessible v) {
		this.x = v.get();
		this.y = v.get();
	}

	public MutableVector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public MutableVector2(Value.Accessible x, Value.Accessible y) {
		this.x = x.get();
		this.y = y.get();
	}

	public MutableVector2(Vector2.Accessible xy) {
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

	@Override
	public void x(double x) {
		this.x = x;
	}

	@Override
	public void y(double y) {
		this.y = y;
	}

	@Override
	public void xy(double v) {
		this.x = v;
		this.y = v;
	}

	@Override
	public void xy(double x, double y) {
		this.x = x;
		this.y = y;
	}

}

