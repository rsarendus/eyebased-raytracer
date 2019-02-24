package ee.ristoseene.raytracer.eyebased.core.vecmath;

public interface Matrix4x4 {

	interface Accessible {

		double Xx();
		double Xy();
		double Xz();
		double Xw();
		double Yx();
		double Yy();
		double Yz();
		double Yw();
		double Zx();
		double Zy();
		double Zz();
		double Zw();
		double Tx();
		double Ty();
		double Tz();
		double Tw();

		default Value.Accessible const$Xx() {
			return this::Xx;
		}

		default Value.Accessible const$Xy() {
			return this::Xy;
		}

		default Value.Accessible const$Xz() {
			return this::Xz;
		}

		default Value.Accessible const$Xw() {
			return this::Xw;
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

		default Value.Accessible const$Yw() {
			return this::Yw;
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

		default Value.Accessible const$Zw() {
			return this::Zw;
		}

		default Value.Accessible const$Tx() {
			return this::Tx;
		}

		default Value.Accessible const$Ty() {
			return this::Ty;
		}

		default Value.Accessible const$Tz() {
			return this::Tz;
		}

		default Value.Accessible const$Tw() {
			return this::Tw;
		}

		default Vector4.Accessible const$X() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.Xx(); }
				public double y() { return Accessible.this.Xy(); }
				public double z() { return Accessible.this.Xz(); }
				public double w() { return Accessible.this.Xw(); }
			};
		}

