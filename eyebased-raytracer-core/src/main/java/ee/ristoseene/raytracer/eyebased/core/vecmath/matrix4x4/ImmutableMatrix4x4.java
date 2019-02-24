package ee.ristoseene.raytracer.eyebased.core.vecmath.matrix4x4;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Matrix4x4;

public class ImmutableMatrix4x4 implements Matrix4x4.Accessible {

	protected final double Xx;
	protected final double Xy;
	protected final double Xz;
	protected final double Xw;
	protected final double Yx;
	protected final double Yy;
	protected final double Yz;
	protected final double Yw;
	protected final double Zx;
	protected final double Zy;
	protected final double Zz;
	protected final double Zw;
	protected final double Tx;
	protected final double Ty;
	protected final double Tz;
	protected final double Tw;

	public ImmutableMatrix4x4(double v) {
		this.Xx = v;
		this.Xy = v;
		this.Xz = v;
		this.Xw = v;
		this.Yx = v;
		this.Yy = v;
		this.Yz = v;
		this.Yw = v;
		this.Zx = v;
		this.Zy = v;
		this.Zz = v;
		this.Zw = v;
		this.Tx = v;
		this.Ty = v;
		this.Tz = v;
		this.Tw = v;
	}

	public ImmutableMatrix4x4(Value.Accessible v) {
		this.Xx = v.get();
		this.Xy = v.get();
		this.Xz = v.get();
		this.Xw = v.get();
		this.Yx = v.get();
		this.Yy = v.get();
		this.Yz = v.get();
		this.Yw = v.get();
		this.Zx = v.get();
		this.Zy = v.get();
		this.Zz = v.get();
		this.Zw = v.get();
		this.Tx = v.get();
		this.Ty = v.get();
		this.Tz = v.get();
		this.Tw = v.get();
	}

	public ImmutableMatrix4x4(double Xx, double Xy, double Xz, double Xw, double Yx, double Yy, double Yz, double Yw, double Zx, double Zy, double Zz, double Zw, double Tx, double Ty, double Tz, double Tw) {
		this.Xx = Xx;
		this.Xy = Xy;
		this.Xz = Xz;
		this.Xw = Xw;
		this.Yx = Yx;
		this.Yy = Yy;
		this.Yz = Yz;
		this.Yw = Yw;
		this.Zx = Zx;
		this.Zy = Zy;
		this.Zz = Zz;
		this.Zw = Zw;
		this.Tx = Tx;
		this.Ty = Ty;
		this.Tz = Tz;
		this.Tw = Tw;
	}

	public ImmutableMatrix4x4(Value.Accessible Xx, Value.Accessible Xy, Value.Accessible Xz, Value.Accessible Xw, Value.Accessible Yx, Value.Accessible Yy, Value.Accessible Yz, Value.Accessible Yw, Value.Accessible Zx, Value.Accessible Zy, Value.Accessible Zz, Value.Accessible Zw, Value.Accessible Tx, Value.Accessible Ty, Value.Accessible Tz, Value.Accessible Tw) {
		this.Xx = Xx.get();
		this.Xy = Xy.get();
		this.Xz = Xz.get();
		this.Xw = Xw.get();
		this.Yx = Yx.get();
		this.Yy = Yy.get();
		this.Yz = Yz.get();
		this.Yw = Yw.get();
		this.Zx = Zx.get();
		this.Zy = Zy.get();
		this.Zz = Zz.get();
		this.Zw = Zw.get();
		this.Tx = Tx.get();
		this.Ty = Ty.get();
		this.Tz = Tz.get();
		this.Tw = Tw.get();
	}

	public ImmutableMatrix4x4(Vector4.Accessible X, Vector4.Accessible Y, Vector4.Accessible Z, Vector4.Accessible T) {
		this.Xx = X.x();
		this.Xy = X.y();
		this.Xz = X.z();
		this.Xw = X.w();
		this.Yx = Y.x();
		this.Yy = Y.y();
		this.Yz = Y.z();
		this.Yw = Y.w();
		this.Zx = Z.x();
		this.Zy = Z.y();
		this.Zz = Z.z();
		this.Zw = Z.w();
		this.Tx = T.x();
		this.Ty = T.y();
		this.Tz = T.z();
		this.Tw = T.w();
	}

	public ImmutableMatrix4x4(Matrix4x4.Accessible m) {
		this.Xx = m.Xx();
		this.Xy = m.Xy();
		this.Xz = m.Xz();
		this.Xw = m.Xw();
		this.Yx = m.Yx();
		this.Yy = m.Yy();
		this.Yz = m.Yz();
		this.Yw = m.Yw();
		this.Zx = m.Zx();
		this.Zy = m.Zy();
		this.Zz = m.Zz();
		this.Zw = m.Zw();
		this.Tx = m.Tx();
		this.Ty = m.Ty();
		this.Tz = m.Tz();
		this.Tw = m.Tw();
	}

	@Override
	public double Xx() {
		return this.Xx;
	}

	@Override
	public double Xy() {
		return this.Xy;
	}

	@Override
	public double Xz() {
		return this.Xz;
	}

	@Override
	public double Xw() {
		return this.Xw;
	}

	@Override
	public double Yx() {
		return this.Yx;
	}

	@Override
	public double Yy() {
		return this.Yy;
	}

	@Override
	public double Yz() {
		return this.Yz;
	}

	@Override
	public double Yw() {
		return this.Yw;
	}

	@Override
	public double Zx() {
		return this.Zx;
	}

	@Override
	public double Zy() {
		return this.Zy;
	}

	@Override
	public double Zz() {
		return this.Zz;
	}

	@Override
	public double Zw() {
		return this.Zw;
	}

	@Override
	public double Tx() {
		return this.Tx;
	}

	@Override
	public double Ty() {
		return this.Ty;
	}

	@Override
	public double Tz() {
		return this.Tz;
	}

	@Override
	public double Tw() {
		return this.Tw;
	}

}

