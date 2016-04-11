package rollingsquare.pack.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Scanner;

import rollingsquare.pack.Gra.Poziom.Tlo;
import rollingsquare.pack.RollingSquare;

/**
 * Created by aaa on 2015-03-18.
 */
public class Menu {
    private int numerWybranegoPoziomu;
    private Tlo tlo;
    private Przycisk start;
    public boolean zacznijGre = false;
    Scanner skaner;
    int zawartoscPliku;
    Symulacja symulacja;

    public Menu() {
        symulacja = new Symulacja();
        numerWybranegoPoziomu = 0;
        wczytajPlik("numery.txt");
        tlo = new Tlo(0, 0, "tlo" + String.valueOf((zawartoscPliku) / 10) + ".jpg");
        start = new Przycisk(857, 550, "button.png");
    }

    private void wczytajPlik(String sciezka) {
        FileHandle file = Gdx.files.internal(sciezka);
        String text = file.readString();
        skaner = new Scanner(text);
        int i = 1;
        while ((skaner.hasNextLine())) {
            zawartoscPliku += skaner.nextInt() * i;
            i *= 10;
        }
        if(zawartoscPliku == 0)
        {
            zawartoscPliku = 1;
        }
    }

    public int getNumerPoziomu() {
        return numerWybranegoPoziomu;
    }


    public void rysujObiekty() {
        tlo.rysuj();
        start.rysuj();
    }

    private void sprawdzKliknieciePrzyciskow() {
        if (start.czyDotknieto()) {
            symulacja.usunObiekty();
            zacznijGre = true;
        }
    }


    public void aktualizuj() {
        sprawdzKliknieciePrzyciskow();
        sprawdzNumerPoziomu();
        rysujObiekty();
        symulacja.zacznij();
        RollingSquare.fizyka.aktualizuj();
    }

    private void sprawdzNumerPoziomu() {
        numerWybranegoPoziomu = zawartoscPliku;
    }
}
