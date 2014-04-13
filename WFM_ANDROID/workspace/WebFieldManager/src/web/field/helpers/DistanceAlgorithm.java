package web.field.helpers;

public class DistanceAlgorithm {
	public static final double PIx = 3.141592653589793;
	public static final double RADIUS = 6378.16;

	// / <summary>
	// / This class cannot be instantiated.
	// / </summary>
	private DistanceAlgorithm() {
	}

	// / <summary>
	// / Convert degrees to Radians
	// / </summary>
	// / <param name="x">Degrees</param>
	// / <returns>The equivalent in radians</returns>
	public static double radians(double x) {
		return x * PIx / 180;
	}

	// / <summary>
	// / Calculate the distance between two places.
	// / </summary>
	// / <param name="lon1"></param>
	// / <param name="lat1"></param>
	// / <param name="lon2"></param>
	// / <param name="lat2"></param>
	// / <returns></returns>
	public static double distanceBetweenPlaces(double lon1, double lat1,
			double lon2, double lat2) {
		double dlon = radians(lon2 - lon1);
		double dlat = radians(lat2 - lat1);

		double a = (Math.sin(dlat / 2) * Math.sin(dlat / 2))
				+ Math.cos(radians(lat1)) * Math.cos(radians(lat2))
				* (Math.sin(dlon / 2) * Math.sin(dlon / 2));
		double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return angle * RADIUS;
	}
}
