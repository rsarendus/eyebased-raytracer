package ee.ristoseene.raytracer.eyebased.core.vecmath.vector4;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector2;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector3;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;

public class ImmutableVector4 implements Vector4.Accessible {

	protected final double x;
	protected final double y;
	protected final double z;
	protected final double w;

	public ImmutableVector4(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
		this.w = v;
	}

	public ImmutableVector4(Value.Accessible v) {
		this.x = v.get();
		this.y = v.get();
		this.z = v.get();
		this.w = v.get();
	}

	public ImmutableVector4(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public ImmutableVector4(Value.Accessible x, Value.Accessible y, Value.Accessible z, Value.Accessible w) {
		this.x = x.get();
		this.y = y.get();
		this.z = z.get();
		this.w = w.get();
	}

	public ImmutableVector4(double x, double y, Vector2.Accessible zw) {
		this.x = x;
		this.y = y;
		this.z = zw.x();
		this.w = zw.y();
	}

	public ImmutableVector4(Value.Accessible x, Value.Accessible y, Vector2.Accessible zw) {
		this.x = x.get();
		this.y = y.get();
		this.z = zw.x();
		this.w = zw.y();
	}

	public ImmutableVector4(double x, Vector2.Accessible yz, double w) {
		this.x = x;
		this.y = yz.x();
		this.z = yz.y();
		this.w = w;
	}

	public ImmutableVector4(Value.Accessible x, Vector2.Accessible yz, Value.Accessible w) {
		this.x = x.get();
		this.y = yz.x();
		this.z = yz.y();
		this.w = w.get();
	}

	public ImmutableVector4(double x, Vector3.Accessible yzw) {
		this.x = x;
		this.y = yzw.x();
		this.z = yzw.y();
		this.w = yzw.z();
	}

	public ImmutableVector4(Value.Accessible x, Vector3.Accessible yzw) {
		this.x = x.get();
		this.y = yzw.x();
		this.z = yzw.y();
		this.w = yzw.z();
	}

	public ImmutableVector4(Vector2.Accessible xy, double z, double w) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z;
		this.w = w;
	}

	public ImmutableVector4(Vector2.Accessible xy, Value.Accessible z, Value.Accessible w) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z.get();
		this.w = w.get();
	}

	public ImmutableVector4(Vector2.Accessible xy, Vector2.Accessible zw) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = zw.x();
		this.w = zw.y();
	}

	public ImmutableVector4(Vector3.Accessible xyz, double w) {
		this.x = xyz.x();
		this.y = xyz.y();
		this.z = xyz.z();
		this.w = w;
	}

	public ImmutableVector4(Vector3.Accessible xyz, Value.Accessible w) {
		this.x = xyz.x();
		this.y = xyz.y();
		this.z = xyz.z();
		this.w = w.get();
	}

	public ImmutableVector4(Vector4.Accessible xyzw) {
		this.x = xyzw.x();
		this.y = xyzw.y();
		this.z = xyzw.z();
		this.w = xyzw.w();
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

	@Override
	public double w() {
		return this.w;
	}

}

