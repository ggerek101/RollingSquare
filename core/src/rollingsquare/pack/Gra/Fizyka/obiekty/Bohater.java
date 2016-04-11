package rollingsquare.pack.Gra.Fizyka.obiekty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Gra.Poziom.Mapa;
import rollingsquare.pack.Grafika;

/**
 * Created by aaa on 2015-03-08.
 */
public class Bohater extends Obiekt
{
    private float V;
    public boolean zebralGwiazdke;
    public boolean moznaSkakac;
    public int zebraneGwiazdki;
    public float czasOdOstatniegoUzyciaKoloru;
    Kolor kolor;

    public Kolor getKolor() {
        return kolor;
    }
    private void ustawKolor(Kolor kolor)
    {
        this.kolor = kolor;
        String sciezka;

        if (kolor == Kolor.Czerwony)
        {
            sciezka = "kwCzerwony.png";
        }
        else
        {
            sciezka = "kwNiebieski.png";
        }

        Texture texture = new Texture(Gdx.files.internal(sciezka));
        sprite.setTexture(texture);

    }

    public Bohater(float x, float y, String sciezka) {
        super(x, y,sciezka);

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = Swiat.world.createBody(bodyDef);

        pShape = new PolygonShape();
        pShape.setAsBox(sprite.getWidth() / 2 , sprite.getHeight() / 2 );

        fixtureDef = new FixtureDef();
        fixtureDef.shape = pShape;

        body.createFixture(fixtureDef).setUserData("heroBody");

        pShape.setAsBox(sprite.getWidth() / 2 - 1.0f * Swiat.WTB, sprite.getHeight());
        fixtureDef.isSensor = true;
        fixtureDef.shape = pShape;

        body.createFixture(fixtureDef).setUserData("heroFoot");
        body.setUserData(this);

        V = 1.75f;
        zebralGwiazdke = false;
        moznaSkakac = false;
        zebraneGwiazdki = 0;
        kolor = Kolor.Czerwony;
        czasOdOstatniegoUzyciaKoloru = 0;
    }

    public void resetuj()
    {
       umarl = false;
       body.setTransform(Mapa.RESPAWN.x, Mapa.RESPAWN.y, 0);
       V = 0;
       zebraneGwiazdki = 0;
    }

    public void sterowanie()
    {
        skok();
        idz();
    }

   /* private void obroc()
    {
        body.setTransform(body.getWorldCenter(),body.getTransform().getRotation() + 0.1f);
        sprite.setRotation((float) (body.getTransform().getRotation()   * (180/Math.PI)));
        sprite.setOriginCenter();
    }
    */

    private void skok()
    {
        if(moznaSkakac)
        {
            body.setLinearVelocity(body.getLinearVelocity().x,-V * 3.4f);
            moznaSkakac = false;
        }
    }

    private void idz()
    {
        if(Gdx.input.isTouched(0))
        {
                if (Gdx.input.getX(0) > Gdx.graphics.getWidth() / 2) {
                    body.setLinearVelocity(V, body.getLinearVelocity().y);
                } else {
                    body.setLinearVelocity(-V, body.getLinearVelocity().y);
            }
        }
        else
        {
            body.setLinearVelocity(0f,body.getLinearVelocity().y);
        }
           /* Grafika.cam.position.x += body.getLinearVelocity().x * 1 / 60;
            Grafika.cam.position.x = body.getPosition().x + Grafika.cam.viewportWidth / 3;
            */
    }

    public void aktualizujWydarzenia()
    {
        sterowanie();
        sprawdzCzyDodacGwiazdke();
        sprawdzCzyZmienicKolorBohatera();
        sprawdzCzyBohaterSpadl();
        sprawdzCzyZniszczyc();
        sprawdzCzyDezaktywowacCialo();
    }

    private void sprawdzCzyDodacGwiazdke()
    {
        if(zebralGwiazdke)
        {
            zebraneGwiazdki++;
            zebralGwiazdke = false;
        }
    }
    private void sprawdzCzyBohaterSpadl()
    {
        if (body.getPosition().y >= Grafika.cam.viewportHeight) {
            stan = Stan.Usuniety;
        }
    }

    private void sprawdzCzyZmienicKolorBohatera()
    {
        czasOdOstatniegoUzyciaKoloru += Gdx.graphics.getDeltaTime();
        if(zmienKolor && czasOdOstatniegoUzyciaKoloru >= 0.3f)
        {
            if (kolor == Kolor.Czerwony)
            {
                kolor = Kolor.Niebieski;
                ustawKolor(Kolor.Niebieski);
            }
            else
            {
                kolor = Kolor.Czerwony;
                ustawKolor(Kolor.Czerwony);
            }
            zmienKolor = false;
            czasOdOstatniegoUzyciaKoloru = 0;
        }
    }

}
