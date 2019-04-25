
package engel865650.a02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cgtools.Random;
import cgtools.Vec3;

public class ColoredDiamond {
	private static List<Diamond> diamonds = null;
	private Diamond diamond = null;

	private double sampling = 10;

	public ColoredDiamond() {

	}

	public ColoredDiamond(int numberOfSlices) {
		diamond = new Diamond();
		diamonds = new ArrayList<Diamond>();
		for (int i = 0; i < numberOfSlices; i++) {
			diamonds.add(diamond.getRandomAttributes());
		}
		/*
		 * Source for Java Collections - Comparator:
		 * https://www.logicbig.com/how-to/code-snippets/jcode-java-collections-
		 * comparator-comparingdouble.html
		 */
		diamonds.sort(Comparator.comparingDouble(Diamond::getSize_E));
	}

	public Vec3 pixelColor(double x, double y) {
		for (Diamond diamond : diamonds) {
			/*
			 * Source for mathematical formula: https://www.wolframalpha.com/
			 * http://www.mathematische-basteleien.de/raute.htm
			 * https://www.matheretter.de/rechner/raute
			 */
			if (Math.abs(y - diamond.getY())
					/ Math.abs(x - diamond.getX()) <= diamond.getSize_F() / Math.abs(x - diamond.getX())
							- diamond.getSize_F() / diamond.getSize_E()) {
				return diamond.color;
			}
		}
		// white background if too less slices
		return new Vec3(0, 1, 1);
	}

	public Vec3 stratified_Sampling(double x, double y) {
		// isResult, as Vec3 data type, gets the result of pixelColor
		Vec3 isResult = pixelColor(x, y);
		// for xi 10 times
		for (int xi = 0; xi < sampling; xi++) {
			// and for yi 10 times
			for (int yi = 0; yi < sampling; yi++) {
				double rx = Random.random();
				double ry = Random.random();
				double xs = x + (xi + rx) / sampling;
				double ys = y + (yi + ry) / sampling;
				// Adds the given vectors and returns a newly allocated vector.
				isResult = Vec3.add(isResult, pixelColor(xs, ys));
			}
		}
		// determine average color
		// divide my new vector by the sampler * 10
		// each pixel scan 100 times and forms the average value
		// sampling * sampling for xi and yi like xi * yi -> so we have 100
		return Vec3.divide(isResult, sampling * sampling);
	}
}