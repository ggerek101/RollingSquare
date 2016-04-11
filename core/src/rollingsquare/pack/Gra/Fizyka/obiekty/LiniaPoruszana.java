package rollingsquare.pack.Gra.Fizyka.obiekty;

import rollingsquare.pack.Gra.Fizyka.Swiat;

/**
 * Created by aaa on 2015-03-10.
 */
public class LiniaPoruszana extends Linia
{
    float start;
    float s;
    float V;
    float kierunek; // 0 gora-dol 1 lewo-prawo
    public LiniaPoruszana(float x, float y, String sciezka,float s,float V, float kierunek) {
        super(x, y,sciezka);

        this.V = V;
        this.s = s * Swiat.WTB;
        this.kierunek = kierunek;
        if(this.kierunek == 0)
        {
            this.start = y * Swiat.WTB;
        }
        else
        {
            this.start = x * Swiat.WTB;
        }

    }

    @Override
    public void uruchom()
    {
        if(kierunek == 0) {
            if (body.getPosition().y < (start - s)) {
                body.setLinearVelocity(0.f, V);
            }
            if (body.getPosition().y > start) {
                body.setLinearVelocity(0.f, -V);
            }
        }
        else
        {
            if (body.getPosition().x < (start - s)) {
                body.setLinearVelocity(V, 0f);
            }
            if (body.getPosition().x > start) {
                body.setLinearVelocity(-V, 0f);
            }
        }
        }
    }
