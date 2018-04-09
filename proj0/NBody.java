class NBody{
	public static double readRadius(String path) {
		In input = new In(path);
		int N = input.readInt(); // N is the number of planets 
		return input.readDouble();
	}

	public static Planet[] readPlanets(String path) {
		In input = new In(path);
		int N = input.readInt();
		Planet[] planets = new Planet[N];
		double R = input.readDouble();
		for(int i=0; i<N; i++) {
			double xxPos = input.readDouble();
			double yyPos = input.readDouble();
			double xxVel = input.readDouble();
			double yyVel = input.readDouble();
			double mass  = input.readDouble();
			String img   = input.readString();
			planets[i]   = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
		}
		return planets;
	}

	public static void main(String[] args) {
		double T  			=  Double.parseDouble(args[0]);
		double dt 			=  Double.parseDouble(args[1]);
		String filename 	= args[2];
		double Radius 		= NBody.readRadius(filename);
		Planet[] planets 	= NBody.readPlanets(filename);

		StdDraw.setScale(-Radius, Radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		for(Planet p: planets) {
			p.draw();
		}

		// Animation
		StdDraw.enableDoubleBuffering();
		for(double i=0; i<T; i+=dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int j=0; j<planets.length; j++) {
				xForces[j] = planets[j].calcNetForceExertedByX(planets);
				yForces[j] = planets[j].calcNetForceExertedByY(planets);
				planets[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for(Planet p: planets) {
				p.draw();
				
			}
			StdDraw.show(10);
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", Radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
				planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}