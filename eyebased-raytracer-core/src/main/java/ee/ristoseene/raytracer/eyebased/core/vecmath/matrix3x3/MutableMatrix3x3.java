package ee.ristoseene.raytracer.eyebased.core.vecmath.matrix3x3;

import ee.ristoseene.raytracer.eyebased.core.vecmath.Value;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Vector3;
import ee.ristoseene.raytracer.eyebased.core.vecmath.Matrix3x3;

public class MutableMatrix3x3 implements Matrix3x3.AccessibleAndMutable {

	protected double Xx;
	protected double Xy;
	protected double Xz;
	protected double Yx;
	protected double Yy;
	protected double Yz;
	protected double Zx;
	protected double Zy;
	protected double Zz;

	public MutableMatrix3x3() {}

	public MutableMatrix3x3(double v) {
		this.Xx = v;
		this.Xy = v;
		this.Xz = v;
		this.Yx = v;
		this.Yy = v;
		this.Yz = v;
		this.Zx = v;
		this.Zy = v;
		this.Zz = v;
	}

	public MutableMatrix3x3(Value.Accessible v) {
		this.Xx = v.get();
		this.Xy = v.get();
		this.Xz = v.get();
		this.Yx = v.get();
		this.Yy = v.get();
		this.Yz = v.get();
		this.Zx = v.get();
		this.Zy = v.get();
		this.Zz = v.get();
	}

	public MutableMatrix3x3(double Xx, double Xy, double Xz, double Yx, double Yy, double Yz, double Zx, double Zy, double Zz) {
		this.Xx = Xx;
		this.Xy = Xy;
		this.Xz = Xz;
		this.Yx = Yx;
		this.Yy = Yy;
		this.Yz = Yz;
		this.Zx = Zx;
		this.Zy = Zy;
		this.Zz = Zz;
	}

	public MutableMatrix3x3(Value.Accessible Xx, Value.Accessible Xy, Value.Accessible Xz, Value.Accessible Yx, Value.Accessible Yy, Value.Accessible Yz, Value.Accessible Zx, Value.Accessible Zy, Value.Accessible Zz) {
		this.Xx = Xx.get();
		this.Xy = Xy.get();
		this.Xz = Xz.get();
		this.Yx = Yx.get();
		this.Yy = Yy.get();
		this.Yz = Yz.get();
		this.Zx = Zx.get();
		this.Zy = Zy.get();
		this.Zz = Zz.get();
	}

	public MutableMatrix3x3(Vector3.Accessible X, Vector3.Accessible Y, Vector3.Accessible Z) {
		this.Xx = X.x();
		this.Xy = X.y();
		this.Xz = X.z();
		this.Yx = Y.x();
		this.Yy = Y.y();
		this.Yz = Y.z();
		this.Zx = Z.x();
		this.Zy = Z.y();
		this.Zz = Z.z();
	}

	public MutableMatrix3x3(Matrix3x3.Accessible m) {
		this.Xx = m.Xx();
		this.Xy = m.Xy();
		this.Xz = m.Xz();
		this.Yx = m.Yx();
		this.Yy = m.Yy();
		this.Yz = m.Yz();
		this.Zx = m.Zx();
		this.Zy = m.Zy();
		this.Zz = m.Zz();
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

}

