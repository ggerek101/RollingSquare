package rollingsquare.pack.Gra;

import com.badlogic.gdx.Gdx;

import java.io.FileWriter;
import java.io.IOException;

import rollingsquare.pack.Gra.Poziom.Poziom;
import rollingsquare.pack.Gra.Poziom.Tlo;
import rollingsquare.pack.RollingSquare;

/**
 * Created by aaa on 2015-03-12.
 */
public class Gra
{
    ManagerObiektow managerObiektow;
    Tlo tlo;
    Poziom poziom;
    int numerPoziomu;
    float czasOdWygraniaPoziomu;

    public Gra(int numerPoziomu)
    {
        this.numerPoziomu = numerPoziomu;
        poziom = new Poziom();
        String sciezkaDoTla =  "tlo" + String.valueOf((numerPoziomu) / 10) + ".jpg";
        poziom.wczytaj(("poziom" + String.valueOf(numerPoziomu) + ".txt"), (sciezkaDoTla));  //+ String.valueOf(numerPoziomu) + ".png"));
        tlo = poziom.getMapa().getTlo();
        managerObiektow = new ManagerObiektow(poziom.getMapa());
        czasOdWygraniaPoziomu = 0;
    }

    private void nadpiszPlikZPoziomem(){
        try {
            FileWriter zapis = new FileWriter("numery.txt",false);
            zapis.write(toString().valueOf(numerPoziomu));
            zapis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nastepnyPoziom()
    {
        numerPoziomu++;
        nadpiszPlikZPoziomem();
        managerObiektow.usunObiekty();
        String sciezkaDoTla =  "tlo" + String.valueOf((numerPoziomu) / 10) + ".jpg";
        poziom.wczytaj(("poziom" + String.valueOf(numerPoziomu) + ".txt"), (sciezkaDoTla));  //+ String.valueOf(numerPoziomu) + ".png"));
        tlo = poziom.getMapa().getTlo();
        managerObiektow.ustawObiekty();
    }

    public boolean bohaterZebralGwiazdki()
    {
        if(managerObiektow.bohater.zebraneGwiazdki == managerObiektow.mapa.iloscGwiazdek)
        {
            return true;
        }
        return false;
    }

    private void resetujObiekty()
    {
        czasOdWygraniaPoziomu = 0;
    }

    private void sprawdzCzyBohaterWygralPoziom()
    {
        if(bohaterZebralGwiazdki())
        {
            czasOdWygraniaPoziomu += Gdx.graphics.getDeltaTime();
            if(czasOdWygraniaPoziomu > 0.5)
            {
                nastepnyPoziom();
                resetujObiekty();
            }
        }
    }

    public void rozgrywka()
    {
        tlo.rysuj();
        managerObiektow.aktualizuj();
        RollingSquare.fizyka.aktualizuj();
        sprawdzCzyBohaterWygralPoziom();
    }
}
