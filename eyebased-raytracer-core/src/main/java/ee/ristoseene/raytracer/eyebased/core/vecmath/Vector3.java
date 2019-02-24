package ee.ristoseene.raytracer.eyebased.core.vecmath;

public interface Vector3 {

	interface Accessible {

		double x();
		double y();
		double z();

		default Value.Accessible const$x() {
			return this::x;
		}

		default Value.Accessible const$y() {
			return this::y;
		}

		default Value.Accessible const$z() {
			return this::z;
		}

		default Vector2.Accessible const$xx() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
			};
		}

		default Vector2.Accessible const$xy() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
			};
		}

		default Vector2.Accessible const$xz() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
			};
		}

		default Vector2.Accessible const$yx() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
			};
		}

		default Vector2.Accessible const$yy() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
			};
		}

		default Vector2.Accessible const$yz() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
			};
		}

		default Vector2.Accessible const$zx() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
			};
		}

		default Vector2.Accessible const$zy() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
			};
		}

		default Vector2.Accessible const$zz() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$xxx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$xxy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$xxz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$xyx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$xyy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$xyz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$xzx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$xzy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$xzz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$yxx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$yxy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$yxz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$yyx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$yyy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$yyz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$yzx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$yzy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$yzz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$zxx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$zxy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$zxz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$zyx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$zyy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$zyz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$zzx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$zzy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$zzz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Value.Accessible $x() {
			return const$x();
		}

		default Value.Accessible $y() {
			return const$y();
		}

		default Value.Accessible $z() {
			return const$z();
		}

		default Vector2.Accessible $xx() {
			return const$xx();
		}

		default Vector2.Accessible $xy() {
			return const$xy();
		}

		default Vector2.Accessible $xz() {
			return const$xz();
		}

		default Vector2.Accessible $yx() {
			return const$yx();
		}

		default Vector2.Accessible $yy() {
			return const$yy();
		}

		default Vector2.Accessible $yz() {
			return const$yz();
		}

		default Vector2.Accessible $zx() {
			return const$zx();
		}

		default Vector2.Accessible $zy() {
			return const$zy();
		}

		default Vector2.Accessible $zz() {
			return const$zz();
		}

		default Vector3.Accessible $xxx() {
			return const$xxx();
		}

		default Vector3.Accessible $xxy() {
			return const$xxy();
		}

		default Vector3.Accessible $xxz() {
			return const$xxz();
		}

		default Vector3.Accessible $xyx() {
			return const$xyx();
		}

		default Vector3.Accessible $xyy() {
			return const$xyy();
		}

		default Vector3.Accessible $xyz() {
			return this;
		}

		default Vector3.Accessible $xzx() {
			return const$xzx();
		}

		default Vector3.Accessible $xzy() {
			return const$xzy();
		}

		default Vector3.Accessible $xzz() {
			return const$xzz();
		}

		default Vector3.Accessible $yxx() {
			return const$yxx();
		}

		default Vector3.Accessible $yxy() {
			return const$yxy();
		}

		default Vector3.Accessible $yxz() {
			return const$yxz();
		}

		default Vector3.Accessible $yyx() {
			return const$yyx();
		}

		default Vector3.Accessible $yyy() {
			return const$yyy();
		}

		default Vector3.Accessible $yyz() {
			return const$yyz();
		}

		default Vector3.Accessible $yzx() {
			return const$yzx();
		}

		default Vector3.Accessible $yzy() {
			return const$yzy();
		}

		default Vector3.Accessible $yzz() {
			return const$yzz();
		}

		default Vector3.Accessible $zxx() {
			return const$zxx();
		}

		default Vector3.Accessible $zxy() {
			return const$zxy();
		}

		default Vector3.Accessible $zxz() {
			return const$zxz();
		}

		default Vector3.Accessible $zyx() {
			return const$zyx();
		}

		default Vector3.Accessible $zyy() {
			return const$zyy();
		}

		default Vector3.Accessible $zyz() {
			return const$zyz();
		}

		default Vector3.Accessible $zzx() {
			return const$zzx();
		}

		default Vector3.Accessible $zzy() {
			return const$zzy();
		}

		default Vector3.Accessible $zzz() {
			return const$zzz();
		}

	}

	interface Mutable {

		void x(double x);
		void y(double y);
		void z(double z);

		default void xy(double v) {
			x(v);
			y(v);
		}

		default void xz(double v) {
			x(v);
			z(v);
		}

		default void yz(double v) {
			y(v);
			z(v);
		}

		default void xyz(double v) {
			x(v);
			y(v);
			z(v);
		}

		default void xy(double x, double y) {
			x(x);
			y(y);
		}

		default void xz(double x, double z) {
			x(x);
			z(z);
		}

		default void yz(double y, double z) {
			y(y);
			z(z);
		}

		default void xyz(double x, double y, double z) {
			x(x);
			y(y);
			z(z);
		}

		default void x(Value.Accessible x) {
			x(x.get());
		}

		default void y(Value.Accessible y) {
			y(y.get());
		}

		default void z(Value.Accessible z) {
			z(z.get());
		}

		default void xy(Vector2.Accessible v) {
			xy(v.x(), v.y());
		}

		default void xz(Vector2.Accessible v) {
			xz(v.x(), v.y());
		}

		default void yx(Vector2.Accessible v) {
			xy(v.y(), v.x());
		}

		default void yz(Vector2.Accessible v) {
			yz(v.x(), v.y());
		}

		default void zx(Vector2.Accessible v) {
			xz(v.y(), v.x());
		}

		default void zy(Vector2.Accessible v) {
			yz(v.y(), v.x());
		}

		default void xyz(Vector3.Accessible v) {
			xyz(v.x(), v.y(), v.z());
		}

		default void xzy(Vector3.Accessible v) {
			xyz(v.x(), v.z(), v.y());
		}

		default void yxz(Vector3.Accessible v) {
			xyz(v.y(), v.x(), v.z());
		}

		default void yzx(Vector3.Accessible v) {
			xyz(v.z(), v.x(), v.y());
		}

		default void zxy(Vector3.Accessible v) {
			xyz(v.y(), v.z(), v.x());
		}

		default void zyx(Vector3.Accessible v) {
			xyz(v.z(), v.y(), v.x());
		}

		default Value.Mutable $x() {
			return this::x;
		}

		default Value.Mutable $y() {
			return this::y;
		}

		default Value.Mutable $z() {
			return this::z;
		}

		default Vector2.Mutable $xy() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.y(y); }
			};
		}

		default Vector2.Mutable $xz() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.z(y); }
			};
		}

		default Vector2.Mutable $yx() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
			};
		}

		default Vector2.Mutable $yz() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.z(y); }
			};
		}

		default Vector2.Mutable $zx() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.x(y); }
			};
		}

		default Vector2.Mutable $zy() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.y(y); }
			};
		}

		default Vector3.Mutable $xyz() {
			return this;
		}

		default Vector3.Mutable $xzy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $yxz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $yzx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $zxy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $zyx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

	}

	interface AccessibleAndMutable extends Accessible, Mutable {

		default Value.AccessibleAndMutable $x() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.x(); }
				public void set(double v) { AccessibleAndMutable.this.x(v); }
			};
		}

		default Value.AccessibleAndMutable $y() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.y(); }
				public void set(double v) { AccessibleAndMutable.this.y(v); }
			};
		}

		default Value.AccessibleAndMutable $z() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.z(); }
				public void set(double v) { AccessibleAndMutable.this.z(v); }
			};
		}

		default Vector2.AccessibleAndMutable $xy() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
			};
		}

		default Vector2.AccessibleAndMutable $xz() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
			};
		}

		default Vector2.AccessibleAndMutable $yx() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
			};
		}

		default Vector2.AccessibleAndMutable $yz() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
			};
		}

		default Vector2.AccessibleAndMutable $zx() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
			};
		}

		default Vector2.AccessibleAndMutable $zy() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
			};
		}

		default Vector3.AccessibleAndMutable $xyz() {
			return this;
		}

		default Vector3.AccessibleAndMutable $xzy() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
			};
		}

		default Vector3.AccessibleAndMutable $yxz() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
			};
		}

		default Vector3.AccessibleAndMutable $yzx() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
			};
		}

		default Vector3.AccessibleAndMutable $zxy() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
			};
		}

		default Vector3.AccessibleAndMutable $zyx() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
			};
		}

	}

}

