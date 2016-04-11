package rollingsquare.pack.Menu;

import com.badlogic.gdx.Gdx;

import java.util.Random;
import java.util.Vector;

import rollingsquare.pack.Gra.Fizyka.Swiat;
import rollingsquare.pack.Gra.Fizyka.obiekty.CeleMapy.Gwiazdka;
import rollingsquare.pack.Gra.Fizyka.obiekty.Kolce;
import rollingsquare.pack.Gra.Fizyka.obiekty.Linia;
import rollingsquare.pack.Gra.Fizyka.obiekty.LiniaProsta;
import rollingsquare.pack.Gra.Fizyka.obiekty.Obiekt;
import rollingsquare.pack.Grafika;

/**
 * Created by aaa on 2015-08-07.
 */
public class Symulacja
{
    Vector<Linia> linie;
    Vector<Gwiazdka> gwiazdki;
    Vector<Kolce> podloga;
    float objectWidth, objectHeight;
    float V;
    float czasPojawania;
    float czasOdOstatniego;
    Random rand;

    public Symulacja()
    {
        linie = new Vector();
        gwiazdki = new Vector();
        podloga = new Vector();
        objectWidth = 52;
        objectHeight = 52;
        this.V = 5;
        this.czasPojawania = 2f;
        this.czasOdOstatniego = 0;
        rand = new Random();
        stworzPodloge();
    }

    private void stworzPodloge()
    {
        for(int i = 0; i != 37; i++)
        {
            podloga.add(new Kolce(objectWidth * i, Grafika.cam.viewportHeight * Swiat.BTW - objectHeight, "kolce.png",0,0));
        }
    }

    public void zacznij()
    {
        czasOdOstatniego += Gdx.graphics.getDeltaTime();
        if(czasOdOstatniego >= czasPojawania)
        {
            float x = rand.nextInt((int)(Grafika.cam.viewportWidth * Swiat.BTW / objectWidth));
            x *= objectWidth;
            float y = 0;
            String sciezka1;
            switch(rand.nextInt(2))
            {
                case 0:
                    switch (rand.nextInt(3))
                    {
                        case 0:
                            sciezka1 = "linia.png";
                            break;
                        case 1:
                            sciezka1 = "kwCzerwony.png";
                            break;
                        default:
                            sciezka1 = "kwNiebieski.png";
                            break;
                    }
                    linie.add(new LiniaProsta(x, y, sciezka1));
                    break;
                case 1:
                    String sciezka2;
                    if(rand.nextInt(2) == 0)
                    {
                        sciezka2 = "gwiazdkaCzerwona.png";
                    }
                    else
                    {
                        sciezka2 = "gwiazdkaNiebieska.png";
                    }
                    gwiazdki.add(new Gwiazdka(x, y, sciezka2, 0, 0));
                    break;
            }
            czasOdOstatniego = 0;
        }
        zarzadzaj();
    }

    private void zarzadzaj()
    {
        if(linie.size() + gwiazdki.size() > 5)
        {
            usunObiekty();
            stworzPodloge();
        }
        for(int i = 0; i != linie.size(); i++)
        {
            linie.get(i).getBody().setLinearVelocity(0, V);
            for(int j = 0; j != podloga.size(); j++) {
                if (linie.get(i).czyKoliduje(podloga.get(j)))
                {
                    linie.get(i).stan = Obiekt.Stan.Zniszczony;
                }
            }
            linie.get(i).rysuj();
            if(sprawdzCzyUsunac(linie.get(i))) {
                linie.remove(i);
                i--;
            }
        }

        for(int i = 0; i != gwiazdki.size(); i++)
        {
            gwiazdki.get(i).getBody().setLinearVelocity(0, V);
            for(int j = 0; j != podloga.size(); j++) {
                if (gwiazdki.get(i).czyKoliduje(podloga.get(j)))
                {
                    gwiazdki.get(i).stan = Obiekt.Stan.Zniszczony;
                }
            }
            gwiazdki.get(i).rysuj();
            if(sprawdzCzyUsunac(gwiazdki.get(i))) {
                gwiazdki.remove(i);
                i--;
            }
        }

        for(int i = 0; i != podloga.size(); i++) {
            podloga.get(i).rysuj();
        }

    }

    public boolean sprawdzCzyUsunac(Obiekt obiekt)
    {
        if(obiekt.stan == Obiekt.Stan.Zniszczony && obiekt.vectorKawalkiJestPusty)
        {
            obiekt.getBody().getWorld().destroyBody(obiekt.getBody());
            return true;
        }
        return false;
    }

    public void usunObiekty()
    {
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

        for(int i = 0; i != podloga.size(); i++)
        {
            podloga.get(i).getBody().getWorld().destroyBody(podloga.get(i).getBody());
            podloga.remove(i);
            i--;
        }
    }
}
