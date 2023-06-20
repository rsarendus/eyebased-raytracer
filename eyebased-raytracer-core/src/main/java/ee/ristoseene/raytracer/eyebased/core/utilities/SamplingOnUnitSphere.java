package ee.ristoseene.raytracer.eyebased.core.utilities;

import ee.ristoseene.vecmath.Vector3;

public final class SamplingOnUnitSphere {

    public static final double PIx2 = Math.PI * 2.0;

    public static <R> R mapToPointOnUnitSphere(
            final Vector3.Accessible direction, final double theta, final double phi, final Vector3.Factory<R> resultFactory
    ) {
        // Direction vector (x, y, z)
        final double directionX = direction.x();
        final double directionY = direction.y();
        final double directionZ = direction.z();

        // Direction vector squared
        final double directionXX = directionX * directionX;
        final double directionYY = directionY * directionY;
        final double directionZZ = directionZ * directionZ;

        // Vector perpendicular to the direction (x, y, z)
        double perpendicularX, perpendicularY, perpendicularZ;

        if (directionXX > directionZZ) {
            final double mul = 1.0 / Math.sqrt(directionXX + directionYY);
            perpendicularX = directionY * mul;
            perpendicularY = -directionX * mul;
            perpendicularZ = 0.0;
        } else {
            final double mul = 1.0 / Math.sqrt(directionYY + directionZZ);
            perpendicularX = 0.0;
            perpendicularY = -directionZ * mul;
            perpendicularZ = directionY * mul;
        }

        final double sinTheta = Math.sin(theta);
        final double sphereX = sinTheta * Math.cos(phi);
        final double sphereY = sinTheta * Math.sin(phi);
        final double sphereZ = Math.cos(theta);

        // perpendicular * sphereX + cross(direction, perpendicular) * sphereY + direction * sphereZ
        return resultFactory.create(
                perpendicularX * sphereX + (directionY * perpendicularZ - perpendicularY * directionZ) * sphereY + directionX * sphereZ,
                perpendicularY * sphereX + (directionZ * perpendicularX - perpendicularZ * directionX) * sphereY + directionY * sphereZ,
                perpendicularZ * sphereX + (directionX * perpendicularY - perpendicularX * directionY) * sphereY + directionZ * sphereZ
        );
    }

    public static void mapToPointOnUnitSphere(
            final Vector3.Consumer resultConsumer, final Vector3.Accessible direction, final double theta, final double phi
    ) {
        // Direction vector (x, y, z)
        final double directionX = direction.x();
        final double directionY = direction.y();
        final double directionZ = direction.z();

        // Direction vector squared
        final double directionXX = directionX * directionX;
        final double directionYY = directionY * directionY;
        final double directionZZ = directionZ * directionZ;

        // Vector perpendicular to the direction (x, y, z)
        double perpendicularX, perpendicularY, perpendicularZ;

        if (directionXX > directionZZ) {
            final double mul = 1.0 / Math.sqrt(directionXX + directionYY);
            perpendicularX = directionY * mul;
            perpendicularY = -directionX * mul;
            perpendicularZ = 0.0;
        } else {
            final double mul = 1.0 / Math.sqrt(directionYY + directionZZ);
            perpendicularX = 0.0;
            perpendicularY = -directionZ * mul;
            perpendicularZ = directionY * mul;
        }

        final double sinTheta = Math.sin(theta);
        final double sphereX = sinTheta * Math.cos(phi);
        final double sphereY = sinTheta * Math.sin(phi);
        final double sphereZ = Math.cos(theta);

        // perpendicular * sphereX + cross(direction, perpendicular) * sphereY + direction * sphereZ
        resultConsumer.xyz(
                perpendicularX * sphereX + (directionY * perpendicularZ - perpendicularY * directionZ) * sphereY + directionX * sphereZ,
                perpendicularY * sphereX + (directionZ * perpendicularX - perpendicularZ * directionX) * sphereY + directionY * sphereZ,
                perpendicularZ * sphereX + (directionX * perpendicularY - perpendicularX * directionY) * sphereY + directionZ * sphereZ
        );
    }

    private SamplingOnUnitSphere() {}

}
