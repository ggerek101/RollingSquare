package rollingsquare.pack.Gra;

import com.badlogic.gdx.Gdx;

import java.util.Vector;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Gra.Fizyka.obiekty.Bohater;
import rollingsquare.pack.Gra.Fizyka.obiekty.CeleMapy.Gwiazdka;
import rollingsquare.pack.Gra.Fizyka.obiekty.Kolce;
import rollingsquare.pack.Gra.Fizyka.obiekty.Linia;
import rollingsquare.pack.Gra.Fizyka.obiekty.LiniaObracana;
import rollingsquare.pack.Gra.Fizyka.obiekty.LiniaPoruszana;
import rollingsquare.pack.Gra.Fizyka.obiekty.LiniaProsta;
import rollingsquare.pack.Gra.Fizyka.obiekty.LiniaZnikajaca;
import rollingsquare.pack.Gra.Fizyka.obiekty.Obiekt;
import rollingsquare.pack.Gra.Fizyka.obiekty.Zmiana;
import rollingsquare.pack.Gra.Poziom.Mapa;

/**
 * Created by aaa on 2015-03-09.
 */
@SuppressWarnings("ALL")
public class ManagerObiektow {
    Mapa mapa;
    Bohater bohater;
    Vector<Linia> linie;
    Vector<Gwiazdka> gwiazdki;

    public ManagerObiektow(Mapa mapa) {
        this.mapa = mapa;
        linie = new Vector();
        gwiazdki = new Vector();
        ustawObiekty();
    }

    public void ustawObiekty() {
        bohater = new Bohater(mapa.RESPAWN.x, mapa.RESPAWN.y, "kwCzerwony.png");
        for (int i = 0; i != mapa.getObiekty().size(); i++) {
            switch (mapa.getObiekty().get(i).nazwa) {
                case "L": {
                    linie.add(new LiniaProsta(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y, "linia.png"));
                    break;
                }
                case "LP": {
                    linie.add(new LiniaPoruszana(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y, "linia.png", mapa.getObiekty().get(i).s, mapa.getObiekty().get(i).V, mapa.getObiekty().get(i).kierunek));
                    break;
                }
                case "LZ": {
                    linie.add(new LiniaZnikajaca(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y, "liniaZnikajaca.png", mapa.getObiekty().get(i).czasPojawiania, mapa.getObiekty().get(i).czasZnikania));
                    break;
                }
                case "LO": {
                    linie.add(new LiniaObracana(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y,mapa.getObiekty().get(i).V, "linia.png"));
                    break;
                }
                case "K": {
                    linie.add(new Kolce(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y, "kolce.png", mapa.getObiekty().get(i).s,  mapa.getObiekty().get(i).V));
                    break;
                }
                case "Z": {
                    linie.add(new Zmiana(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y, "zmiana.png"));
                    break;
                }
                case "G": {
                    String sciezka = null;
                    if (mapa.getObiekty().get(i).kolor == 'R') {
                        sciezka = "gwiazdkaCzerwona.png";
                    } else if (mapa.getObiekty().get(i).kolor == 'B') {
                        sciezka = "gwiazdkaNiebieska.png";
                    }
                    gwiazdki.add(new Gwiazdka(mapa.getObiekty().get(i).x, mapa.getObiekty().get(i).y, sciezka, mapa.getObiekty().get(i).s, mapa.getObiekty().get(i).V));
                    break;
                }
            }
        }
    }

    private void rysujObiekt(Obiekt obiekt)
    {
        obiekt.rysuj();
    }
    private void uruchomGwiazdke(Gwiazdka gwiazdka)
    {
        gwiazdka.aktualizujWydarzenia();
    }
    private void uruchomLinie(Linia linia)
    {
        linia.uruchom();
        linia.aktualizujWydarzenia();
    }
    private void uruchomBohatera()
    {
        bohater.aktualizujWydarzenia();
    }

    private void zniszczObiekty()
    {
        bohater.getBody().getWorld().destroyBody(bohater.getBody());
        for(int i = 0; i != linie.size(); i++) {
            linie.get(i).getBody().getWorld().destroyBody(linie.get(i).getBody());
            linie.remove(i);
            i--;
        }
        for(int i = 0; i != gwiazdki.size(); i++)
        {
            gwiazdki.get(i).getBody().getWorld().destroyBody(gwiazdki.get(i).getBody());
            gwiazdki.remove(i);
            i--;
        }
    }

    public void usunObiekty()
    {
        bohater.resetuj();
        zniszczObiekty();
    }

    private void sprawdzCzyZresetowacMape()
    {
        if(bohater.stan.Usuniety == bohater.stan)
        {
            usunObiekty();
            ustawObiekty();
        }
    }

    private boolean wygralMape()
    {
        if(bohater.zebraneGwiazdki == mapa.iloscGwiazdek)
        {
            return true;
        }
        return false;
    }

    private boolean czyUsunac(Obiekt o)
    {
        if(o.stan == Obiekt.Stan.Usuniety)
        {
            return true;
        }
        return false;
    }

    public void aktualizuj()
    {
        sprawdzCzyZresetowacMape();

        uruchomBohatera();
        bohater.rysuj();

        for(int i = 0; i != linie.size(); i++)
        {
            uruchomLinie(linie.get(i));
            rysujObiekt(linie.get(i));
            if(czyUsunac(linie.get(i)))
            {
                linie.get(i).getBody().getWorld().destroyBody(linie.get(i).getBody());
                linie.remove(i);
                i--;
            }
        }

        for (int i = 0; i != gwiazdki.size(); i++)
        {
            uruchomGwiazdke(gwiazdki.get(i));
            rysujObiekt(gwiazdki.get(i));
            if (czyUsunac(gwiazdki.get(i)))
            {
                gwiazdki.get(i).getBody().getWorld().destroyBody(gwiazdki.get(i).getBody());
                gwiazdki.remove(i);
                i--;
            }
        }
    }

}
