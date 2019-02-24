package ee.ristoseene.raytracer.eyebased.core.vecmath;

public final class VecMath {

	private VecMath() {}

	public static double min(double value1, double value2) {
		return (value1 < value2) ? value1 : value2;
	}

	public static double max(double value1, double value2) {
		return (value1 > value2) ? value1 : value2;
	}

	public static double clamp(double value, double min, double max) {
		return (value > min) ? (value < max ? value : max) : min;
	}

	public static void lerp(Vector2.Accessible v0, Vector2.Accessible v1, double t, Vector2.Mutable result) {
		result.xy(
				v0.x() * (1.0 - t) + v1.x() * t,
				v0.y() * (1.0 - t) + v1.y() * t
		);
	}

	public static void negate(Vector2.Accessible vector, Vector2.Mutable result) {
		result.xy(
				-vector.x(),
				-vector.y()
		);
	}

	public static void add(Vector2.Accessible left, double right, Vector2.Mutable result) {
		result.xy(
				left.x() + right,
				left.y() + right
		);
	}

	public static void add(Vector2.Accessible left, Vector2.Accessible right, Vector2.Mutable result) {
		result.xy(
				left.x() + right.x(),
				left.y() + right.y()
		);
	}

	public static void subtract(Vector2.Accessible left, double right, Vector2.Mutable result) {
		result.xy(
				left.x() - right,
				left.y() - right
		);
	}

	public static void subtract(Vector2.Accessible left, Vector2.Accessible right, Vector2.Mutable result) {
		result.xy(
				left.x() - right.x(),
				left.y() - right.y()
		);
	}

	public static void multiply(Vector2.Accessible left, double right, Vector2.Mutable result) {
		result.xy(
				left.x() * right,
				left.y() * right
		);
	}

	public static void multiply(Vector2.Accessible left, Vector2.Accessible right, Vector2.Mutable result) {
		result.xy(
				left.x() * right.x(),
				left.y() * right.y()
		);
	}

	public static void divide(Vector2.Accessible left, double right, Vector2.Mutable result) {
		result.xy(
				left.x() / right,
				left.y() / right
		);
	}

	public static void divide(Vector2.Accessible left, Vector2.Accessible right, Vector2.Mutable result) {
		result.xy(
				left.x() / right.x(),
				left.y() / right.y()
		);
	}

	public static void madd(Vector2.Accessible left, double middle, double right, Vector2.Mutable result) {
		result.xy(
				left.x() * middle + right,
				left.y() * middle + right
		);
	}

	public static void madd(Vector2.Accessible left, Vector2.Accessible middle, double right, Vector2.Mutable result) {
		result.xy(
				left.x() * middle.x() + right,
				left.y() * middle.y() + right
		);
	}

	public static void madd(Vector2.Accessible left, double middle, Vector2.Accessible right, Vector2.Mutable result) {
		result.xy(
				left.x() * middle + right.x(),
				left.y() * middle + right.y()
		);
	}

	public static void madd(Vector2.Accessible left, Vector2.Accessible middle, Vector2.Accessible right, Vector2.Mutable result) {
		result.xy(
				left.x() * middle.x() + right.x(),
				left.y() * middle.y() + right.y()
		);
	}

	public static double dot(Vector2.Accessible a, double b) {
		return a.x() * b + a.y() * b;
	}

	public static double dot(Vector2.Accessible a, Vector2.Accessible b) {
		return a.x() * b.x() + a.y() * b.y();
	}

	public static void lerp(Vector3.Accessible v0, Vector3.Accessible v1, double t, Vector3.Mutable result) {
		result.xyz(
				v0.x() * (1.0 - t) + v1.x() * t,
				v0.y() * (1.0 - t) + v1.y() * t,
				v0.z() * (1.0 - t) + v1.z() * t
		);
	}

	public static void negate(Vector3.Accessible vector, Vector3.Mutable result) {
		result.xyz(
				-vector.x(),
				-vector.y(),
				-vector.z()
		);
	}

	public static void add(Vector3.Accessible left, double right, Vector3.Mutable result) {
		result.xyz(
				left.x() + right,
				left.y() + right,
				left.z() + right
		);
	}

	public static void add(Vector3.Accessible left, Vector3.Accessible right, Vector3.Mutable result) {
		result.xyz(
				left.x() + right.x(),
				left.y() + right.y(),
				left.z() + right.z()
		);
	}

