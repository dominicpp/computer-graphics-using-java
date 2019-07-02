package engel865650.a10;

import java.io.IOException;

import cgtools.ImageTexture;
import cgtools.Vec3;

public class Texture implements Sampler {

	private ImageTexture texture = null;
	private Vec3 color = null;

	public Texture(String filename) {
		try {
			texture = new ImageTexture(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Vec3 color(double u, double v) {
		color = texture.color(u, v);
		/*
		 * undo gamma correction of the textures (imported images)
		 */
		double x = Math.pow(color.x, 2.2);
		double y = Math.pow(color.y, 2.2);
		double z = Math.pow(color.z, 2.2);
		return new Vec3(x, y, z);

	}

}
