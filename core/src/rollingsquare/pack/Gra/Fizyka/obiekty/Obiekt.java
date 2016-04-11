package rollingsquare.pack.Gra.Fizyka.obiekty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;
import java.util.Vector;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Gra.efekty.MalyObiekt;
import rollingsquare.pack.Grafika;

/**
 * Created by aaa on 2015-03-08.
 */
@SuppressWarnings("ALL")
public abstract class Obiekt extends Object
{
    protected Random rand;

    public enum Kolor{Czerwony,Niebieski}
    public boolean zmienKolor;
    public enum Stan{Normalny,Zniszczony,Usuniety}
    public Stan stan;

    protected BodyDef bodyDef;
    protected Body body;
    protected FixtureDef fixtureDef;
    protected PolygonShape pShape;
    protected CircleShape cShape;
    protected Sprite sprite;
    protected Texture tex;

    public Vector<MalyObiekt> kawalki;

    protected int iloscKawalkow;
    protected int iloscStworzonychKawalkow;
    protected int vZniszczenia;

    public boolean vectorKawalkiJestPusty;
    public boolean umarl;

    public Obiekt(float x, float y, String sciezka)
    {
        tex = new Texture(Gdx.files.internal(sciezka));
        sprite = new Sprite(tex);
        sprite.flip(false, true);
        sprite.setSize(sprite.getWidth() * Swiat.WTB, sprite.getHeight() * Swiat.WTB);

        x = zmienCentrumX(x * Swiat.WTB, sprite.getWidth());
        y = zmienCentrumY(y * Swiat.WTB,sprite.getHeight());

        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);

        zmienKolor = false;

        stan = Stan.Normalny;

        rand = new Random();
        kawalki = new Vector();

        vectorKawalkiJestPusty = false;

        iloscKawalkow = 3;
        vZniszczenia =  3;
        iloscStworzonychKawalkow = 0;

        umarl = false;
    }

   /* public void ustawWielkoscWybuchu(int il, int v)
    {
        iloscKawalkow = il;
        vZniszczenia = v;
    }
    */

    public float zmienCentrumX(float x, float width)
    {
        return  x + width / 2;
    }
    public float zmienCentrumY(float y, float height)
    {
        return  y + height / 2;
    }


    public Sprite getSprite()
    {
        return  sprite;
    }
    public Body getBody() {
        return body;
    }

    private boolean czyRysowacZniszczenie()
    {
        if(stan == Stan.Zniszczony)
        {
            return true;
        }
        return false;
    }
    private boolean czyVektorJestPusty()
    {
        if(kawalki.isEmpty())
        {
            return true;
        }
        return false;
    }

    private void dodajKawalek()
    {
        kawalki.add(new MalyObiekt(this.getBody().getPosition().x, this.getBody().getPosition().y, sprite.getTexture(), 1, vZniszczenia * 2, 1, vZniszczenia * 2));
        iloscStworzonychKawalkow++;
    }

    private int rysujKawalki(int i)
    {
        kawalki.get(i).aktualizujObiekt();
        kawalki.get(i).getSprite().draw(Grafika.batch);
        if(kawalki.get(i).sprawdzCzyUsunac())
        {
            kawalki.remove(i);
            i--;
            if(kawalki.size() == 1)
            {
                stan = Stan.Usuniety;
            }
        }
        return i;
    }

    public void rysuj()
    {
        Grafika.batch.begin();
        if(czyRysowacZniszczenie())
        {
            if(czyVektorJestPusty())
            {
                for(int i = 0; i != iloscKawalkow; i++)
                {
                    dodajKawalek();
                }
            }
            else
            {
                for(int i = 0; i != kawalki.size(); i++)
                {
                    i = rysujKawalki(i);
                }
            }
        }
        else if(umarl == false)
        {
            if(body != null)
            {
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
                sprite.draw(Grafika.batch);
            }
        }
        Grafika.batch.end();
    }

    public boolean czyKoliduje(Obiekt o)
    {
        Vector2 rog_obiektu1 =  new Vector2();
        Vector2 rog_obiektu2 =  new Vector2();

        rog_obiektu1.x = body.getPosition().x - sprite.getWidth() / 2;
        rog_obiektu1.y = body.getPosition().y - sprite.getHeight() / 2;

        rog_obiektu2.x = o.body.getPosition().x - o.getSprite().getWidth() / 2;
        rog_obiektu2.y = o.body.getPosition().y - o.getSprite().getHeight() / 2;

        if(rog_obiektu1.x > rog_obiektu2.x+o.getSprite().getWidth() ||
                rog_obiektu1.x+ getSprite().getWidth() < rog_obiektu2.x ||
                rog_obiektu1.y >  rog_obiektu2.y + o.getSprite().getHeight() ||
                rog_obiektu1.y+ getSprite().getHeight() <  rog_obiektu2.y)
        {
            return false;
        }
        return true;
    }

    public void aktualizujWydarzenia()
    {
        sprawdzCzyZniszczyc();
        sprawdzCzyDezaktywowacCialo();
    }

    protected void sprawdzCzyZniszczyc()
    {
        if(umarl)
        {
            stan = Stan.Zniszczony;
        }
    }

    protected void sprawdzCzyDezaktywowacCialo()
    {
        if(stan == Stan.Zniszczony)
        {
            body.setActive(false);
        }
    }

}
