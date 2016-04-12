package rollingsquare.pack.Gra.efekty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

import rollingsquare.pack.Gra.Fizyka.Swiat;

/**
 * Created by aaa on 2015-05-19.
 */
public class MalyObiekt
{
    Random rand;
    float x,y;
    Sprite sprite;
    Texture tex;
    float vx;
    float vy;
    float alpha;

    public MalyObiekt(float x, float y, Texture texture, int rxMin, int rxMax,int ryMin, int ryMax)
    {
        this.x = x;
        this.y = y;
        tex = texture;
        sprite = new Sprite(tex);
        sprite.setSize(20 * Swiat.WTB, 20 * Swiat.WTB);

        rand = new Random();

        vy = (rand.nextInt(ryMax-ryMin+1)+ryMin) * Swiat.WTB;
        vx = (rand.nextInt(rxMax+rxMin+1)-rxMin) * Swiat.WTB;

        alpha = 1;
    }
    
    public void aktualizujObiekt()
    {
        aktualizujPozycje();
        sprite.setPosition(x,y);
        aktualizujPrzezroczystosc();
    }

    public boolean sprawdzCzyUsunac()
    {
        if(alpha <= 0.1)
        {
            return true;
        }
        return false;
    }
    private void aktualizujPrzezroczystosc()
    {
        alpha -= 0.03;
        sprite.setAlpha(alpha);
    }
    private void aktualizujPozycje()
    {
        x += vx;
        y -= vy;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getY() {
        return y;
    }
}
