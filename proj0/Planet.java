public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return G * mass * p.mass / ( calcDistance(p) * calcDistance(p) );
    }

    public double calcForceExertedByX(Planet p) {
        if (p.xxPos > xxPos) {
            return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
        } else {
            return calcForceExertedBy(p) * (xxPos - p.xxPos) / calcDistance(p);
        }
    }

    public double calcForceExertedByY(Planet p) {
        if (p.yyPos > yyPos) {
            return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
        } else {
            return calcForceExertedBy(p) * (yyPos - p.yyPos) / calcDistance(p);
        }
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double res = 0;
        for (Planet p :
                ps) {
            if (!this.equals(p)) {
                res += calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double res = 0;
        for (Planet p :
                ps) {
            if (!this.equals(p)) {
                res += calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
            }
        }
        return res;
    }

    public void update(double period, double xxForce, double yyForce) {
        double ax = xxForce / mass;
        double ay = yyForce / mass;
        xxVel += period * ax;
        yyVel += period * ay;
        xxPos += period * xxVel;
        yyPos += period * yyVel;
    }
}