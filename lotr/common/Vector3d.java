/*
 * Decompiled with CFR 0.148.
 */
package lotr.common;

public class Vector3d {
    public double x;
    public double y;
    public double z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d sub(Vector3d vec) {
        return new Vector3d(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector3d normalise() {
        double d = this.magnitude();
        if (d != 0.0) {
            this.multiply(1.0 / d);
        }
        return this;
    }

    private Vector3d multiply(double d) {
        this.x *= d;
        this.y *= d;
        this.z *= d;
        return this;
    }
}

