package rollingsquare.pack.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Grafika;

/**
 * Created by aaa on 2015-03-20.
 */
public class Przycisk
{
    Texture tex;
    Sprite sprite;
    float x,y;

    public Przycisk(float x, float y, String sciezka)
    {
        this.x = x * Swiat.WTB;
        this.y = y * Swiat.WTB;
        tex = new Texture(Gdx.files.internal(sciezka));
        sprite = new Sprite(tex);
        sprite.flip(false,true);
        sprite.setSize(sprite.getWidth() * Swiat.WTB, sprite.getHeight() * Swiat.WTB);
    }

    public void rysuj()
    {
        sprite.setPosition(x,y);

        Grafika.batch.begin();
        sprite.draw(Grafika.batch);
        Grafika.batch.end();
    }

    public boolean czyDotknieto()
        {
            if(Gdx.input.justTouched()) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0); // pobieramy x i y dotkniecia
                Grafika.cam.unproject(touchPos);
                if(sprite.getX() <= touchPos.x && sprite.getX() + sprite.getWidth() >= touchPos.x
                        && sprite.getY() <= touchPos.y && sprite.getY() + sprite.getHeight() >= touchPos.y) // nasz algorytm, ktora wykrywa, czy dotknieto obiektu
                {
                    return true;
                }
            }
        return false;
    }
}