	public static void subtract(Vector3.Accessible left, double right, Vector3.Mutable result) {
		result.xyz(
				left.x() - right,
				left.y() - right,
				left.z() - right
		);
	}

	public static void subtract(Vector3.Accessible left, Vector3.Accessible right, Vector3.Mutable result) {
		result.xyz(
				left.x() - right.x(),
				left.y() - right.y(),
				left.z() - right.z()
		);
	}

	public static void multiply(Vector3.Accessible left, double right, Vector3.Mutable result) {
		result.xyz(
				left.x() * right,
				left.y() * right,
				left.z() * right
		);
	}

	public static void multiply(Vector3.Accessible left, Vector3.Accessible right, Vector3.Mutable result) {
		result.xyz(
				left.x() * right.x(),
				left.y() * right.y(),
				left.z() * right.z()
		);
	}

	public static void divide(Vector3.Accessible left, double right, Vector3.Mutable result) {
		result.xyz(
				left.x() / right,
				left.y() / right,
				left.z() / right
		);
	}

	public static void divide(Vector3.Accessible left, Vector3.Accessible right, Vector3.Mutable result) {
		result.xyz(
				left.x() / right.x(),
				left.y() / right.y(),
				left.z() / right.z()
		);
	}

	public static void madd(Vector3.Accessible left, double middle, double right, Vector3.Mutable result) {
		result.xyz(
				left.x() * middle + right,
				left.y() * middle + right,
				left.z() * middle + right
		);
	}

	public static void madd(Vector3.Accessible left, Vector3.Accessible middle, double right, Vector3.Mutable result) {
		result.xyz(
				left.x() * middle.x() + right,
				left.y() * middle.y() + right,
				left.z() * middle.z() + right
		);
	}

	public static void madd(Vector3.Accessible left, double middle, Vector3.Accessible right, Vector3.Mutable result) {
		result.xyz(
				left.x() * middle + right.x(),
				left.y() * middle + right.y(),
				left.z() * middle + right.z()
		);
	}

	public static void madd(Vector3.Accessible left, Vector3.Accessible middle, Vector3.Accessible right, Vector3.Mutable result) {
		result.xyz(
				left.x() * middle.x() + right.x(),
				left.y() * middle.y() + right.y(),
				left.z() * middle.z() + right.z()
		);
	}

	public static double dot(Vector3.Accessible a, double b) {
		return a.x() * b + a.y() * b + a.z() * b;
	}

	public static double dot(Vector3.Accessible a, Vector3.Accessible b) {
		return a.x() * b.x() + a.y() * b.y() + a.z() * b.z();
	}

	public static void lerp(Vector4.Accessible v0, Vector4.Accessible v1, double t, Vector4.Mutable result) {
		result.xyzw(
				v0.x() * (1.0 - t) + v1.x() * t,
				v0.y() * (1.0 - t) + v1.y() * t,
				v0.z() * (1.0 - t) + v1.z() * t,
				v0.w() * (1.0 - t) + v1.w() * t
		);
	}

	public static void negate(Vector4.Accessible vector, Vector4.Mutable result) {
		result.xyzw(
				-vector.x(),
				-vector.y(),
				-vector.z(),
				-vector.w()
		);
	}

	public static void add(Vector4.Accessible left, double right, Vector4.Mutable result) {
		result.xyzw(
				left.x() + right,
				left.y() + right,
				left.z() + right,
				left.w() + right
		);
	}

	public static void add(Vector4.Accessible left, Vector4.Accessible right, Vector4.Mutable result) {
		result.xyzw(
				left.x() + right.x(),
				left.y() + right.y(),
				left.z() + right.z(),
				left.w() + right.w()
		);
	}

	public static void subtract(Vector4.Accessible left, double right, Vector4.Mutable result) {
		result.xyzw(
				left.x() - right,
				left.y() - right,
				left.z() - right,
				left.w() - right
		);
	}

	public static void subtract(Vector4.Accessible left, Vector4.Accessible right, Vector4.Mutable result) {
		result.xyzw(
				left.x() - right.x(),
				left.y() - right.y(),
				left.z() - right.z(),
				left.w() - right.w()
		);
	}

	public static void multiply(Vector4.Accessible left, double right, Vector4.Mutable result) {
		result.xyzw(
				left.x() * right,
				left.y() * right,
				left.z() * right,
				left.w() * right
		);
	}

