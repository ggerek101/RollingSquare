package rollingsquare.pack.Gra.Fizyka;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by aaa on 2015-03-08.
 */

public class Swiat
{
    public static final float WTB = 0.01f;
    public static final float BTW = 100f;
    public static World world;
    public static CollisionDetection collisionDetection;

    public Swiat()
    {
        world = new World(new Vector2(0, 20f), true);
        collisionDetection = new CollisionDetection();
        world.setContactListener(collisionDetection);

    }

    public void aktualizuj()
    {
        world.step(1 / 60f, 8, 3);
    }
}
