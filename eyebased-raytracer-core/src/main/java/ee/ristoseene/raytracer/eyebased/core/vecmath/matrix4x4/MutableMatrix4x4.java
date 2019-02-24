package ee.ristoseene.raytracer.eyebased.core.vecmath.matrix4x4;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector4;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Matrix4x4;

public class MutableMatrix4x4 implements Matrix4x4.AccessibleAndMutable {

	protected double Xx;
	protected double Xy;
	protected double Xz;
	protected double Xw;
	protected double Yx;
	protected double Yy;
	protected double Yz;
	protected double Yw;
	protected double Zx;
	protected double Zy;
	protected double Zz;
	protected double Zw;
	protected double Tx;
	protected double Ty;
	protected double Tz;
	protected double Tw;

	public MutableMatrix4x4() {}

	public MutableMatrix4x4(double v) {
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

	public MutableMatrix4x4(Value.Accessible v) {
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

	public MutableMatrix4x4(double Xx, double Xy, double Xz, double Xw, double Yx, double Yy, double Yz, double Yw, double Zx, double Zy, double Zz, double Zw, double Tx, double Ty, double Tz, double Tw) {
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

	public MutableMatrix4x4(Value.Accessible Xx, Value.Accessible Xy, Value.Accessible Xz, Value.Accessible Xw, Value.Accessible Yx, Value.Accessible Yy, Value.Accessible Yz, Value.Accessible Yw, Value.Accessible Zx, Value.Accessible Zy, Value.Accessible Zz, Value.Accessible Zw, Value.Accessible Tx, Value.Accessible Ty, Value.Accessible Tz, Value.Accessible Tw) {
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

	public MutableMatrix4x4(Vector4.Accessible X, Vector4.Accessible Y, Vector4.Accessible Z, Vector4.Accessible T) {
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

	public MutableMatrix4x4(Matrix4x4.Accessible m) {
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

	@Override
	public void Xx(double Xx) {
		this.Xx = Xx;
	}

	@Override
	public void Xy(double Xy) {
		this.Xy = Xy;
	}

	@Override
	public void Xz(double Xz) {
		this.Xz = Xz;
	}

	@Override
	public void Xw(double Xw) {
		this.Xw = Xw;
	}

	@Override
	public void Yx(double Yx) {
		this.Yx = Yx;
	}

	@Override
	public void Yy(double Yy) {
		this.Yy = Yy;
	}

	@Override
	public void Yz(double Yz) {
		this.Yz = Yz;
	}

	@Override
	public void Yw(double Yw) {
		this.Yw = Yw;
	}

	@Override
	public void Zx(double Zx) {
		this.Zx = Zx;
	}

	@Override
	public void Zy(double Zy) {
		this.Zy = Zy;
	}

	@Override
	public void Zz(double Zz) {
		this.Zz = Zz;
	}

	@Override
	public void Zw(double Zw) {
		this.Zw = Zw;
	}

	@Override
	public void Tx(double Tx) {
		this.Tx = Tx;
	}

	@Override
	public void Ty(double Ty) {
		this.Ty = Ty;
	}

	@Override
	public void Tz(double Tz) {
		this.Tz = Tz;
	}

	@Override
	public void Tw(double Tw) {
		this.Tw = Tw;
	}

}