	public static void multiply(Vector4.Accessible left, Vector4.Accessible right, Vector4.Mutable result) {
		result.xyzw(
				left.x() * right.x(),
				left.y() * right.y(),
				left.z() * right.z(),
				left.w() * right.w()
		);
	}

	public static void divide(Vector4.Accessible left, double right, Vector4.Mutable result) {
		result.xyzw(
				left.x() / right,
				left.y() / right,
				left.z() / right,
				left.w() / right
		);
	}

	public static void divide(Vector4.Accessible left, Vector4.Accessible right, Vector4.Mutable result) {
		result.xyzw(
				left.x() / right.x(),
				left.y() / right.y(),
				left.z() / right.z(),
				left.w() / right.w()
		);
	}

	public static void madd(Vector4.Accessible left, double middle, double right, Vector4.Mutable result) {
		result.xyzw(
				left.x() * middle + right,
				left.y() * middle + right,
				left.z() * middle + right,
				left.w() * middle + right
		);
	}

	public static void madd(Vector4.Accessible left, Vector4.Accessible middle, double right, Vector4.Mutable result) {
		result.xyzw(
				left.x() * middle.x() + right,
				left.y() * middle.y() + right,
				left.z() * middle.z() + right,
				left.w() * middle.w() + right
		);
	}

	public static void madd(Vector4.Accessible left, double middle, Vector4.Accessible right, Vector4.Mutable result) {
		result.xyzw(
				left.x() * middle + right.x(),
				left.y() * middle + right.y(),
				left.z() * middle + right.z(),
				left.w() * middle + right.w()
		);
	}

	public static void madd(Vector4.Accessible left, Vector4.Accessible middle, Vector4.Accessible right, Vector4.Mutable result) {
		result.xyzw(
				left.x() * middle.x() + right.x(),
				left.y() * middle.y() + right.y(),
				left.z() * middle.z() + right.z(),
				left.w() * middle.w() + right.w()
		);
	}

	public static double dot(Vector4.Accessible a, double b) {
		return a.x() * b + a.y() * b + a.z() * b + a.w() * b;
	}

	public static double dot(Vector4.Accessible a, Vector4.Accessible b) {
		return a.x() * b.x() + a.y() * b.y() + a.z() * b.z() + a.w() * b.w();
	}

	public static void add(Matrix3x3.Accessible left, double right, Matrix3x3.Mutable result) {
		final double Xx = left.Xx() + right;
		final double Xy = left.Xy() + right;
		final double Xz = left.Xz() + right;
		final double Yx = left.Yx() + right;
		final double Yy = left.Yy() + right;
		final double Yz = left.Yz() + right;
		final double Zx = left.Zx() + right;
		final double Zy = left.Zy() + right;
		final double Zz = left.Zz() + right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
	}

	public static void subtract(Matrix3x3.Accessible left, double right, Matrix3x3.Mutable result) {
		final double Xx = left.Xx() - right;
		final double Xy = left.Xy() - right;
		final double Xz = left.Xz() - right;
		final double Yx = left.Yx() - right;
		final double Yy = left.Yy() - right;
		final double Yz = left.Yz() - right;
		final double Zx = left.Zx() - right;
		final double Zy = left.Zy() - right;
		final double Zz = left.Zz() - right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
	}

	public static void multiply(Matrix3x3.Accessible left, double right, Matrix3x3.Mutable result) {
		final double Xx = left.Xx() * right;
		final double Xy = left.Xy() * right;
		final double Xz = left.Xz() * right;
		final double Yx = left.Yx() * right;
		final double Yy = left.Yy() * right;
		final double Yz = left.Yz() * right;
		final double Zx = left.Zx() * right;
		final double Zy = left.Zy() * right;
		final double Zz = left.Zz() * right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
	}

	public static void divide(Matrix3x3.Accessible left, double right, Matrix3x3.Mutable result) {
		final double Xx = left.Xx() / right;
		final double Xy = left.Xy() / right;
		final double Xz = left.Xz() / right;
		final double Yx = left.Yx() / right;
		final double Yy = left.Yy() / right;
		final double Yz = left.Yz() / right;
		final double Zx = left.Zx() / right;
		final double Zy = left.Zy() / right;
		final double Zz = left.Zz() / right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
	}

