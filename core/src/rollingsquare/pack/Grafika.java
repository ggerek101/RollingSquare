package rollingsquare.pack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by aaa on 2015-03-08.
 */
public class Grafika
{
    static public SpriteBatch batch;
    static public OrthographicCamera cam;

    public static void init()
    {
        batch = new SpriteBatch();
        cam = new OrthographicCamera(1920,1200);
        cam.setToOrtho(true,19.2f,12.0f);
        batch.setProjectionMatrix(cam.combined);
    }

    public static void odswiez () {
        Gdx.gl.glClearColor(245, 244, 251, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }
}
