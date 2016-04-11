package rollingsquare.pack.Gra.Fizyka.obiekty.CeleMapy;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Gra.Fizyka.obiekty.Obiekt;

/**
 * Created by aaa on 2015-03-29.
 */
public class Gwiazdka extends Obiekt
{
    float starty;
    float hPodnoszenia;
    float V;
    Kolor kolor;

    public Gwiazdka(float x, float y, String sciezka, float hPodnoszenia, float V) {
        super(x, y, sciezka);

        if(sciezka == "gwiazdkaCzerwona.png") {
            this.kolor = Kolor.Czerwony;

        }
        else if(sciezka == "gwiazdkaNiebieska.png") {
            this.kolor = Kolor.Niebieski;
        }

        bodyDef.type = BodyDef.BodyType.KinematicBody;

        body = Swiat.world.createBody(bodyDef);

        pShape = new PolygonShape();
        pShape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = pShape;

        body.createFixture(fixtureDef);
        body.setUserData(this);


        pShape.dispose();

        this.starty = y * Swiat.WTB + sprite.getHeight() / 2 - 1 * Swiat.WTB;
        this.V = V;
        this.hPodnoszenia = hPodnoszenia * Swiat.WTB;
    }

    private void obroc()
    {
        body.setTransform(body.getWorldCenter(),body.getTransform().getRotation() + 0.01f);
        sprite.setRotation((float) (body.getTransform().getRotation()   * (180/Math.PI)));
        sprite.setOriginCenter();
    }

    private void poruszaj()
    {
        if (body.getPosition().y < (starty - hPodnoszenia)) {
            body.setLinearVelocity(0.f, V);
        }
        if (body.getPosition().y > starty) {
            body.setLinearVelocity(0.f, -V);
        }
    }

    public void aktualizujWydarzenia()
    {
        poruszaj();
        sprawdzCzyZniszczyc();
        sprawdzCzyDezaktywowacCialo();
    }

    public Kolor getKolor() {
        return kolor;
    }
}