	public static void add(Matrix3x3.Accessible left, Matrix3x3.Accessible right, Matrix3x3.Mutable result) {
		final double Xx = left.Xx() + right.Xx();
		final double Xy = left.Xy() + right.Xy();
		final double Xz = left.Xz() + right.Xz();
		final double Yx = left.Yx() + right.Yx();
		final double Yy = left.Yy() + right.Yy();
		final double Yz = left.Yz() + right.Yz();
		final double Zx = left.Zx() + right.Zx();
		final double Zy = left.Zy() + right.Zy();
		final double Zz = left.Zz() + right.Zz();

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
	}

	public static void subtract(Matrix3x3.Accessible left, Matrix3x3.Accessible right, Matrix3x3.Mutable result) {
		final double Xx = left.Xx() - right.Xx();
		final double Xy = left.Xy() - right.Xy();
		final double Xz = left.Xz() - right.Xz();
		final double Yx = left.Yx() - right.Yx();
		final double Yy = left.Yy() - right.Yy();
		final double Yz = left.Yz() - right.Yz();
		final double Zx = left.Zx() - right.Zx();
		final double Zy = left.Zy() - right.Zy();
		final double Zz = left.Zz() - right.Zz();

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
	}

	public static void multiply(Matrix3x3.Accessible left, Matrix3x3.Accessible right, Matrix3x3.Mutable result) {
		final double leftXx = left.Xx();
		final double leftXy = left.Xy();
		final double leftXz = left.Xz();
		final double leftYx = left.Yx();
		final double leftYy = left.Yy();
		final double leftYz = left.Yz();
		final double leftZx = left.Zx();
		final double leftZy = left.Zy();
		final double leftZz = left.Zz();

		final double rightXx = right.Xx();
		final double rightXy = right.Xy();
		final double rightXz = right.Xz();
		final double rightYx = right.Yx();
		final double rightYy = right.Yy();
		final double rightYz = right.Yz();
		final double rightZx = right.Zx();
		final double rightZy = right.Zy();
		final double rightZz = right.Zz();

		result.Xx(leftXx * rightXx + leftYx * rightXy + leftZx * rightXz);
		result.Xy(leftXy * rightXx + leftYy * rightXy + leftZy * rightXz);
		result.Xz(leftXz * rightXx + leftYz * rightXy + leftZz * rightXz);
		result.Yx(leftXx * rightYx + leftYx * rightYy + leftZx * rightYz);
		result.Yy(leftXy * rightYx + leftYy * rightYy + leftZy * rightYz);
		result.Yz(leftXz * rightYx + leftYz * rightYy + leftZz * rightYz);
		result.Zx(leftXx * rightZx + leftYx * rightZy + leftZx * rightZz);
		result.Zy(leftXy * rightZx + leftYy * rightZy + leftZy * rightZz);
		result.Zz(leftXz * rightZx + leftYz * rightZy + leftZz * rightZz);
	}

	public static void multiply(Matrix3x3.Accessible left, Vector3.Accessible right, Vector3.Mutable result) {
		final double leftXx = left.Xx();
		final double leftXy = left.Xy();
		final double leftXz = left.Xz();
		final double leftYx = left.Yx();
		final double leftYy = left.Yy();
		final double leftYz = left.Yz();
		final double leftZx = left.Zx();
		final double leftZy = left.Zy();
		final double leftZz = left.Zz();

		final double rightx = right.x();
		final double righty = right.y();
		final double rightz = right.z();

		result.x(leftXx * rightx + leftYx * righty + leftZx * rightz);
		result.y(leftXy * rightx + leftYy * righty + leftZy * rightz);
		result.z(leftXz * rightx + leftYz * righty + leftZz * rightz);
	}

	public static void inverse(Matrix3x3.Accessible matrix, Matrix3x3.Mutable result) {
		final double matrixXx = matrix.Xx();
		final double matrixXy = matrix.Xy();
		final double matrixXz = matrix.Xz();
		final double matrixYx = matrix.Yx();
		final double matrixYy = matrix.Yy();
		final double matrixYz = matrix.Yz();
		final double matrixZx = matrix.Zx();
		final double matrixZy = matrix.Zy();
		final double matrixZz = matrix.Zz();

	}

