package ee.ristoseene.raytracer.eyebased.core.vecmath;

public interface Vector2 {

	interface Accessible {

		double x();
		double y();

		default Value.Accessible const$x() {
			return this::x;
		}

		default Value.Accessible const$y() {
			return this::y;
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

		default Value.Accessible $x() {
			return const$x();
		}

		default Value.Accessible $y() {
			return const$y();
		}

		default Vector2.Accessible $xx() {
			return const$xx();
		}

		default Vector2.Accessible $xy() {
			return this;
		}

		default Vector2.Accessible $yx() {
			return const$yx();
		}

		default Vector2.Accessible $yy() {
			return const$yy();
		}

	}

	interface Mutable {

		void x(double x);
		void y(double y);

		default void xy(double v) {
			x(v);
			y(v);
		}

		default void xy(double x, double y) {
			x(x);
			y(y);
		}

		default void x(Value.Accessible x) {
			x(x.get());
		}

		default void y(Value.Accessible y) {
			y(y.get());
		}

		default void xy(Vector2.Accessible v) {
			xy(v.x(), v.y());
		}

		default void yx(Vector2.Accessible v) {
			xy(v.y(), v.x());
		}

		default Value.Mutable $x() {
			return this::x;
		}

		default Value.Mutable $y() {
			return this::y;
		}

		default Vector2.Mutable $xy() {
			return this;
		}

		default Vector2.Mutable $yx() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
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

		default Vector2.AccessibleAndMutable $xy() {
			return this;
		}

		default Vector2.AccessibleAndMutable $yx() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
			};
		}

	}

}

