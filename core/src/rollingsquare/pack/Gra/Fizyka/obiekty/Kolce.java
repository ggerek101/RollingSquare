package rollingsquare.pack.Gra.Fizyka.obiekty;

import com.badlogic.gdx.physics.box2d.Filter;

import rollingsquare.pack.Gra.Fizyka.Swiat;

/**
 * Created by aaa on 2015-05-21.
 */
public class Kolce extends Linia {
    Filter filter;
    float s,V;
    float startY;
    public Kolce(float x, float y, String sciezka, float s, float V) {
        super(x, y, sciezka);

      //  filter = new Filter();
      //  filter.categoryBits  = 0x0001;
      //  filter.maskBits = 0x0002;
     //   body.getFixtureList().get(0).setFilterData(filter);
        body.setUserData(this);

        this.s = s * Swiat.WTB;
        this.V = V;
        this.startY = getBody().getPosition().y;
    }

    @Override
    public void uruchom()
    {
        if(V > 0)
        {
            if (getBody().getPosition().y < startY + s) {
                body.setLinearVelocity(0.f, V);
            }
            else
            {
                body.setLinearVelocity(0.f, 0.f);
                getBody().setTransform(body.getPosition().x,startY,body.getAngle());
            }
        }
        if((V < 0)) {
            if (getBody().getPosition().y > startY - s) {
                body.setLinearVelocity(0.f, V);
            }
            else
            {
                body.setLinearVelocity(0.f, 0.f);
                getBody().setTransform(body.getPosition().x, startY, body.getAngle());
            }
        }
    }
}
