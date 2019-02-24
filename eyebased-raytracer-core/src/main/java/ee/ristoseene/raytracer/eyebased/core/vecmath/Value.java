package ee.ristoseene.raytracer.eyebased.core.vecmath;

public interface Value {

	interface Accessible {

		double get();

		default Value.Accessible const$() {
			return this::get;
		}

		default Value.Accessible $() {
			return this;
		}

	}

	interface Mutable {

		void set(double v);

		default void set(Value.Accessible v) {
			set(v.get());
		}

		default Value.Mutable $() {
			return this;
		}

	}

	interface AccessibleAndMutable extends Accessible, Mutable {

		default Value.AccessibleAndMutable $() {
			return this;
		}

	}

}