	public static void add(Matrix4x4.Accessible left, double right, Matrix4x4.Mutable result) {
		final double Xx = left.Xx() + right;
		final double Xy = left.Xy() + right;
		final double Xz = left.Xz() + right;
		final double Xw = left.Xw() + right;
		final double Yx = left.Yx() + right;
		final double Yy = left.Yy() + right;
		final double Yz = left.Yz() + right;
		final double Yw = left.Yw() + right;
		final double Zx = left.Zx() + right;
		final double Zy = left.Zy() + right;
		final double Zz = left.Zz() + right;
		final double Zw = left.Zw() + right;
		final double Tx = left.Tx() + right;
		final double Ty = left.Ty() + right;
		final double Tz = left.Tz() + right;
		final double Tw = left.Tw() + right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Xw(Xw);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Yw(Yw);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
		result.Zw(Zw);
		result.Tx(Tx);
		result.Ty(Ty);
		result.Tz(Tz);
		result.Tw(Tw);
	}

	public static void subtract(Matrix4x4.Accessible left, double right, Matrix4x4.Mutable result) {
		final double Xx = left.Xx() - right;
		final double Xy = left.Xy() - right;
		final double Xz = left.Xz() - right;
		final double Xw = left.Xw() - right;
		final double Yx = left.Yx() - right;
		final double Yy = left.Yy() - right;
		final double Yz = left.Yz() - right;
		final double Yw = left.Yw() - right;
		final double Zx = left.Zx() - right;
		final double Zy = left.Zy() - right;
		final double Zz = left.Zz() - right;
		final double Zw = left.Zw() - right;
		final double Tx = left.Tx() - right;
		final double Ty = left.Ty() - right;
		final double Tz = left.Tz() - right;
		final double Tw = left.Tw() - right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Xw(Xw);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Yw(Yw);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
		result.Zw(Zw);
		result.Tx(Tx);
		result.Ty(Ty);
		result.Tz(Tz);
		result.Tw(Tw);
	}

	public static void multiply(Matrix4x4.Accessible left, double right, Matrix4x4.Mutable result) {
		final double Xx = left.Xx() * right;
		final double Xy = left.Xy() * right;
		final double Xz = left.Xz() * right;
		final double Xw = left.Xw() * right;
		final double Yx = left.Yx() * right;
		final double Yy = left.Yy() * right;
		final double Yz = left.Yz() * right;
		final double Yw = left.Yw() * right;
		final double Zx = left.Zx() * right;
		final double Zy = left.Zy() * right;
		final double Zz = left.Zz() * right;
		final double Zw = left.Zw() * right;
		final double Tx = left.Tx() * right;
		final double Ty = left.Ty() * right;
		final double Tz = left.Tz() * right;
		final double Tw = left.Tw() * right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Xw(Xw);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Yw(Yw);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
		result.Zw(Zw);
		result.Tx(Tx);
		result.Ty(Ty);
		result.Tz(Tz);
		result.Tw(Tw);
	}

	public static void divide(Matrix4x4.Accessible left, double right, Matrix4x4.Mutable result) {
		final double Xx = left.Xx() / right;
		final double Xy = left.Xy() / right;
		final double Xz = left.Xz() / right;
		final double Xw = left.Xw() / right;
		final double Yx = left.Yx() / right;
		final double Yy = left.Yy() / right;
		final double Yz = left.Yz() / right;
		final double Yw = left.Yw() / right;
		final double Zx = left.Zx() / right;
		final double Zy = left.Zy() / right;
		final double Zz = left.Zz() / right;
		final double Zw = left.Zw() / right;
		final double Tx = left.Tx() / right;
		final double Ty = left.Ty() / right;
		final double Tz = left.Tz() / right;
		final double Tw = left.Tw() / right;

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Xw(Xw);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Yw(Yw);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
		result.Zw(Zw);
		result.Tx(Tx);
		result.Ty(Ty);
		result.Tz(Tz);
		result.Tw(Tw);
	}

