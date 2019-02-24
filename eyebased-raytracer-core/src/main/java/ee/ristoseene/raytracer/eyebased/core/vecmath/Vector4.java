package ee.ristoseene.raytracer.eyebased.core.vecmath;

public interface Vector4 {

	interface Accessible {

		double x();
		double y();
		double z();
		double w();

		default Value.Accessible const$x() {
			return this::x;
		}

		default Value.Accessible const$y() {
			return this::y;
		}

		default Value.Accessible const$z() {
			return this::z;
		}

		default Value.Accessible const$w() {
			return this::w;
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

		default Vector2.Accessible const$xw() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
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

		default Vector2.Accessible const$yw() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
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

		default Vector2.Accessible const$zw() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
			};
		}

		default Vector2.Accessible const$wx() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
			};
		}

		default Vector2.Accessible const$wy() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
			};
		}

		default Vector2.Accessible const$wz() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
			};
		}

		default Vector2.Accessible const$ww() {
			return new Vector2.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$xxw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$xyw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$xzw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$xwx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$xwy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$xwz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$xww() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$yxw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$yyw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$yzw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$ywx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$ywy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$ywz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$yww() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$zxw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$zyw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
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

		default Vector3.Accessible const$zzw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$zwx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$zwy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$zwz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$zww() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$wxx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$wxy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$wxz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$wxw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$wyx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$wyy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$wyz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$wyw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$wzx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$wzy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$wzz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$wzw() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector3.Accessible const$wwx() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
			};
		}

		default Vector3.Accessible const$wwy() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
			};
		}

		default Vector3.Accessible const$wwz() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
			};
		}

		default Vector3.Accessible const$www() {
			return new Vector3.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xxxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xxxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xxxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xxxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xxyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xxyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xxyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xxyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xxzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xxzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xxzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xxzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xxwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xxwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xxwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xxww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xyxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xyxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xyxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xyxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xyyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xyyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xyyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xyyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xyzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xyzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xyzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xyzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xywx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xywy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xywz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xyww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xzxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xzxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xzxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xzxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xzyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xzyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xzyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xzyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xzzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xzzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xzzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xzzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xzwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xzwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xzwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xzww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xwxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xwxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xwxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xwxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xwyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xwyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xwyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xwyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xwzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xwzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xwzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xwzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$xwwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$xwwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$xwwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$xwww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.x(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yxxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yxxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yxxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yxxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yxyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yxyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yxyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yxyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yxzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yxzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yxzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yxzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yxwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yxwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yxwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yxww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yyxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yyxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yyxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yyxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yyyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yyyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yyyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yyyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yyzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yyzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yyzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yyzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yywx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yywy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yywz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yyww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yzxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yzxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yzxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yzxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yzyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yzyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yzyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yzyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yzzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yzzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yzzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yzzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$yzwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$yzwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$yzwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$yzww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$ywxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$ywxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$ywxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$ywxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$ywyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$ywyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$ywyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$ywyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$ywzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$ywzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$ywzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$ywzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$ywwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$ywwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$ywwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$ywww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.y(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zxxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zxxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zxxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zxxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zxyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zxyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zxyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zxyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zxzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zxzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zxzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zxzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zxwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zxwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zxwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zxww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zyxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zyxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zyxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zyxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zyyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zyyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zyyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zyyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zyzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zyzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zyzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zyzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zywx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zywy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zywz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zyww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zzxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zzxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zzxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zzxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zzyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zzyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zzyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zzyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zzzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zzzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zzzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zzzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zzwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zzwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zzwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zzww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zwxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zwxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zwxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zwxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zwyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zwyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zwyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zwyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zwzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zwzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zwzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zwzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$zwwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$zwwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$zwwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$zwww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.z(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wxxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wxxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wxxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wxxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wxyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wxyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wxyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wxyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wxzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wxzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wxzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wxzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wxwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wxwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wxwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wxww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.x(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wyxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wyxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wyxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wyxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wyyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wyyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wyyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wyyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wyzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wyzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wyzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wyzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wywx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wywy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wywz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wyww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.y(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wzxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wzxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wzxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wzxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wzyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wzyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wzyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wzyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wzzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wzzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wzzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wzzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wzwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wzwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wzwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wzww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.z(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wwxx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wwxy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wwxz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wwxw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.x(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wwyx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wwyy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wwyz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wwyw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.y(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wwzx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wwzy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wwzz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wwzw() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.z(); }
				public double w() { return Accessible.this.w(); }
			};
		}

		default Vector4.Accessible const$wwwx() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.x(); }
			};
		}

		default Vector4.Accessible const$wwwy() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.y(); }
			};
		}

		default Vector4.Accessible const$wwwz() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.z(); }
			};
		}

		default Vector4.Accessible const$wwww() {
			return new Vector4.Accessible() {
				public double x() { return Accessible.this.w(); }
				public double y() { return Accessible.this.w(); }
				public double z() { return Accessible.this.w(); }
				public double w() { return Accessible.this.w(); }
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

		default Value.Accessible $w() {
			return const$w();
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

		default Vector2.Accessible $xw() {
			return const$xw();
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

		default Vector2.Accessible $yw() {
			return const$yw();
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

		default Vector2.Accessible $zw() {
			return const$zw();
		}

		default Vector2.Accessible $wx() {
			return const$wx();
		}

		default Vector2.Accessible $wy() {
			return const$wy();
		}

		default Vector2.Accessible $wz() {
			return const$wz();
		}

		default Vector2.Accessible $ww() {
			return const$ww();
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

		default Vector3.Accessible $xxw() {
			return const$xxw();
		}

		default Vector3.Accessible $xyx() {
			return const$xyx();
		}

		default Vector3.Accessible $xyy() {
			return const$xyy();
		}

		default Vector3.Accessible $xyz() {
			return const$xyz();
		}

		default Vector3.Accessible $xyw() {
			return const$xyw();
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

		default Vector3.Accessible $xzw() {
			return const$xzw();
		}

		default Vector3.Accessible $xwx() {
			return const$xwx();
		}

		default Vector3.Accessible $xwy() {
			return const$xwy();
		}

		default Vector3.Accessible $xwz() {
			return const$xwz();
		}

		default Vector3.Accessible $xww() {
			return const$xww();
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

		default Vector3.Accessible $yxw() {
			return const$yxw();
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

		default Vector3.Accessible $yyw() {
			return const$yyw();
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

		default Vector3.Accessible $yzw() {
			return const$yzw();
		}

		default Vector3.Accessible $ywx() {
			return const$ywx();
		}

		default Vector3.Accessible $ywy() {
			return const$ywy();
		}

		default Vector3.Accessible $ywz() {
			return const$ywz();
		}

		default Vector3.Accessible $yww() {
			return const$yww();
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

		default Vector3.Accessible $zxw() {
			return const$zxw();
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

		default Vector3.Accessible $zyw() {
			return const$zyw();
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

		default Vector3.Accessible $zzw() {
			return const$zzw();
		}

		default Vector3.Accessible $zwx() {
			return const$zwx();
		}

		default Vector3.Accessible $zwy() {
			return const$zwy();
		}

		default Vector3.Accessible $zwz() {
			return const$zwz();
		}

		default Vector3.Accessible $zww() {
			return const$zww();
		}

		default Vector3.Accessible $wxx() {
			return const$wxx();
		}

		default Vector3.Accessible $wxy() {
			return const$wxy();
		}

		default Vector3.Accessible $wxz() {
			return const$wxz();
		}

		default Vector3.Accessible $wxw() {
			return const$wxw();
		}

		default Vector3.Accessible $wyx() {
			return const$wyx();
		}

		default Vector3.Accessible $wyy() {
			return const$wyy();
		}

		default Vector3.Accessible $wyz() {
			return const$wyz();
		}

		default Vector3.Accessible $wyw() {
			return const$wyw();
		}

		default Vector3.Accessible $wzx() {
			return const$wzx();
		}

		default Vector3.Accessible $wzy() {
			return const$wzy();
		}

		default Vector3.Accessible $wzz() {
			return const$wzz();
		}

		default Vector3.Accessible $wzw() {
			return const$wzw();
		}

		default Vector3.Accessible $wwx() {
			return const$wwx();
		}

		default Vector3.Accessible $wwy() {
			return const$wwy();
		}

		default Vector3.Accessible $wwz() {
			return const$wwz();
		}

		default Vector3.Accessible $www() {
			return const$www();
		}

		default Vector4.Accessible $xxxx() {
			return const$xxxx();
		}

		default Vector4.Accessible $xxxy() {
			return const$xxxy();
		}

		default Vector4.Accessible $xxxz() {
			return const$xxxz();
		}

		default Vector4.Accessible $xxxw() {
			return const$xxxw();
		}

		default Vector4.Accessible $xxyx() {
			return const$xxyx();
		}

		default Vector4.Accessible $xxyy() {
			return const$xxyy();
		}

		default Vector4.Accessible $xxyz() {
			return const$xxyz();
		}

		default Vector4.Accessible $xxyw() {
			return const$xxyw();
		}

		default Vector4.Accessible $xxzx() {
			return const$xxzx();
		}

		default Vector4.Accessible $xxzy() {
			return const$xxzy();
		}

		default Vector4.Accessible $xxzz() {
			return const$xxzz();
		}

		default Vector4.Accessible $xxzw() {
			return const$xxzw();
		}

		default Vector4.Accessible $xxwx() {
			return const$xxwx();
		}

		default Vector4.Accessible $xxwy() {
			return const$xxwy();
		}

		default Vector4.Accessible $xxwz() {
			return const$xxwz();
		}

		default Vector4.Accessible $xxww() {
			return const$xxww();
		}

		default Vector4.Accessible $xyxx() {
			return const$xyxx();
		}

		default Vector4.Accessible $xyxy() {
			return const$xyxy();
		}

		default Vector4.Accessible $xyxz() {
			return const$xyxz();
		}

		default Vector4.Accessible $xyxw() {
			return const$xyxw();
		}

		default Vector4.Accessible $xyyx() {
			return const$xyyx();
		}

		default Vector4.Accessible $xyyy() {
			return const$xyyy();
		}

		default Vector4.Accessible $xyyz() {
			return const$xyyz();
		}

		default Vector4.Accessible $xyyw() {
			return const$xyyw();
		}

		default Vector4.Accessible $xyzx() {
			return const$xyzx();
		}

		default Vector4.Accessible $xyzy() {
			return const$xyzy();
		}

		default Vector4.Accessible $xyzz() {
			return const$xyzz();
		}

		default Vector4.Accessible $xyzw() {
			return this;
		}

		default Vector4.Accessible $xywx() {
			return const$xywx();
		}

		default Vector4.Accessible $xywy() {
			return const$xywy();
		}

		default Vector4.Accessible $xywz() {
			return const$xywz();
		}

		default Vector4.Accessible $xyww() {
			return const$xyww();
		}

		default Vector4.Accessible $xzxx() {
			return const$xzxx();
		}

		default Vector4.Accessible $xzxy() {
			return const$xzxy();
		}

		default Vector4.Accessible $xzxz() {
			return const$xzxz();
		}

		default Vector4.Accessible $xzxw() {
			return const$xzxw();
		}

		default Vector4.Accessible $xzyx() {
			return const$xzyx();
		}

		default Vector4.Accessible $xzyy() {
			return const$xzyy();
		}

		default Vector4.Accessible $xzyz() {
			return const$xzyz();
		}

		default Vector4.Accessible $xzyw() {
			return const$xzyw();
		}

		default Vector4.Accessible $xzzx() {
			return const$xzzx();
		}

		default Vector4.Accessible $xzzy() {
			return const$xzzy();
		}

		default Vector4.Accessible $xzzz() {
			return const$xzzz();
		}

		default Vector4.Accessible $xzzw() {
			return const$xzzw();
		}

		default Vector4.Accessible $xzwx() {
			return const$xzwx();
		}

		default Vector4.Accessible $xzwy() {
			return const$xzwy();
		}

		default Vector4.Accessible $xzwz() {
			return const$xzwz();
		}

		default Vector4.Accessible $xzww() {
			return const$xzww();
		}

		default Vector4.Accessible $xwxx() {
			return const$xwxx();
		}

		default Vector4.Accessible $xwxy() {
			return const$xwxy();
		}

		default Vector4.Accessible $xwxz() {
			return const$xwxz();
		}

		default Vector4.Accessible $xwxw() {
			return const$xwxw();
		}

		default Vector4.Accessible $xwyx() {
			return const$xwyx();
		}

		default Vector4.Accessible $xwyy() {
			return const$xwyy();
		}

		default Vector4.Accessible $xwyz() {
			return const$xwyz();
		}

		default Vector4.Accessible $xwyw() {
			return const$xwyw();
		}

		default Vector4.Accessible $xwzx() {
			return const$xwzx();
		}

		default Vector4.Accessible $xwzy() {
			return const$xwzy();
		}

		default Vector4.Accessible $xwzz() {
			return const$xwzz();
		}

		default Vector4.Accessible $xwzw() {
			return const$xwzw();
		}

		default Vector4.Accessible $xwwx() {
			return const$xwwx();
		}

		default Vector4.Accessible $xwwy() {
			return const$xwwy();
		}

		default Vector4.Accessible $xwwz() {
			return const$xwwz();
		}

		default Vector4.Accessible $xwww() {
			return const$xwww();
		}

		default Vector4.Accessible $yxxx() {
			return const$yxxx();
		}

		default Vector4.Accessible $yxxy() {
			return const$yxxy();
		}

		default Vector4.Accessible $yxxz() {
			return const$yxxz();
		}

		default Vector4.Accessible $yxxw() {
			return const$yxxw();
		}

		default Vector4.Accessible $yxyx() {
			return const$yxyx();
		}

		default Vector4.Accessible $yxyy() {
			return const$yxyy();
		}

		default Vector4.Accessible $yxyz() {
			return const$yxyz();
		}

		default Vector4.Accessible $yxyw() {
			return const$yxyw();
		}

		default Vector4.Accessible $yxzx() {
			return const$yxzx();
		}

		default Vector4.Accessible $yxzy() {
			return const$yxzy();
		}

		default Vector4.Accessible $yxzz() {
			return const$yxzz();
		}

		default Vector4.Accessible $yxzw() {
			return const$yxzw();
		}

		default Vector4.Accessible $yxwx() {
			return const$yxwx();
		}

		default Vector4.Accessible $yxwy() {
			return const$yxwy();
		}

		default Vector4.Accessible $yxwz() {
			return const$yxwz();
		}

		default Vector4.Accessible $yxww() {
			return const$yxww();
		}

		default Vector4.Accessible $yyxx() {
			return const$yyxx();
		}

		default Vector4.Accessible $yyxy() {
			return const$yyxy();
		}

		default Vector4.Accessible $yyxz() {
			return const$yyxz();
		}

		default Vector4.Accessible $yyxw() {
			return const$yyxw();
		}

		default Vector4.Accessible $yyyx() {
			return const$yyyx();
		}

		default Vector4.Accessible $yyyy() {
			return const$yyyy();
		}

		default Vector4.Accessible $yyyz() {
			return const$yyyz();
		}

		default Vector4.Accessible $yyyw() {
			return const$yyyw();
		}

		default Vector4.Accessible $yyzx() {
			return const$yyzx();
		}

		default Vector4.Accessible $yyzy() {
			return const$yyzy();
		}

		default Vector4.Accessible $yyzz() {
			return const$yyzz();
		}

		default Vector4.Accessible $yyzw() {
			return const$yyzw();
		}

		default Vector4.Accessible $yywx() {
			return const$yywx();
		}

		default Vector4.Accessible $yywy() {
			return const$yywy();
		}

		default Vector4.Accessible $yywz() {
			return const$yywz();
		}

		default Vector4.Accessible $yyww() {
			return const$yyww();
		}

		default Vector4.Accessible $yzxx() {
			return const$yzxx();
		}

		default Vector4.Accessible $yzxy() {
			return const$yzxy();
		}

		default Vector4.Accessible $yzxz() {
			return const$yzxz();
		}

		default Vector4.Accessible $yzxw() {
			return const$yzxw();
		}

		default Vector4.Accessible $yzyx() {
			return const$yzyx();
		}

		default Vector4.Accessible $yzyy() {
			return const$yzyy();
		}

		default Vector4.Accessible $yzyz() {
			return const$yzyz();
		}

		default Vector4.Accessible $yzyw() {
			return const$yzyw();
		}

		default Vector4.Accessible $yzzx() {
			return const$yzzx();
		}

		default Vector4.Accessible $yzzy() {
			return const$yzzy();
		}

		default Vector4.Accessible $yzzz() {
			return const$yzzz();
		}

		default Vector4.Accessible $yzzw() {
			return const$yzzw();
		}

		default Vector4.Accessible $yzwx() {
			return const$yzwx();
		}

		default Vector4.Accessible $yzwy() {
			return const$yzwy();
		}

		default Vector4.Accessible $yzwz() {
			return const$yzwz();
		}

		default Vector4.Accessible $yzww() {
			return const$yzww();
		}

		default Vector4.Accessible $ywxx() {
			return const$ywxx();
		}

		default Vector4.Accessible $ywxy() {
			return const$ywxy();
		}

		default Vector4.Accessible $ywxz() {
			return const$ywxz();
		}

		default Vector4.Accessible $ywxw() {
			return const$ywxw();
		}

		default Vector4.Accessible $ywyx() {
			return const$ywyx();
		}

		default Vector4.Accessible $ywyy() {
			return const$ywyy();
		}

		default Vector4.Accessible $ywyz() {
			return const$ywyz();
		}

		default Vector4.Accessible $ywyw() {
			return const$ywyw();
		}

		default Vector4.Accessible $ywzx() {
			return const$ywzx();
		}

		default Vector4.Accessible $ywzy() {
			return const$ywzy();
		}

		default Vector4.Accessible $ywzz() {
			return const$ywzz();
		}

		default Vector4.Accessible $ywzw() {
			return const$ywzw();
		}

		default Vector4.Accessible $ywwx() {
			return const$ywwx();
		}

		default Vector4.Accessible $ywwy() {
			return const$ywwy();
		}

		default Vector4.Accessible $ywwz() {
			return const$ywwz();
		}

		default Vector4.Accessible $ywww() {
			return const$ywww();
		}

		default Vector4.Accessible $zxxx() {
			return const$zxxx();
		}

		default Vector4.Accessible $zxxy() {
			return const$zxxy();
		}

		default Vector4.Accessible $zxxz() {
			return const$zxxz();
		}

		default Vector4.Accessible $zxxw() {
			return const$zxxw();
		}

		default Vector4.Accessible $zxyx() {
			return const$zxyx();
		}

		default Vector4.Accessible $zxyy() {
			return const$zxyy();
		}

		default Vector4.Accessible $zxyz() {
			return const$zxyz();
		}

		default Vector4.Accessible $zxyw() {
			return const$zxyw();
		}

		default Vector4.Accessible $zxzx() {
			return const$zxzx();
		}

		default Vector4.Accessible $zxzy() {
			return const$zxzy();
		}

		default Vector4.Accessible $zxzz() {
			return const$zxzz();
		}

		default Vector4.Accessible $zxzw() {
			return const$zxzw();
		}

		default Vector4.Accessible $zxwx() {
			return const$zxwx();
		}

		default Vector4.Accessible $zxwy() {
			return const$zxwy();
		}

		default Vector4.Accessible $zxwz() {
			return const$zxwz();
		}

		default Vector4.Accessible $zxww() {
			return const$zxww();
		}

		default Vector4.Accessible $zyxx() {
			return const$zyxx();
		}

		default Vector4.Accessible $zyxy() {
			return const$zyxy();
		}

		default Vector4.Accessible $zyxz() {
			return const$zyxz();
		}

		default Vector4.Accessible $zyxw() {
			return const$zyxw();
		}

		default Vector4.Accessible $zyyx() {
			return const$zyyx();
		}

		default Vector4.Accessible $zyyy() {
			return const$zyyy();
		}

		default Vector4.Accessible $zyyz() {
			return const$zyyz();
		}

		default Vector4.Accessible $zyyw() {
			return const$zyyw();
		}

		default Vector4.Accessible $zyzx() {
			return const$zyzx();
		}

		default Vector4.Accessible $zyzy() {
			return const$zyzy();
		}

		default Vector4.Accessible $zyzz() {
			return const$zyzz();
		}

		default Vector4.Accessible $zyzw() {
			return const$zyzw();
		}

		default Vector4.Accessible $zywx() {
			return const$zywx();
		}

		default Vector4.Accessible $zywy() {
			return const$zywy();
		}

		default Vector4.Accessible $zywz() {
			return const$zywz();
		}

		default Vector4.Accessible $zyww() {
			return const$zyww();
		}

		default Vector4.Accessible $zzxx() {
			return const$zzxx();
		}

		default Vector4.Accessible $zzxy() {
			return const$zzxy();
		}

		default Vector4.Accessible $zzxz() {
			return const$zzxz();
		}

		default Vector4.Accessible $zzxw() {
			return const$zzxw();
		}

		default Vector4.Accessible $zzyx() {
			return const$zzyx();
		}

		default Vector4.Accessible $zzyy() {
			return const$zzyy();
		}

		default Vector4.Accessible $zzyz() {
			return const$zzyz();
		}

		default Vector4.Accessible $zzyw() {
			return const$zzyw();
		}

		default Vector4.Accessible $zzzx() {
			return const$zzzx();
		}

		default Vector4.Accessible $zzzy() {
			return const$zzzy();
		}

		default Vector4.Accessible $zzzz() {
			return const$zzzz();
		}

		default Vector4.Accessible $zzzw() {
			return const$zzzw();
		}

		default Vector4.Accessible $zzwx() {
			return const$zzwx();
		}

		default Vector4.Accessible $zzwy() {
			return const$zzwy();
		}

		default Vector4.Accessible $zzwz() {
			return const$zzwz();
		}

		default Vector4.Accessible $zzww() {
			return const$zzww();
		}

		default Vector4.Accessible $zwxx() {
			return const$zwxx();
		}

		default Vector4.Accessible $zwxy() {
			return const$zwxy();
		}

		default Vector4.Accessible $zwxz() {
			return const$zwxz();
		}

		default Vector4.Accessible $zwxw() {
			return const$zwxw();
		}

		default Vector4.Accessible $zwyx() {
			return const$zwyx();
		}

		default Vector4.Accessible $zwyy() {
			return const$zwyy();
		}

		default Vector4.Accessible $zwyz() {
			return const$zwyz();
		}

		default Vector4.Accessible $zwyw() {
			return const$zwyw();
		}

		default Vector4.Accessible $zwzx() {
			return const$zwzx();
		}

		default Vector4.Accessible $zwzy() {
			return const$zwzy();
		}

		default Vector4.Accessible $zwzz() {
			return const$zwzz();
		}

		default Vector4.Accessible $zwzw() {
			return const$zwzw();
		}

		default Vector4.Accessible $zwwx() {
			return const$zwwx();
		}

		default Vector4.Accessible $zwwy() {
			return const$zwwy();
		}

		default Vector4.Accessible $zwwz() {
			return const$zwwz();
		}

		default Vector4.Accessible $zwww() {
			return const$zwww();
		}

		default Vector4.Accessible $wxxx() {
			return const$wxxx();
		}

		default Vector4.Accessible $wxxy() {
			return const$wxxy();
		}

		default Vector4.Accessible $wxxz() {
			return const$wxxz();
		}

		default Vector4.Accessible $wxxw() {
			return const$wxxw();
		}

		default Vector4.Accessible $wxyx() {
			return const$wxyx();
		}

		default Vector4.Accessible $wxyy() {
			return const$wxyy();
		}

		default Vector4.Accessible $wxyz() {
			return const$wxyz();
		}

		default Vector4.Accessible $wxyw() {
			return const$wxyw();
		}

		default Vector4.Accessible $wxzx() {
			return const$wxzx();
		}

		default Vector4.Accessible $wxzy() {
			return const$wxzy();
		}

		default Vector4.Accessible $wxzz() {
			return const$wxzz();
		}

		default Vector4.Accessible $wxzw() {
			return const$wxzw();
		}

		default Vector4.Accessible $wxwx() {
			return const$wxwx();
		}

		default Vector4.Accessible $wxwy() {
			return const$wxwy();
		}

		default Vector4.Accessible $wxwz() {
			return const$wxwz();
		}

		default Vector4.Accessible $wxww() {
			return const$wxww();
		}

		default Vector4.Accessible $wyxx() {
			return const$wyxx();
		}

		default Vector4.Accessible $wyxy() {
			return const$wyxy();
		}

		default Vector4.Accessible $wyxz() {
			return const$wyxz();
		}

		default Vector4.Accessible $wyxw() {
			return const$wyxw();
		}

		default Vector4.Accessible $wyyx() {
			return const$wyyx();
		}

		default Vector4.Accessible $wyyy() {
			return const$wyyy();
		}

		default Vector4.Accessible $wyyz() {
			return const$wyyz();
		}

		default Vector4.Accessible $wyyw() {
			return const$wyyw();
		}

		default Vector4.Accessible $wyzx() {
			return const$wyzx();
		}

		default Vector4.Accessible $wyzy() {
			return const$wyzy();
		}

		default Vector4.Accessible $wyzz() {
			return const$wyzz();
		}

		default Vector4.Accessible $wyzw() {
			return const$wyzw();
		}

		default Vector4.Accessible $wywx() {
			return const$wywx();
		}

		default Vector4.Accessible $wywy() {
			return const$wywy();
		}

		default Vector4.Accessible $wywz() {
			return const$wywz();
		}

		default Vector4.Accessible $wyww() {
			return const$wyww();
		}

		default Vector4.Accessible $wzxx() {
			return const$wzxx();
		}

		default Vector4.Accessible $wzxy() {
			return const$wzxy();
		}

		default Vector4.Accessible $wzxz() {
			return const$wzxz();
		}

		default Vector4.Accessible $wzxw() {
			return const$wzxw();
		}

		default Vector4.Accessible $wzyx() {
			return const$wzyx();
		}

		default Vector4.Accessible $wzyy() {
			return const$wzyy();
		}

		default Vector4.Accessible $wzyz() {
			return const$wzyz();
		}

		default Vector4.Accessible $wzyw() {
			return const$wzyw();
		}

		default Vector4.Accessible $wzzx() {
			return const$wzzx();
		}

		default Vector4.Accessible $wzzy() {
			return const$wzzy();
		}

		default Vector4.Accessible $wzzz() {
			return const$wzzz();
		}

		default Vector4.Accessible $wzzw() {
			return const$wzzw();
		}

		default Vector4.Accessible $wzwx() {
			return const$wzwx();
		}

		default Vector4.Accessible $wzwy() {
			return const$wzwy();
		}

		default Vector4.Accessible $wzwz() {
			return const$wzwz();
		}

		default Vector4.Accessible $wzww() {
			return const$wzww();
		}

		default Vector4.Accessible $wwxx() {
			return const$wwxx();
		}

		default Vector4.Accessible $wwxy() {
			return const$wwxy();
		}

		default Vector4.Accessible $wwxz() {
			return const$wwxz();
		}

		default Vector4.Accessible $wwxw() {
			return const$wwxw();
		}

		default Vector4.Accessible $wwyx() {
			return const$wwyx();
		}

		default Vector4.Accessible $wwyy() {
			return const$wwyy();
		}

		default Vector4.Accessible $wwyz() {
			return const$wwyz();
		}

		default Vector4.Accessible $wwyw() {
			return const$wwyw();
		}

		default Vector4.Accessible $wwzx() {
			return const$wwzx();
		}

		default Vector4.Accessible $wwzy() {
			return const$wwzy();
		}

		default Vector4.Accessible $wwzz() {
			return const$wwzz();
		}

		default Vector4.Accessible $wwzw() {
			return const$wwzw();
		}

		default Vector4.Accessible $wwwx() {
			return const$wwwx();
		}

		default Vector4.Accessible $wwwy() {
			return const$wwwy();
		}

		default Vector4.Accessible $wwwz() {
			return const$wwwz();
		}

		default Vector4.Accessible $wwww() {
			return const$wwww();
		}

	}

	interface Mutable {

		void x(double x);
		void y(double y);
		void z(double z);
		void w(double w);

		default void xy(double v) {
			x(v);
			y(v);
		}

		default void xz(double v) {
			x(v);
			z(v);
		}

		default void xw(double v) {
			x(v);
			w(v);
		}

		default void yz(double v) {
			y(v);
			z(v);
		}

		default void yw(double v) {
			y(v);
			w(v);
		}

		default void zw(double v) {
			z(v);
			w(v);
		}

		default void xyz(double v) {
			x(v);
			y(v);
			z(v);
		}

		default void xyw(double v) {
			x(v);
			y(v);
			w(v);
		}

		default void xzw(double v) {
			x(v);
			z(v);
			w(v);
		}

		default void yzw(double v) {
			y(v);
			z(v);
			w(v);
		}

		default void xyzw(double v) {
			x(v);
			y(v);
			z(v);
			w(v);
		}

		default void xy(double x, double y) {
			x(x);
			y(y);
		}

		default void xz(double x, double z) {
			x(x);
			z(z);
		}

		default void xw(double x, double w) {
			x(x);
			w(w);
		}

		default void yz(double y, double z) {
			y(y);
			z(z);
		}

		default void yw(double y, double w) {
			y(y);
			w(w);
		}

		default void zw(double z, double w) {
			z(z);
			w(w);
		}

		default void xyz(double x, double y, double z) {
			x(x);
			y(y);
			z(z);
		}

		default void xyw(double x, double y, double w) {
			x(x);
			y(y);
			w(w);
		}

		default void xzw(double x, double z, double w) {
			x(x);
			z(z);
			w(w);
		}

		default void yzw(double y, double z, double w) {
			y(y);
			z(z);
			w(w);
		}

		default void xyzw(double x, double y, double z, double w) {
			x(x);
			y(y);
			z(z);
			w(w);
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

		default void w(Value.Accessible w) {
			w(w.get());
		}

		default void xy(Vector2.Accessible v) {
			xy(v.x(), v.y());
		}

		default void xz(Vector2.Accessible v) {
			xz(v.x(), v.y());
		}

		default void xw(Vector2.Accessible v) {
			xw(v.x(), v.y());
		}

		default void yx(Vector2.Accessible v) {
			xy(v.y(), v.x());
		}

		default void yz(Vector2.Accessible v) {
			yz(v.x(), v.y());
		}

		default void yw(Vector2.Accessible v) {
			yw(v.x(), v.y());
		}

		default void zx(Vector2.Accessible v) {
			xz(v.y(), v.x());
		}

		default void zy(Vector2.Accessible v) {
			yz(v.y(), v.x());
		}

		default void zw(Vector2.Accessible v) {
			zw(v.x(), v.y());
		}

		default void wx(Vector2.Accessible v) {
			xw(v.y(), v.x());
		}

		default void wy(Vector2.Accessible v) {
			yw(v.y(), v.x());
		}

		default void wz(Vector2.Accessible v) {
			zw(v.y(), v.x());
		}

		default void xyz(Vector3.Accessible v) {
			xyz(v.x(), v.y(), v.z());
		}

		default void xyw(Vector3.Accessible v) {
			xyw(v.x(), v.y(), v.z());
		}

		default void xzy(Vector3.Accessible v) {
			xyz(v.x(), v.z(), v.y());
		}

		default void xzw(Vector3.Accessible v) {
			xzw(v.x(), v.y(), v.z());
		}

		default void xwy(Vector3.Accessible v) {
			xyw(v.x(), v.z(), v.y());
		}

		default void xwz(Vector3.Accessible v) {
			xzw(v.x(), v.z(), v.y());
		}

		default void yxz(Vector3.Accessible v) {
			xyz(v.y(), v.x(), v.z());
		}

		default void yxw(Vector3.Accessible v) {
			xyw(v.y(), v.x(), v.z());
		}

		default void yzx(Vector3.Accessible v) {
			xyz(v.z(), v.x(), v.y());
		}

		default void yzw(Vector3.Accessible v) {
			yzw(v.x(), v.y(), v.z());
		}

		default void ywx(Vector3.Accessible v) {
			xyw(v.z(), v.x(), v.y());
		}

		default void ywz(Vector3.Accessible v) {
			yzw(v.x(), v.z(), v.y());
		}

		default void zxy(Vector3.Accessible v) {
			xyz(v.y(), v.z(), v.x());
		}

		default void zxw(Vector3.Accessible v) {
			xzw(v.y(), v.x(), v.z());
		}

		default void zyx(Vector3.Accessible v) {
			xyz(v.z(), v.y(), v.x());
		}

		default void zyw(Vector3.Accessible v) {
			yzw(v.y(), v.x(), v.z());
		}

		default void zwx(Vector3.Accessible v) {
			xzw(v.z(), v.x(), v.y());
		}

		default void zwy(Vector3.Accessible v) {
			yzw(v.z(), v.x(), v.y());
		}

		default void wxy(Vector3.Accessible v) {
			xyw(v.y(), v.z(), v.x());
		}

		default void wxz(Vector3.Accessible v) {
			xzw(v.y(), v.z(), v.x());
		}

		default void wyx(Vector3.Accessible v) {
			xyw(v.z(), v.y(), v.x());
		}

		default void wyz(Vector3.Accessible v) {
			yzw(v.y(), v.z(), v.x());
		}

		default void wzx(Vector3.Accessible v) {
			xzw(v.z(), v.y(), v.x());
		}

		default void wzy(Vector3.Accessible v) {
			yzw(v.z(), v.y(), v.x());
		}

		default void xyzw(Vector4.Accessible v) {
			xyzw(v.x(), v.y(), v.z(), v.w());
		}

		default void xywz(Vector4.Accessible v) {
			xyzw(v.x(), v.y(), v.w(), v.z());
		}

		default void xzyw(Vector4.Accessible v) {
			xyzw(v.x(), v.z(), v.y(), v.w());
		}

		default void xzwy(Vector4.Accessible v) {
			xyzw(v.x(), v.w(), v.y(), v.z());
		}

		default void xwyz(Vector4.Accessible v) {
			xyzw(v.x(), v.z(), v.w(), v.y());
		}

		default void xwzy(Vector4.Accessible v) {
			xyzw(v.x(), v.w(), v.z(), v.y());
		}

		default void yxzw(Vector4.Accessible v) {
			xyzw(v.y(), v.x(), v.z(), v.w());
		}

		default void yxwz(Vector4.Accessible v) {
			xyzw(v.y(), v.x(), v.w(), v.z());
		}

		default void yzxw(Vector4.Accessible v) {
			xyzw(v.z(), v.x(), v.y(), v.w());
		}

		default void yzwx(Vector4.Accessible v) {
			xyzw(v.w(), v.x(), v.y(), v.z());
		}

		default void ywxz(Vector4.Accessible v) {
			xyzw(v.z(), v.x(), v.w(), v.y());
		}

		default void ywzx(Vector4.Accessible v) {
			xyzw(v.w(), v.x(), v.z(), v.y());
		}

		default void zxyw(Vector4.Accessible v) {
			xyzw(v.y(), v.z(), v.x(), v.w());
		}

		default void zxwy(Vector4.Accessible v) {
			xyzw(v.y(), v.w(), v.x(), v.z());
		}

		default void zyxw(Vector4.Accessible v) {
			xyzw(v.z(), v.y(), v.x(), v.w());
		}

		default void zywx(Vector4.Accessible v) {
			xyzw(v.w(), v.y(), v.x(), v.z());
		}

		default void zwxy(Vector4.Accessible v) {
			xyzw(v.z(), v.w(), v.x(), v.y());
		}

		default void zwyx(Vector4.Accessible v) {
			xyzw(v.w(), v.z(), v.x(), v.y());
		}

		default void wxyz(Vector4.Accessible v) {
			xyzw(v.y(), v.z(), v.w(), v.x());
		}

		default void wxzy(Vector4.Accessible v) {
			xyzw(v.y(), v.w(), v.z(), v.x());
		}

		default void wyxz(Vector4.Accessible v) {
			xyzw(v.z(), v.y(), v.w(), v.x());
		}

		default void wyzx(Vector4.Accessible v) {
			xyzw(v.w(), v.y(), v.z(), v.x());
		}

		default void wzxy(Vector4.Accessible v) {
			xyzw(v.z(), v.w(), v.y(), v.x());
		}

		default void wzyx(Vector4.Accessible v) {
			xyzw(v.w(), v.z(), v.y(), v.x());
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

		default Value.Mutable $w() {
			return this::w;
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

		default Vector2.Mutable $xw() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.w(y); }
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

		default Vector2.Mutable $yw() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.w(y); }
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

		default Vector2.Mutable $zw() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.w(y); }
			};
		}

		default Vector2.Mutable $wx() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.x(y); }
			};
		}

		default Vector2.Mutable $wy() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.y(y); }
			};
		}

		default Vector2.Mutable $wz() {
			return new Vector2.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.z(y); }
			};
		}

		default Vector3.Mutable $xyz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $xyw() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.w(z); }
			};
		}

		default Vector3.Mutable $xzy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $xzw() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.w(z); }
			};
		}

		default Vector3.Mutable $xwy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $xwz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $yxz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $yxw() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.w(z); }
			};
		}

		default Vector3.Mutable $yzx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $yzw() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.w(z); }
			};
		}

		default Vector3.Mutable $ywx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $ywz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $zxy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $zxw() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.w(z); }
			};
		}

		default Vector3.Mutable $zyx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $zyw() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.w(z); }
			};
		}

		default Vector3.Mutable $zwx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $zwy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $wxy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector3.Mutable $wxz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $wyx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $wyz() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.z(z); }
			};
		}

		default Vector3.Mutable $wzx() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.x(z); }
			};
		}

		default Vector3.Mutable $wzy() {
			return new Vector3.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.y(z); }
			};
		}

		default Vector4.Mutable $xyzw() {
			return this;
		}

		default Vector4.Mutable $xywz() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.w(z); }
				public void w(double w) { Mutable.this.z(w); }
			};
		}

		default Vector4.Mutable $xzyw() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.y(z); }
				public void w(double w) { Mutable.this.w(w); }
			};
		}

		default Vector4.Mutable $xzwy() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.w(z); }
				public void w(double w) { Mutable.this.y(w); }
			};
		}

		default Vector4.Mutable $xwyz() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.y(z); }
				public void w(double w) { Mutable.this.z(w); }
			};
		}

		default Vector4.Mutable $xwzy() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.x(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.z(z); }
				public void w(double w) { Mutable.this.y(w); }
			};
		}

		default Vector4.Mutable $yxzw() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.z(z); }
				public void w(double w) { Mutable.this.w(w); }
			};
		}

		default Vector4.Mutable $yxwz() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.w(z); }
				public void w(double w) { Mutable.this.z(w); }
			};
		}

		default Vector4.Mutable $yzxw() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.x(z); }
				public void w(double w) { Mutable.this.w(w); }
			};
		}

		default Vector4.Mutable $yzwx() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.w(z); }
				public void w(double w) { Mutable.this.x(w); }
			};
		}

		default Vector4.Mutable $ywxz() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.x(z); }
				public void w(double w) { Mutable.this.z(w); }
			};
		}

		default Vector4.Mutable $ywzx() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.y(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.z(z); }
				public void w(double w) { Mutable.this.x(w); }
			};
		}

		default Vector4.Mutable $zxyw() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.y(z); }
				public void w(double w) { Mutable.this.w(w); }
			};
		}

		default Vector4.Mutable $zxwy() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.w(z); }
				public void w(double w) { Mutable.this.y(w); }
			};
		}

		default Vector4.Mutable $zyxw() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.x(z); }
				public void w(double w) { Mutable.this.w(w); }
			};
		}

		default Vector4.Mutable $zywx() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.w(z); }
				public void w(double w) { Mutable.this.x(w); }
			};
		}

		default Vector4.Mutable $zwxy() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.x(z); }
				public void w(double w) { Mutable.this.y(w); }
			};
		}

		default Vector4.Mutable $zwyx() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.z(x); }
				public void y(double y) { Mutable.this.w(y); }
				public void z(double z) { Mutable.this.y(z); }
				public void w(double w) { Mutable.this.x(w); }
			};
		}

		default Vector4.Mutable $wxyz() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.y(z); }
				public void w(double w) { Mutable.this.z(w); }
			};
		}

		default Vector4.Mutable $wxzy() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.x(y); }
				public void z(double z) { Mutable.this.z(z); }
				public void w(double w) { Mutable.this.y(w); }
			};
		}

		default Vector4.Mutable $wyxz() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.x(z); }
				public void w(double w) { Mutable.this.z(w); }
			};
		}

		default Vector4.Mutable $wyzx() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.y(y); }
				public void z(double z) { Mutable.this.z(z); }
				public void w(double w) { Mutable.this.x(w); }
			};
		}

		default Vector4.Mutable $wzxy() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.x(z); }
				public void w(double w) { Mutable.this.y(w); }
			};
		}

		default Vector4.Mutable $wzyx() {
			return new Vector4.Mutable() {
				public void x(double x) { Mutable.this.w(x); }
				public void y(double y) { Mutable.this.z(y); }
				public void z(double z) { Mutable.this.y(z); }
				public void w(double w) { Mutable.this.x(w); }
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

		default Value.AccessibleAndMutable $w() {
			return new Value.AccessibleAndMutable() {
				public double get() { return AccessibleAndMutable.this.w(); }
				public void set(double v) { AccessibleAndMutable.this.w(v); }
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

		default Vector2.AccessibleAndMutable $xw() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
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

		default Vector2.AccessibleAndMutable $yw() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
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

		default Vector2.AccessibleAndMutable $zw() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
			};
		}

		default Vector2.AccessibleAndMutable $wx() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
			};
		}

		default Vector2.AccessibleAndMutable $wy() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
			};
		}

		default Vector2.AccessibleAndMutable $wz() {
			return new Vector2.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
			};
		}

		default Vector3.AccessibleAndMutable $xyz() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
			};
		}

		default Vector3.AccessibleAndMutable $xyw() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
			};
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

		default Vector3.AccessibleAndMutable $xzw() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
			};
		}

		default Vector3.AccessibleAndMutable $xwy() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
			};
		}

		default Vector3.AccessibleAndMutable $xwz() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
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

		default Vector3.AccessibleAndMutable $yxw() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
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

		default Vector3.AccessibleAndMutable $yzw() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
			};
		}

		default Vector3.AccessibleAndMutable $ywx() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
			};
		}

		default Vector3.AccessibleAndMutable $ywz() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
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

		default Vector3.AccessibleAndMutable $zxw() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
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

		default Vector3.AccessibleAndMutable $zyw() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
			};
		}

		default Vector3.AccessibleAndMutable $zwx() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
			};
		}

		default Vector3.AccessibleAndMutable $zwy() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
			};
		}

		default Vector3.AccessibleAndMutable $wxy() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
			};
		}

		default Vector3.AccessibleAndMutable $wxz() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
			};
		}

		default Vector3.AccessibleAndMutable $wyx() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
			};
		}

		default Vector3.AccessibleAndMutable $wyz() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
			};
		}

		default Vector3.AccessibleAndMutable $wzx() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
			};
		}

		default Vector3.AccessibleAndMutable $wzy() {
			return new Vector3.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
			};
		}

		default Vector4.AccessibleAndMutable $xyzw() {
			return this;
		}

		default Vector4.AccessibleAndMutable $xywz() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public double w() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
				public void w(double w) { AccessibleAndMutable.this.z(w); }
			};
		}

		default Vector4.AccessibleAndMutable $xzyw() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public double w() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
				public void w(double w) { AccessibleAndMutable.this.w(w); }
			};
		}

		default Vector4.AccessibleAndMutable $xzwy() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public double w() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
				public void w(double w) { AccessibleAndMutable.this.y(w); }
			};
		}

		default Vector4.AccessibleAndMutable $xwyz() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public double w() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
				public void w(double w) { AccessibleAndMutable.this.z(w); }
			};
		}

		default Vector4.AccessibleAndMutable $xwzy() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.x(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public double w() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.x(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
				public void w(double w) { AccessibleAndMutable.this.y(w); }
			};
		}

		default Vector4.AccessibleAndMutable $yxzw() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public double w() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
				public void w(double w) { AccessibleAndMutable.this.w(w); }
			};
		}

		default Vector4.AccessibleAndMutable $yxwz() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public double w() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
				public void w(double w) { AccessibleAndMutable.this.z(w); }
			};
		}

		default Vector4.AccessibleAndMutable $yzxw() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public double w() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
				public void w(double w) { AccessibleAndMutable.this.w(w); }
			};
		}

		default Vector4.AccessibleAndMutable $yzwx() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public double w() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
				public void w(double w) { AccessibleAndMutable.this.x(w); }
			};
		}

		default Vector4.AccessibleAndMutable $ywxz() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public double w() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
				public void w(double w) { AccessibleAndMutable.this.z(w); }
			};
		}

		default Vector4.AccessibleAndMutable $ywzx() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.y(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public double w() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.y(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
				public void w(double w) { AccessibleAndMutable.this.x(w); }
			};
		}

		default Vector4.AccessibleAndMutable $zxyw() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public double w() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
				public void w(double w) { AccessibleAndMutable.this.w(w); }
			};
		}

		default Vector4.AccessibleAndMutable $zxwy() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public double w() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
				public void w(double w) { AccessibleAndMutable.this.y(w); }
			};
		}

		default Vector4.AccessibleAndMutable $zyxw() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public double w() { return AccessibleAndMutable.this.w(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
				public void w(double w) { AccessibleAndMutable.this.w(w); }
			};
		}

		default Vector4.AccessibleAndMutable $zywx() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.w(); }
				public double w() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.w(z); }
				public void w(double w) { AccessibleAndMutable.this.x(w); }
			};
		}

		default Vector4.AccessibleAndMutable $zwxy() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public double w() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
				public void w(double w) { AccessibleAndMutable.this.y(w); }
			};
		}

		default Vector4.AccessibleAndMutable $zwyx() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.z(); }
				public double y() { return AccessibleAndMutable.this.w(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public double w() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.z(x); }
				public void y(double y) { AccessibleAndMutable.this.w(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
				public void w(double w) { AccessibleAndMutable.this.x(w); }
			};
		}

		default Vector4.AccessibleAndMutable $wxyz() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public double w() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
				public void w(double w) { AccessibleAndMutable.this.z(w); }
			};
		}

		default Vector4.AccessibleAndMutable $wxzy() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.x(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public double w() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.x(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
				public void w(double w) { AccessibleAndMutable.this.y(w); }
			};
		}

		default Vector4.AccessibleAndMutable $wyxz() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public double w() { return AccessibleAndMutable.this.z(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
				public void w(double w) { AccessibleAndMutable.this.z(w); }
			};
		}

		default Vector4.AccessibleAndMutable $wyzx() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.y(); }
				public double z() { return AccessibleAndMutable.this.z(); }
				public double w() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.y(y); }
				public void z(double z) { AccessibleAndMutable.this.z(z); }
				public void w(double w) { AccessibleAndMutable.this.x(w); }
			};
		}

		default Vector4.AccessibleAndMutable $wzxy() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.x(); }
				public double w() { return AccessibleAndMutable.this.y(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.x(z); }
				public void w(double w) { AccessibleAndMutable.this.y(w); }
			};
		}

		default Vector4.AccessibleAndMutable $wzyx() {
			return new Vector4.AccessibleAndMutable() {
				public double x() { return AccessibleAndMutable.this.w(); }
				public double y() { return AccessibleAndMutable.this.z(); }
				public double z() { return AccessibleAndMutable.this.y(); }
				public double w() { return AccessibleAndMutable.this.x(); }
				public void x(double x) { AccessibleAndMutable.this.w(x); }
				public void y(double y) { AccessibleAndMutable.this.z(y); }
				public void z(double z) { AccessibleAndMutable.this.y(z); }
				public void w(double w) { AccessibleAndMutable.this.x(w); }
			};
		}

	}

}

