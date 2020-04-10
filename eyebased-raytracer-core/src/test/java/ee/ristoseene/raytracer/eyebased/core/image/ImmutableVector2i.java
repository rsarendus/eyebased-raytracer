package ee.ristoseene.raytracer.eyebased.core.image;

import java.util.Objects;

public final class ImmutableVector2i {

    private final int x;
    private final int y;

    public ImmutableVector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableVector2i that = (ImmutableVector2i) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