	public static void add(Matrix4x4.Accessible left, Matrix4x4.Accessible right, Matrix4x4.Mutable result) {
		final double Xx = left.Xx() + right.Xx();
		final double Xy = left.Xy() + right.Xy();
		final double Xz = left.Xz() + right.Xz();
		final double Xw = left.Xw() + right.Xw();
		final double Yx = left.Yx() + right.Yx();
		final double Yy = left.Yy() + right.Yy();
		final double Yz = left.Yz() + right.Yz();
		final double Yw = left.Yw() + right.Yw();
		final double Zx = left.Zx() + right.Zx();
		final double Zy = left.Zy() + right.Zy();
		final double Zz = left.Zz() + right.Zz();
		final double Zw = left.Zw() + right.Zw();
		final double Tx = left.Tx() + right.Tx();
		final double Ty = left.Ty() + right.Ty();
		final double Tz = left.Tz() + right.Tz();
		final double Tw = left.Tw() + right.Tw();

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Xw(Xw);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Yw(Yw);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
		result.Zw(Zw);
		result.Tx(Tx);
		result.Ty(Ty);
		result.Tz(Tz);
		result.Tw(Tw);
	}

	public static void subtract(Matrix4x4.Accessible left, Matrix4x4.Accessible right, Matrix4x4.Mutable result) {
		final double Xx = left.Xx() - right.Xx();
		final double Xy = left.Xy() - right.Xy();
		final double Xz = left.Xz() - right.Xz();
		final double Xw = left.Xw() - right.Xw();
		final double Yx = left.Yx() - right.Yx();
		final double Yy = left.Yy() - right.Yy();
		final double Yz = left.Yz() - right.Yz();
		final double Yw = left.Yw() - right.Yw();
		final double Zx = left.Zx() - right.Zx();
		final double Zy = left.Zy() - right.Zy();
		final double Zz = left.Zz() - right.Zz();
		final double Zw = left.Zw() - right.Zw();
		final double Tx = left.Tx() - right.Tx();
		final double Ty = left.Ty() - right.Ty();
		final double Tz = left.Tz() - right.Tz();
		final double Tw = left.Tw() - right.Tw();

		result.Xx(Xx);
		result.Xy(Xy);
		result.Xz(Xz);
		result.Xw(Xw);
		result.Yx(Yx);
		result.Yy(Yy);
		result.Yz(Yz);
		result.Yw(Yw);
		result.Zx(Zx);
		result.Zy(Zy);
		result.Zz(Zz);
		result.Zw(Zw);
		result.Tx(Tx);
		result.Ty(Ty);
		result.Tz(Tz);
		result.Tw(Tw);
	}

	public static void multiply(Matrix4x4.Accessible left, Matrix4x4.Accessible right, Matrix4x4.Mutable result) {
		final double leftXx = left.Xx();
		final double leftXy = left.Xy();
		final double leftXz = left.Xz();
		final double leftXw = left.Xw();
		final double leftYx = left.Yx();
		final double leftYy = left.Yy();
		final double leftYz = left.Yz();
		final double leftYw = left.Yw();
		final double leftZx = left.Zx();
		final double leftZy = left.Zy();
		final double leftZz = left.Zz();
		final double leftZw = left.Zw();
		final double leftTx = left.Tx();
		final double leftTy = left.Ty();
		final double leftTz = left.Tz();
		final double leftTw = left.Tw();

		final double rightXx = right.Xx();
		final double rightXy = right.Xy();
		final double rightXz = right.Xz();
		final double rightXw = right.Xw();
		final double rightYx = right.Yx();
		final double rightYy = right.Yy();
		final double rightYz = right.Yz();
		final double rightYw = right.Yw();
		final double rightZx = right.Zx();
		final double rightZy = right.Zy();
		final double rightZz = right.Zz();
		final double rightZw = right.Zw();
		final double rightTx = right.Tx();
		final double rightTy = right.Ty();
		final double rightTz = right.Tz();
		final double rightTw = right.Tw();

		result.Xx(leftXx * rightXx + leftYx * rightXy + leftZx * rightXz + leftTx * rightXw);
		result.Xy(leftXy * rightXx + leftYy * rightXy + leftZy * rightXz + leftTy * rightXw);
		result.Xz(leftXz * rightXx + leftYz * rightXy + leftZz * rightXz + leftTz * rightXw);
		result.Xw(leftXw * rightXx + leftYw * rightXy + leftZw * rightXz + leftTw * rightXw);
		result.Yx(leftXx * rightYx + leftYx * rightYy + leftZx * rightYz + leftTx * rightYw);
		result.Yy(leftXy * rightYx + leftYy * rightYy + leftZy * rightYz + leftTy * rightYw);
		result.Yz(leftXz * rightYx + leftYz * rightYy + leftZz * rightYz + leftTz * rightYw);
		result.Yw(leftXw * rightYx + leftYw * rightYy + leftZw * rightYz + leftTw * rightYw);
		result.Zx(leftXx * rightZx + leftYx * rightZy + leftZx * rightZz + leftTx * rightZw);
		result.Zy(leftXy * rightZx + leftYy * rightZy + leftZy * rightZz + leftTy * rightZw);
		result.Zz(leftXz * rightZx + leftYz * rightZy + leftZz * rightZz + leftTz * rightZw);
		result.Zw(leftXw * rightZx + leftYw * rightZy + leftZw * rightZz + leftTw * rightZw);
		result.Tx(leftXx * rightTx + leftYx * rightTy + leftZx * rightTz + leftTx * rightTw);
		result.Ty(leftXy * rightTx + leftYy * rightTy + leftZy * rightTz + leftTy * rightTw);
		result.Tz(leftXz * rightTx + leftYz * rightTy + leftZz * rightTz + leftTz * rightTw);
		result.Tw(leftXw * rightTx + leftYw * rightTy + leftZw * rightTz + leftTw * rightTw);
	}

