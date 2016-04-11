package rollingsquare.pack.Gra.Poziom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Grafika;

/**
 * Created by aaa on 2015-03-12.
 */
@SuppressWarnings("ALL")
public class Tlo
{
    float x,y;
    Sprite sprite;
    Texture tex;

    public Tlo(int x, int y, String sciezka)
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
        Grafika.batch.begin();
        sprite.setPosition(x - x / 2,y - y / 2);
        sprite.draw(Grafika.batch);
        Grafika.batch.end();
    }
}
