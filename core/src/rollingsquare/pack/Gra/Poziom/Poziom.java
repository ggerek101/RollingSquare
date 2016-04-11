package rollingsquare.pack.Gra.Poziom;

/**
 * Created by aaa on 2015-03-12.
 */
@SuppressWarnings("ALL")
public class Poziom
{
    Mapa mapa;

    public Poziom()
    {
        mapa = new Mapa();
    }
    public void wczytaj(String sciezkaDoPoziomu, String sciezkaDoTla)
    {
        mapa.wczytajKolejnaMape(sciezkaDoPoziomu);
        mapa.wczytajTlo(sciezkaDoTla);
        mapa.przeksztalcMape();
    }

    public Mapa getMapa()
    {
        return mapa;
    }
}