	public static void multiply(Matrix4x4.Accessible left, Vector4.Accessible right, Vector4.Mutable result) {
		final double leftXx = left.Xx();
		final double leftXy = left.Xy();
		final double leftXz = left.Xz();
		final double leftXw = left.Xw();
		final double leftYx = left.Yx();
		final double leftYy = left.Yy();
		final double leftYz = left.Yz();
		final double leftYw = left.Yw();
		final double leftZx = left.Zx();
		final double leftZy = left.Zy();
		final double leftZz = left.Zz();
		final double leftZw = left.Zw();
		final double leftTx = left.Tx();
		final double leftTy = left.Ty();
		final double leftTz = left.Tz();
		final double leftTw = left.Tw();

		final double rightx = right.x();
		final double righty = right.y();
		final double rightz = right.z();
		final double rightw = right.w();

		result.x(leftXx * rightx + leftYx * righty + leftZx * rightz + leftTx * rightw);
		result.y(leftXy * rightx + leftYy * righty + leftZy * rightz + leftTy * rightw);
		result.z(leftXz * rightx + leftYz * righty + leftZz * rightz + leftTz * rightw);
		result.w(leftXw * rightx + leftYw * righty + leftZw * rightz + leftTw * rightw);
	}

	public static void inverse(Matrix4x4.Accessible matrix, Matrix4x4.Mutable result) {
		final double matrixXx = matrix.Xx();
		final double matrixXy = matrix.Xy();
		final double matrixXz = matrix.Xz();
		final double matrixXw = matrix.Xw();
		final double matrixYx = matrix.Yx();
		final double matrixYy = matrix.Yy();
		final double matrixYz = matrix.Yz();
		final double matrixYw = matrix.Yw();
		final double matrixZx = matrix.Zx();
		final double matrixZy = matrix.Zy();
		final double matrixZz = matrix.Zz();
		final double matrixZw = matrix.Zw();
		final double matrixTx = matrix.Tx();
		final double matrixTy = matrix.Ty();
		final double matrixTz = matrix.Tz();
		final double matrixTw = matrix.Tw();

		final double det00 = matrixXx * matrixYy - matrixXy * matrixYx;
		final double det01 = matrixXx * matrixYz - matrixXz * matrixYx;
		final double det02 = matrixXx * matrixYw - matrixXw * matrixYx;
		final double det03 = matrixXy * matrixYz - matrixXz * matrixYy;
		final double det04 = matrixXy * matrixYw - matrixXw * matrixYy;
		final double det05 = matrixXz * matrixYw - matrixXw * matrixYz;
		final double det06 = matrixZx * matrixTy - matrixZy * matrixTx;
		final double det07 = matrixZx * matrixTz - matrixZz * matrixTx;
		final double det08 = matrixZx * matrixTw - matrixZw * matrixTx;
		final double det09 = matrixZy * matrixTz - matrixZz * matrixTy;
		final double det10 = matrixZy * matrixTw - matrixZw * matrixTy;
		final double det11 = matrixZz * matrixTw - matrixZw * matrixTz;

		double det = det00 * det11 - det01 * det10 + det02 * det09 + det03 * det08 - det04 * det07 + det05 * det06;
		det = 1.0 / det;

		result.Xx((matrixYy * det11 - matrixYz * det10 + matrixYw * det09) * det);
		result.Xy((matrixXz * det10 - matrixXy * det11 - matrixXw * det09) * det);
		result.Xz((matrixTy * det05 - matrixTz * det04 + matrixTw * det03) * det);
		result.Xw((matrixZz * det04 - matrixZy * det05 - matrixZw * det03) * det);
		result.Yx((matrixYz * det08 - matrixYx * det11 - matrixYw * det07) * det);
		result.Yy((matrixXx * det11 - matrixXz * det08 + matrixXw * det07) * det);
		result.Yz((matrixTz * det02 - matrixTx * det05 - matrixTw * det01) * det);
		result.Yw((matrixZx * det05 - matrixZz * det02 + matrixZw * det01) * det);
		result.Zx((matrixYx * det10 - matrixYy * det08 + matrixYw * det06) * det);
		result.Zy((matrixXy * det08 - matrixXx * det10 - matrixXw * det06) * det);
		result.Zz((matrixTx * det04 - matrixTy * det02 + matrixTw * det00) * det);
		result.Zw((matrixZy * det02 - matrixZx * det04 - matrixZw * det00) * det);
		result.Tx((matrixYy * det07 - matrixYx * det09 - matrixYz * det06) * det);
		result.Ty((matrixXx * det09 - matrixXy * det07 + matrixXz * det06) * det);
		result.Tz((matrixTy * det01 - matrixTx * det03 - matrixTz * det00) * det);
		result.Tw((matrixZx * det03 - matrixZy * det01 + matrixZz * det00) * det);
	}

