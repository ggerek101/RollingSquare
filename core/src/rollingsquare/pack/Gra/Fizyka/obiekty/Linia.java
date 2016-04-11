package rollingsquare.pack.Gra.Fizyka.obiekty;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.Random;

import rollingsquare.pack.Gra.Fizyka.Swiat;

/**
 * Created by aaa on 2015-03-08.
 */
public abstract class Linia extends Obiekt
{
    Linia(float x, float y, String sciezka) {
        super(x, y,sciezka);

        bodyDef.type = BodyDef.BodyType.KinematicBody;

        body = Swiat.world.createBody(bodyDef);

        pShape = new PolygonShape();
        pShape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = pShape;

        fixtureDef.friction = 0.0f;

        body.createFixture(fixtureDef);
        body.setUserData(this);


        pShape.dispose();


    }
    public abstract void uruchom();
}
