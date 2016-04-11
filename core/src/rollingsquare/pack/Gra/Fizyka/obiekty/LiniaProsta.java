package rollingsquare.pack.Gra.Fizyka.obiekty;

/**
 * Created by aaa on 2015-03-09.
 */
public class LiniaProsta extends Linia
{
    public LiniaProsta(float x, float y, String sciezka)
    {
        super(x, y,sciezka);
        body.setUserData(this);
    }

    @Override
    public void uruchom()
    {

    }
}
