package ee.ristoseene.raytracer.eyebased.core.vecmath.vector3;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector2;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector3;

public class MutableVector3 implements Vector3.AccessibleAndMutable {

	protected double x;
	protected double y;
	protected double z;

	public MutableVector3() {}

	public MutableVector3(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
	}

	public MutableVector3(Value.Accessible v) {
		this.x = v.get();
		this.y = v.get();
		this.z = v.get();
	}

	public MutableVector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MutableVector3(Value.Accessible x, Value.Accessible y, Value.Accessible z) {
		this.x = x.get();
		this.y = y.get();
		this.z = z.get();
	}

	public MutableVector3(double x, Vector2.Accessible yz) {
		this.x = x;
		this.y = yz.x();
		this.z = yz.y();
	}

	public MutableVector3(Value.Accessible x, Vector2.Accessible yz) {
		this.x = x.get();
		this.y = yz.x();
		this.z = yz.y();
	}

	public MutableVector3(Vector2.Accessible xy, double z) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z;
	}

	public MutableVector3(Vector2.Accessible xy, Value.Accessible z) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z.get();
	}

	public MutableVector3(Vector3.Accessible xyz) {
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

	@Override
	public void x(double x) {
		this.x = x;
	}

	@Override
	public void y(double y) {
		this.y = y;
	}

	@Override
	public void z(double z) {
		this.z = z;
	}

	@Override
	public void xy(double v) {
		this.x = v;
		this.y = v;
	}

	@Override
	public void xz(double v) {
		this.x = v;
		this.z = v;
	}

	@Override
	public void yz(double v) {
		this.y = v;
		this.z = v;
	}

	@Override
	public void xyz(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
	}

	@Override
	public void xy(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void xz(double x, double z) {
		this.x = x;
		this.z = z;
	}

	@Override
	public void yz(double y, double z) {
		this.y = y;
		this.z = z;
	}

	@Override
	public void xyz(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}

