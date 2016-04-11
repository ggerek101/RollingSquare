package rollingsquare.pack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import rollingsquare.pack.Gra.Fizyka.Swiat;

/**
 * Created by aaa on 2015-05-20.
 */
public class Intro
{
    Texture tex;
    Sprite sprite;
    boolean czyIscDalej;
    float alpha;

    public Intro(String sciezka)
    {
        tex = new Texture(Gdx.files.internal(sciezka));
        sprite = new Sprite(tex);
        sprite.flip(false,true);
        sprite.setSize(sprite.getWidth() * Swiat.WTB, sprite.getHeight() * Swiat.WTB);

        czyIscDalej = false;
        alpha = 1;
    }

    public void aktualizuj()
    {
        alpha -= 0.02;
        if(alpha <= 0)
        {
            czyIscDalej = true;
        }
        else
        {
            sprite.setAlpha(alpha);
        }
    }

    public void rysuj()
    {
        Grafika.batch.begin();
        sprite.draw(Grafika.batch);
        Grafika.batch.end();

    }
}
