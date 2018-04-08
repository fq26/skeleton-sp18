class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel; // current velocity in x direction
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double G = 6.67e-11;

	/** Plant Constructor */
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
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

	/** calculates the distance between two Planets */
	public double calcDistance(Planet p) {
		double dx = Math.abs(p.xxPos - this.xxPos);
		double dy = Math.abs(p.yyPos - this.yyPos);
		return Math.sqrt(dx * dx + dy * dy);
	}

	/** the force exerted on this planet by the given planet */
	public double calcForceExertedBy(Planet p) {
		double r = calcDistance(p);
		return G * this.mass * p.mass / (r * r);
	} 

	/** the force exerted in the x direction */
	public double calcForceExertedByX(Planet p) {
		double F = calcForceExertedBy(p);
		double dx = Math.abs(p.xxPos - this.xxPos);
		double r = calcDistance(p);
		return F * dx / r;
	}

	/** the force exerted in the y direction */
	public double calcForceExertedByY(Planet p) {
		double F = calcForceExertedBy(p);
		double dy = Math.abs(p.yyPos - this.yyPos);
		double r = calcDistance(p);
		return F * dy / r;
	}

	public double calcNetForceExertedByX(Planet[] pArray) {
		double Fx = 0.0;
		for(Planet p : pArray) {
			if(p.equals(this)) continue;
			Fx += calcForceExertedByX(p);
		}
		return Fx;
	}

	public double calcNetForceExertedByY(Planet[] pArray) {
		double Fy = 0.0;
		for(Planet p : pArray) {
			if(p.equals(this)) continue;
			Fy += calcForceExertedByY(p);
		}
		return Fy;
	}

	/**
	 *	determines how much the forces exerted on the planet will cause that planet to accelerate
     *  @param  dt 		time period
     *  @param  Fx      force value to be added in the x postion
     *  @param  Fy      force value to be added in the y postion
     *	return the resulting change in the planet’s velocity and position in a small period of time dt
     */
	public void update(double dt, double Fx, double Fy) {
		double ax = Fx / this.mass;
		double ay = Fy / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;

		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}
}