package ee.ristoseene.raytracer.eyebased.core.vecmath.vector3;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector2;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector3;

public class ImmutableVector3 implements Vector3.Accessible {

	protected final double x;
	protected final double y;
	protected final double z;

	public ImmutableVector3(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
	}

	public ImmutableVector3(Value.Accessible v) {
		this.x = v.get();
		this.y = v.get();
		this.z = v.get();
	}

	public ImmutableVector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public ImmutableVector3(Value.Accessible x, Value.Accessible y, Value.Accessible z) {
		this.x = x.get();
		this.y = y.get();
		this.z = z.get();
	}

	public ImmutableVector3(double x, Vector2.Accessible yz) {
		this.x = x;
		this.y = yz.x();
		this.z = yz.y();
	}

	public ImmutableVector3(Value.Accessible x, Vector2.Accessible yz) {
		this.x = x.get();
		this.y = yz.x();
		this.z = yz.y();
	}

	public ImmutableVector3(Vector2.Accessible xy, double z) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z;
	}

	public ImmutableVector3(Vector2.Accessible xy, Value.Accessible z) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z.get();
	}

	public ImmutableVector3(Vector3.Accessible xyz) {
		this.x = xyz.x();
		this.y = xyz.y();
		this.z = xyz.z();
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
	public double z() {
		return this.z;
	}

}

