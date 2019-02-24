package ee.ristoseene.raytracer.eyebased.core.vecmath;

public interface Matrix3x3 {

	interface Accessible {

		double Xx();
		double Xy();
		double Xz();
		double Yx();
		double Yy();
		double Yz();
		double Zx();
		double Zy();
		double Zz();

		default Value.Accessible const$Xx() {
			return this::Xx;
		}

		default Value.Accessible const$Xy() {
			return this::Xy;
		}

		default Value.Accessible const$Xz() {
			return this::Xz;
		}

		default Value.Accessible const$Yx() {
			return this::Yx;
		}

		default Value.Accessible const$Yy() {
			return this::Yy;
		}

		default Value.Accessible const$Yz() {
			return this::Yz;
		}

		default Value.Accessible const$Zx() {
			return this::Zx;
		}

		default Value.Accessible const$Zy() {
			return this::Zy;
		}

		default Value.Accessible const$Zz() {
			return this::Zz;
		}

		default Vector3.Accessible const$X() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.Xx(); }
				public double y() { return Accessible.this.Xy(); }
				public double z() { return Accessible.this.Xz(); }
			};
		}

		default Vector3.Accessible const$Y() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.Yx(); }
				public double y() { return Accessible.this.Yy(); }
				public double z() { return Accessible.this.Yz(); }
			};
		}

		default Vector3.Accessible const$Z() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.Zx(); }
				public double y() { return Accessible.this.Zy(); }
				public double z() { return Accessible.this.Zz(); }
			};
		}

		default Vector3.Accessible const$diagonal() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.Xx(); }
				public double y() { return Accessible.this.Yy(); }
				public double z() { return Accessible.this.Zz(); }
			};
		}

		default Value.Accessible $Xx() {
			return const$Xx();
		}

		default Value.Accessible $Xy() {
			return const$Xy();
		}

		default Value.Accessible $Xz() {
			return const$Xz();
		}

		default Value.Accessible $Yx() {
			return const$Yx();
		}

		default Value.Accessible $Yy() {
			return const$Yy();
		}

		default Value.Accessible $Yz() {
			return const$Yz();
		}

		default Value.Accessible $Zx() {
			return const$Zx();
		}

		default Value.Accessible $Zy() {
			return const$Zy();
		}

		default Value.Accessible $Zz() {
			return const$Zz();
		}

		default Vector3.Accessible $X() {
			return const$X();
		}

		default Vector3.Accessible $Y() {
			return const$Y();
		}

		default Vector3.Accessible $Z() {
			return const$Z();
		}

		default Vector3.Accessible $diagonal() {
			return const$diagonal();
		}

	}

	interface Mutable {

		void Xx(double Xx);
		void Xy(double Xy);
		void Xz(double Xz);
		void Yx(double Yx);
		void Yy(double Yy);
		void Yz(double Yz);
		void Zx(double Zx);
		void Zy(double Zy);
		void Zz(double Zz);

		default void X(double v) {
			Xx(v);
			Xy(v);
			Xz(v);
		}

		default void Y(double v) {
			Yx(v);
			Yy(v);
			Yz(v);
		}

		default void Z(double v) {
			Zx(v);
			Zy(v);
			Zz(v);
		}

		default void diagonal(double v) {
			Xx(v);
			Yy(v);
			Zz(v);
		}

		default void set(double v) {
			Xx(v);
			Xy(v);
			Xz(v);
			Yx(v);
			Yy(v);
			Yz(v);
			Zx(v);
			Zy(v);
			Zz(v);
		}

		default void X(double x, double y, double z) {
			Xx(x);
			Xy(y);
			Xz(z);
		}

		default void Y(double x, double y, double z) {
			Yx(x);
			Yy(y);
			Yz(z);
		}

		default void Z(double x, double y, double z) {
			Zx(x);
			Zy(y);
			Zz(z);
		}

		default void diagonal(double Xx, double Yy, double Zz) {
			Xx(Xx);
			Yy(Yy);
			Zz(Zz);
		}

		default void Xx(Value.Accessible Xx) {
			Xx(Xx.get());
		}

		default void Xy(Value.Accessible Xy) {
			Xy(Xy.get());
		}

		default void Xz(Value.Accessible Xz) {
			Xz(Xz.get());
		}

		default void Yx(Value.Accessible Yx) {
			Yx(Yx.get());
		}

		default void Yy(Value.Accessible Yy) {
			Yy(Yy.get());
		}

		default void Yz(Value.Accessible Yz) {
			Yz(Yz.get());
		}

		default void Zx(Value.Accessible Zx) {
			Zx(Zx.get());
		}

		default void Zy(Value.Accessible Zy) {
			Zy(Zy.get());
		}

		default void Zz(Value.Accessible Zz) {
			Zz(Zz.get());
		}

		default void X(Vector3.Accessible v) {
			Xx(v.x());
			Xy(v.y());
			Xz(v.z());
		}

		default void Y(Vector3.Accessible v) {
			Yx(v.x());
			Yy(v.y());
			Yz(v.z());
		}

		default void Z(Vector3.Accessible v) {
			Zx(v.x());
			Zy(v.y());
			Zz(v.z());
		}

		default void diagonal(Vector3.Accessible v) {
			Xx(v.x());
			Yy(v.y());
			Zz(v.z());
		}

		default void set(Matrix3x3.Accessible v) {
			Xx(v.Xx());
			Xy(v.Xy());
			Xz(v.Xz());
			Yx(v.Yx());
			Yy(v.Yy());
			Yz(v.Yz());
			Zx(v.Zx());
			Zy(v.Zy());
			Zz(v.Zz());
		}

		default Value.Mutable $Xx() {
			return this::Xx;
		}

		default Value.Mutable $Xy() {
			return this::Xy;
		}

		default Value.Mutable $Xz() {
			return this::Xz;
		}

		default Value.Mutable $Yx() {
			return this::Yx;
		}

		default Value.Mutable $Yy() {
			return this::Yy;
		}

		default Value.Mutable $Yz() {
			return this::Yz;
		}

		default Value.Mutable $Zx() {
			return this::Zx;
		}

		default Value.Mutable $Zy() {
			return this::Zy;
		}

		default Value.Mutable $Zz() {
			return this::Zz;
		}

		default Vector3.Mutable $X() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.Xx(x); }
				public void y(double y) { Mutable.this.Xy(y); }
				public void z(double z) { Mutable.this.Xz(z); }
			};
		}

		default Vector3.Mutable $Y() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.Yx(x); }
				public void y(double y) { Mutable.this.Yy(y); }
				public void z(double z) { Mutable.this.Yz(z); }
			};
		}

		default Vector3.Mutable $Z() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.Zx(x); }
				public void y(double y) { Mutable.this.Zy(y); }
				public void z(double z) { Mutable.this.Zz(z); }
			};
		}

		default Vector3.Mutable $diagonal() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.Xx(x); }
				public void y(double y) { Mutable.this.Yy(y); }
				public void z(double z) { Mutable.this.Zz(z); }
			};
		}

	}

	interface AccessibleAndMutable extends Accessible, Mutable {

		default Value.AccessibleAndMutable $Xx() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Xx(); }
				public void set(double v) { AccessibleAndMutable.this.Xx(v); }
			};
		}

		default Value.AccessibleAndMutable $Xy() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Xy(); }
				public void set(double v) { AccessibleAndMutable.this.Xy(v); }
			};
		}

		default Value.AccessibleAndMutable $Xz() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Xz(); }
				public void set(double v) { AccessibleAndMutable.this.Xz(v); }
			};
		}

		default Value.AccessibleAndMutable $Yx() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Yx(); }
				public void set(double v) { AccessibleAndMutable.this.Yx(v); }
			};
		}

		default Value.AccessibleAndMutable $Yy() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Yy(); }
				public void set(double v) { AccessibleAndMutable.this.Yy(v); }
			};
		}

		default Value.AccessibleAndMutable $Yz() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Yz(); }
				public void set(double v) { AccessibleAndMutable.this.Yz(v); }
			};
		}

		default Value.AccessibleAndMutable $Zx() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Zx(); }
				public void set(double v) { AccessibleAndMutable.this.Zx(v); }
			};
		}

		default Value.AccessibleAndMutable $Zy() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Zy(); }
				public void set(double v) { AccessibleAndMutable.this.Zy(v); }
			};
		}

		default Value.AccessibleAndMutable $Zz() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Zz(); }
				public void set(double v) { AccessibleAndMutable.this.Zz(v); }
			};
		}

		default Vector3.AccessibleAndMutable $X() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Xx(); }
				public double y() { return AccessibleAndMutable.this.Xy(); }
				public double z() { return AccessibleAndMutable.this.Xz(); }
				public void x(double x) { AccessibleAndMutable.this.Xx(x); }
				public void y(double y) { AccessibleAndMutable.this.Xy(y); }
				public void z(double z) { AccessibleAndMutable.this.Xz(z); }
			};
		}

		default Vector3.AccessibleAndMutable $Y() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Yx(); }
				public double y() { return AccessibleAndMutable.this.Yy(); }
				public double z() { return AccessibleAndMutable.this.Yz(); }
				public void x(double x) { AccessibleAndMutable.this.Yx(x); }
				public void y(double y) { AccessibleAndMutable.this.Yy(y); }
				public void z(double z) { AccessibleAndMutable.this.Yz(z); }
			};
		}

		default Vector3.AccessibleAndMutable $Z() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Zx(); }
				public double y() { return AccessibleAndMutable.this.Zy(); }
				public double z() { return AccessibleAndMutable.this.Zz(); }
				public void x(double x) { AccessibleAndMutable.this.Zx(x); }
				public void y(double y) { AccessibleAndMutable.this.Zy(y); }
				public void z(double z) { AccessibleAndMutable.this.Zz(z); }
			};
		}

		default Vector3.AccessibleAndMutable $diagonal() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Xx(); }
				public double y() { return AccessibleAndMutable.this.Yy(); }
				public double z() { return AccessibleAndMutable.this.Zz(); }
				public void x(double x) { AccessibleAndMutable.this.Xx(x); }
				public void y(double y) { AccessibleAndMutable.this.Yy(y); }
				public void z(double z) { AccessibleAndMutable.this.Zz(z); }
			};
		}

	}

}