	public static void transformPosition(Matrix4x4.Accessible transformationMatrix, Vector3.Accessible position, Vector3.Mutable result) {
		final double transformationMatrixXx = transformationMatrix.Xx();
		final double transformationMatrixXy = transformationMatrix.Xy();
		final double transformationMatrixXz = transformationMatrix.Xz();
		final double transformationMatrixYx = transformationMatrix.Yx();
		final double transformationMatrixYy = transformationMatrix.Yy();
		final double transformationMatrixYz = transformationMatrix.Yz();
		final double transformationMatrixZx = transformationMatrix.Zx();
		final double transformationMatrixZy = transformationMatrix.Zy();
		final double transformationMatrixZz = transformationMatrix.Zz();
		final double transformationMatrixTx = transformationMatrix.Tx();
		final double transformationMatrixTy = transformationMatrix.Ty();
		final double transformationMatrixTz = transformationMatrix.Tz();

		final double positionx = position.x();
		final double positiony = position.y();
		final double positionz = position.z();

		result.x(transformationMatrixXx * positionx + transformationMatrixYx * positiony + transformationMatrixZx * positionz + transformationMatrixTx);
		result.y(transformationMatrixXy * positionx + transformationMatrixYy * positiony + transformationMatrixZy * positionz + transformationMatrixTy);
		result.z(transformationMatrixXz * positionx + transformationMatrixYz * positiony + transformationMatrixZz * positionz + transformationMatrixTz);
	}

	public static void transformDirection(Matrix4x4.Accessible transformationMatrix, Vector3.Accessible direction, Vector3.Mutable result) {
		final double transformationMatrixXx = transformationMatrix.Xx();
		final double transformationMatrixXy = transformationMatrix.Xy();
		final double transformationMatrixXz = transformationMatrix.Xz();
		final double transformationMatrixYx = transformationMatrix.Yx();
		final double transformationMatrixYy = transformationMatrix.Yy();
		final double transformationMatrixYz = transformationMatrix.Yz();
		final double transformationMatrixZx = transformationMatrix.Zx();
		final double transformationMatrixZy = transformationMatrix.Zy();
		final double transformationMatrixZz = transformationMatrix.Zz();

		final double directionx = direction.x();
		final double directiony = direction.y();
		final double directionz = direction.z();

		result.x(transformationMatrixXx * directionx + transformationMatrixYx * directiony + transformationMatrixZx * directionz);
		result.y(transformationMatrixXy * directionx + transformationMatrixYy * directiony + transformationMatrixZy * directionz);
		result.z(transformationMatrixXz * directionx + transformationMatrixYz * directiony + transformationMatrixZz * directionz);
	}

}

