package rollingsquare.pack.Gra.Fizyka.obiekty;

import com.badlogic.gdx.Gdx;

/**
 * Created by aaa on 2015-03-11.
 */
@SuppressWarnings("ALL")
public class LiniaZnikajaca extends Linia
{
    float czas;
    int czasZnikania;
    int czasPojawiania;

    public LiniaZnikajaca(float x, float y, String sciezka, int czasPojawiania, int czasZnikania) {
        super(x, y,sciezka);
        czas = 0f;
        this.czasPojawiania = czasPojawiania;
        this.czasZnikania = czasZnikania;
    }

    private void zniknij()
    {
        sprite.setAlpha(0);
        body.setActive(false);
    }

    private void pokaz()
    {
        sprite.setAlpha(1);
        body.setActive(true);
    }

    public void uruchom()
    {

        czas += Gdx.graphics.getDeltaTime();
        if(czas <= czasPojawiania)
        {
            pokaz();
        }
        else
        {
            zniknij();
            if(czas > czasZnikania + czasPojawiania)
            {
                czas = 0;
            }
        }
    }
}
