package ee.ristoseene.raytracer.eyebased.core.vecmath.vector4;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector2;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector3;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;

public class MutableVector4 implements Vector4.AccessibleAndMutable {

	protected double x;
	protected double y;
	protected double z;
	protected double w;

	public MutableVector4() {}

	public MutableVector4(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
		this.w = v;
	}

	public MutableVector4(Value.Accessible v) {
		this.x = v.get();
		this.y = v.get();
		this.z = v.get();
		this.w = v.get();
	}

	public MutableVector4(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public MutableVector4(Value.Accessible x, Value.Accessible y, Value.Accessible z, Value.Accessible w) {
		this.x = x.get();
		this.y = y.get();
		this.z = z.get();
		this.w = w.get();
	}

	public MutableVector4(double x, double y, Vector2.Accessible zw) {
		this.x = x;
		this.y = y;
		this.z = zw.x();
		this.w = zw.y();
	}

	public MutableVector4(Value.Accessible x, Value.Accessible y, Vector2.Accessible zw) {
		this.x = x.get();
		this.y = y.get();
		this.z = zw.x();
		this.w = zw.y();
	}

	public MutableVector4(double x, Vector2.Accessible yz, double w) {
		this.x = x;
		this.y = yz.x();
		this.z = yz.y();
		this.w = w;
	}

	public MutableVector4(Value.Accessible x, Vector2.Accessible yz, Value.Accessible w) {
		this.x = x.get();
		this.y = yz.x();
		this.z = yz.y();
		this.w = w.get();
	}

	public MutableVector4(double x, Vector3.Accessible yzw) {
		this.x = x;
		this.y = yzw.x();
		this.z = yzw.y();
		this.w = yzw.z();
	}

	public MutableVector4(Value.Accessible x, Vector3.Accessible yzw) {
		this.x = x.get();
		this.y = yzw.x();
		this.z = yzw.y();
		this.w = yzw.z();
	}

	public MutableVector4(Vector2.Accessible xy, double z, double w) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z;
		this.w = w;
	}

	public MutableVector4(Vector2.Accessible xy, Value.Accessible z, Value.Accessible w) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = z.get();
		this.w = w.get();
	}

	public MutableVector4(Vector2.Accessible xy, Vector2.Accessible zw) {
		this.x = xy.x();
		this.y = xy.y();
		this.z = zw.x();
		this.w = zw.y();
	}

	public MutableVector4(Vector3.Accessible xyz, double w) {
		this.x = xyz.x();
		this.y = xyz.y();
		this.z = xyz.z();
		this.w = w;
	}

	public MutableVector4(Vector3.Accessible xyz, Value.Accessible w) {
		this.x = xyz.x();
		this.y = xyz.y();
		this.z = xyz.z();
		this.w = w.get();
	}

	public MutableVector4(Vector4.Accessible xyzw) {
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
	public void w(double w) {
		this.w = w;
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
	public void xw(double v) {
		this.x = v;
		this.w = v;
	}

	@Override
	public void yz(double v) {
		this.y = v;
		this.z = v;
	}

	@Override
	public void yw(double v) {
		this.y = v;
		this.w = v;
	}

	@Override
	public void zw(double v) {
		this.z = v;
		this.w = v;
	}

	@Override
	public void xyz(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
	}

	@Override
	public void xyw(double v) {
		this.x = v;
		this.y = v;
		this.w = v;
	}

	@Override
	public void xzw(double v) {
		this.x = v;
		this.z = v;
		this.w = v;
	}

	@Override
	public void yzw(double v) {
		this.y = v;
		this.z = v;
		this.w = v;
	}

	@Override
	public void xyzw(double v) {
		this.x = v;
		this.y = v;
		this.z = v;
		this.w = v;
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
	public void xw(double x, double w) {
		this.x = x;
		this.w = w;
	}

	@Override
	public void yz(double y, double z) {
		this.y = y;
		this.z = z;
	}

	@Override
	public void yw(double y, double w) {
		this.y = y;
		this.w = w;
	}

	@Override
	public void zw(double z, double w) {
		this.z = z;
		this.w = w;
	}

	@Override
	public void xyz(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void xyw(double x, double y, double w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public void xzw(double x, double z, double w) {
		this.x = x;
		this.z = z;
		this.w = w;
	}

	@Override
	public void yzw(double y, double z, double w) {
		this.y = y;
		this.z = z;
		this.w = w;
	}

	@Override
	public void xyzw(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

}