		default Vector4.Accessible const$Y() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.Yx(); }
				public double y() { return Accessible.this.Yy(); }
				public double z() { return Accessible.this.Yz(); }
				public double w() { return Accessible.this.Yw(); }
			};
		}

		default Vector4.Accessible const$Z() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.Zx(); }
				public double y() { return Accessible.this.Zy(); }
				public double z() { return Accessible.this.Zz(); }
				public double w() { return Accessible.this.Zw(); }
			};
		}

		default Vector4.Accessible const$T() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.Tx(); }
				public double y() { return Accessible.this.Ty(); }
				public double z() { return Accessible.this.Tz(); }
				public double w() { return Accessible.this.Tw(); }
			};
		}

		default Vector4.Accessible const$diagonal() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.Xx(); }
				public double y() { return Accessible.this.Yy(); }
				public double z() { return Accessible.this.Zz(); }
				public double w() { return Accessible.this.Tw(); }
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

		default Value.Accessible $Xw() {
			return const$Xw();
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

		default Value.Accessible $Yw() {
			return const$Yw();
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

		default Value.Accessible $Zw() {
			return const$Zw();
		}

		default Value.Accessible $Tx() {
			return const$Tx();
		}

		default Value.Accessible $Ty() {
			return const$Ty();
		}

		default Value.Accessible $Tz() {
			return const$Tz();
		}

		default Value.Accessible $Tw() {
			return const$Tw();
		}

		default Vector4.Accessible $X() {
			return const$X();
		}

		default Vector4.Accessible $Y() {
			return const$Y();
		}

		default Vector4.Accessible $Z() {
			return const$Z();
		}

		default Vector4.Accessible $T() {
			return const$T();
		}

		default Vector4.Accessible $diagonal() {
			return const$diagonal();
		}

	}

	interface Mutable {

		void Xx(double Xx);
		void Xy(double Xy);
		void Xz(double Xz);
		void Xw(double Xw);
		void Yx(double Yx);
		void Yy(double Yy);
		void Yz(double Yz);
		void Yw(double Yw);
		void Zx(double Zx);
		void Zy(double Zy);
		void Zz(double Zz);
		void Zw(double Zw);
		void Tx(double Tx);
		void Ty(double Ty);
		void Tz(double Tz);
		void Tw(double Tw);

		default void X(double v) {
			Xx(v);
			Xy(v);
			Xz(v);
			Xw(v);
		}

		default void Y(double v) {
			Yx(v);
			Yy(v);
			Yz(v);
			Yw(v);
		}

		default void Z(double v) {
			Zx(v);
			Zy(v);
			Zz(v);
			Zw(v);
		}

		default void T(double v) {
			Tx(v);
			Ty(v);
			Tz(v);
			Tw(v);
		}

		default void diagonal(double v) {
			Xx(v);
			Yy(v);
			Zz(v);
			Tw(v);
		}

		default void set(double v) {
			Xx(v);
			Xy(v);
			Xz(v);
			Xw(v);
			Yx(v);
			Yy(v);
			Yz(v);
			Yw(v);
			Zx(v);
			Zy(v);
			Zz(v);
			Zw(v);
			Tx(v);
			Ty(v);
			Tz(v);
			Tw(v);
		}

		default void X(double x, double y, double z, double w) {
			Xx(x);
			Xy(y);
			Xz(z);
			Xw(w);
		}

		default void Y(double x, double y, double z, double w) {
			Yx(x);
			Yy(y);
			Yz(z);
			Yw(w);
		}

		default void Z(double x, double y, double z, double w) {
			Zx(x);
			Zy(y);
			Zz(z);
			Zw(w);
		}

		default void T(double x, double y, double z, double w) {
			Tx(x);
			Ty(y);
			Tz(z);
			Tw(w);
		}

		default void diagonal(double Xx, double Yy, double Zz, double Tw) {
			Xx(Xx);
			Yy(Yy);
			Zz(Zz);
			Tw(Tw);
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

		default void Xw(Value.Accessible Xw) {
			Xw(Xw.get());
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

		default void Yw(Value.Accessible Yw) {
			Yw(Yw.get());
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

		default void Zw(Value.Accessible Zw) {
			Zw(Zw.get());
		}

		default void Tx(Value.Accessible Tx) {
			Tx(Tx.get());
		}

		default void Ty(Value.Accessible Ty) {
			Ty(Ty.get());
		}

		default void Tz(Value.Accessible Tz) {
			Tz(Tz.get());
		}

		default void Tw(Value.Accessible Tw) {
			Tw(Tw.get());
		}

		default void X(Vector4.Accessible v) {
			Xx(v.x());
			Xy(v.y());
			Xz(v.z());
			Xw(v.w());
		}

		default void Y(Vector4.Accessible v) {
			Yx(v.x());
			Yy(v.y());
			Yz(v.z());
			Yw(v.w());
		}

		default void Z(Vector4.Accessible v) {
			Zx(v.x());
			Zy(v.y());
			Zz(v.z());
			Zw(v.w());
		}

		default void T(Vector4.Accessible v) {
			Tx(v.x());
			Ty(v.y());
			Tz(v.z());
			Tw(v.w());
		}

		default void diagonal(Vector4.Accessible v) {
			Xx(v.x());
			Yy(v.y());
			Zz(v.z());
			Tw(v.w());
		}

		default void set(Matrix4x4.Accessible v) {
			Xx(v.Xx());
			Xy(v.Xy());
			Xz(v.Xz());
			Xw(v.Xw());
			Yx(v.Yx());
			Yy(v.Yy());
			Yz(v.Yz());
			Yw(v.Yw());
			Zx(v.Zx());
			Zy(v.Zy());
			Zz(v.Zz());
			Zw(v.Zw());
			Tx(v.Tx());
			Ty(v.Ty());
			Tz(v.Tz());
			Tw(v.Tw());
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

		default Value.Mutable $Xw() {
			return this::Xw;
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

		default Value.Mutable $Yw() {
			return this::Yw;
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

		default Value.Mutable $Zw() {
			return this::Zw;
		}

		default Value.Mutable $Tx() {
			return this::Tx;
		}

		default Value.Mutable $Ty() {
			return this::Ty;
		}

		default Value.Mutable $Tz() {
			return this::Tz;
		}

		default Value.Mutable $Tw() {
			return this::Tw;
		}

		default Vector4.Mutable $X() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.Xx(x); }
				public void y(double y) { Mutable.this.Xy(y); }
				public void z(double z) { Mutable.this.Xz(z); }
				public void w(double w) { Mutable.this.Xw(w); }
			};
		}

		default Vector4.Mutable $Y() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.Yx(x); }
				public void y(double y) { Mutable.this.Yy(y); }
				public void z(double z) { Mutable.this.Yz(z); }
				public void w(double w) { Mutable.this.Yw(w); }
			};
		}

		default Vector4.Mutable $Z() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.Zx(x); }
				public void y(double y) { Mutable.this.Zy(y); }
				public void z(double z) { Mutable.this.Zz(z); }
				public void w(double w) { Mutable.this.Zw(w); }
			};
		}

		default Vector4.Mutable $T() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.Tx(x); }
				public void y(double y) { Mutable.this.Ty(y); }
				public void z(double z) { Mutable.this.Tz(z); }
				public void w(double w) { Mutable.this.Tw(w); }
			};
		}

		default Vector4.Mutable $diagonal() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.Xx(x); }
				public void y(double y) { Mutable.this.Yy(y); }
				public void z(double z) { Mutable.this.Zz(z); }
				public void w(double w) { Mutable.this.Tw(w); }
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

		default Value.AccessibleAndMutable $Xw() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Xw(); }
				public void set(double v) { AccessibleAndMutable.this.Xw(v); }
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

		default Value.AccessibleAndMutable $Yw() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Yw(); }
				public void set(double v) { AccessibleAndMutable.this.Yw(v); }
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

		default Value.AccessibleAndMutable $Zw() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Zw(); }
				public void set(double v) { AccessibleAndMutable.this.Zw(v); }
			};
		}

		default Value.AccessibleAndMutable $Tx() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Tx(); }
				public void set(double v) { AccessibleAndMutable.this.Tx(v); }
			};
		}

		default Value.AccessibleAndMutable $Ty() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Ty(); }
				public void set(double v) { AccessibleAndMutable.this.Ty(v); }
			};
		}

		default Value.AccessibleAndMutable $Tz() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Tz(); }
				public void set(double v) { AccessibleAndMutable.this.Tz(v); }
			};
		}

		default Value.AccessibleAndMutable $Tw() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.Tw(); }
				public void set(double v) { AccessibleAndMutable.this.Tw(v); }
			};
		}

		default Vector4.AccessibleAndMutable $X() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Xx(); }
				public double y() { return AccessibleAndMutable.this.Xy(); }
				public double z() { return AccessibleAndMutable.this.Xz(); }
				public double w() { return AccessibleAndMutable.this.Xw(); }
				public void x(double x) { AccessibleAndMutable.this.Xx(x); }
				public void y(double y) { AccessibleAndMutable.this.Xy(y); }
				public void z(double z) { AccessibleAndMutable.this.Xz(z); }
				public void w(double w) { AccessibleAndMutable.this.Xw(w); }
			};
		}

		default Vector4.AccessibleAndMutable $Y() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Yx(); }
				public double y() { return AccessibleAndMutable.this.Yy(); }
				public double z() { return AccessibleAndMutable.this.Yz(); }
				public double w() { return AccessibleAndMutable.this.Yw(); }
				public void x(double x) { AccessibleAndMutable.this.Yx(x); }
				public void y(double y) { AccessibleAndMutable.this.Yy(y); }
				public void z(double z) { AccessibleAndMutable.this.Yz(z); }
				public void w(double w) { AccessibleAndMutable.this.Yw(w); }
			};
		}

		default Vector4.AccessibleAndMutable $Z() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Zx(); }
				public double y() { return AccessibleAndMutable.this.Zy(); }
				public double z() { return AccessibleAndMutable.this.Zz(); }
				public double w() { return AccessibleAndMutable.this.Zw(); }
				public void x(double x) { AccessibleAndMutable.this.Zx(x); }
				public void y(double y) { AccessibleAndMutable.this.Zy(y); }
				public void z(double z) { AccessibleAndMutable.this.Zz(z); }
				public void w(double w) { AccessibleAndMutable.this.Zw(w); }
			};
		}

		default Vector4.AccessibleAndMutable $T() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Tx(); }
				public double y() { return AccessibleAndMutable.this.Ty(); }
				public double z() { return AccessibleAndMutable.this.Tz(); }
				public double w() { return AccessibleAndMutable.this.Tw(); }
				public void x(double x) { AccessibleAndMutable.this.Tx(x); }
				public void y(double y) { AccessibleAndMutable.this.Ty(y); }
				public void z(double z) { AccessibleAndMutable.this.Tz(z); }
				public void w(double w) { AccessibleAndMutable.this.Tw(w); }
			};
		}

		default Vector4.AccessibleAndMutable $diagonal() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.Xx(); }
				public double y() { return AccessibleAndMutable.this.Yy(); }
				public double z() { return AccessibleAndMutable.this.Zz(); }
				public double w() { return AccessibleAndMutable.this.Tw(); }
				public void x(double x) { AccessibleAndMutable.this.Xx(x); }
				public void y(double y) { AccessibleAndMutable.this.Yy(y); }
				public void z(double z) { AccessibleAndMutable.this.Zz(z); }
				public void w(double w) { AccessibleAndMutable.this.Tw(w); }
			};
		}

	}

}

