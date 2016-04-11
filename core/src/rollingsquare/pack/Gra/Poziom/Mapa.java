package rollingsquare.pack.Gra.Poziom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import java.util.Scanner;
import java.util.Vector;


/**
 * Created by aaa on 2015-03-09.
 */


@SuppressWarnings("ALL")
public class Mapa
{
    public static Vector2 RESPAWN;
    public  int iloscGwiazdek;
    private int przesuniecie;
    Tlo tlo;
    public Scanner skaner;
    Vector<String>mapaTxt;
    Vector<Struct>obiekty;

    public class Struct
    {
        public int x;
        public int y;
        public String nazwa;
        public int czasPojawiania;
        public int czasZnikania;
        public int V;
        public int kierunek;
        public int s;
        public char kolor;
    }

    public Mapa()
    {
        mapaTxt = new Vector();
        obiekty = new Vector();
        przesuniecie = 0;
        iloscGwiazdek = 0;
    }

    private void usunDanePoprzedniego()
    {
        iloscGwiazdek = 0;
        mapaTxt.removeAllElements();
        obiekty.removeAllElements();
    }

    public void wczytajKolejnaMape(String sciezka)
    {
        usunDanePoprzedniego();
        wczytajMape(sciezka);
    }

    private void wczytajMape(String sciezka)
    {
        FileHandle file = Gdx.files.internal(sciezka);
        String text = file.readString();
            skaner = new Scanner(text);
        while((skaner.hasNextLine())) {
            mapaTxt.add(skaner.nextLine());
        }
    }

    private void pobierzWlasciwosci(int i)
    {
        if(mapaTxt.get(i).startsWith("L."))
        {
            obiekty.lastElement().nazwa = "L";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaLini(i);
        }
        else if(mapaTxt.get(i).startsWith("LP."))
        {
            obiekty.lastElement().nazwa = "LP";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaLiniPodnoszonej(i);
        }
        else if(mapaTxt.get(i).startsWith("LZ."))
        {
            obiekty.lastElement().nazwa = "LZ";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaLiniZnikajacej(i);
        }
        else if(mapaTxt.get(i).startsWith("LO."))
        {
            obiekty.lastElement().nazwa = "LO";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaliniObracanej(i);
        }
        else if(mapaTxt.get(i).startsWith("K."))
        {
            obiekty.lastElement().nazwa = "K";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaKolcow(i);
        }
        else if(mapaTxt.get(i).startsWith("R."))
        {
            obiekty.lastElement().nazwa = "R";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaRespawn(i);
        }
        else if(mapaTxt.get(i).startsWith("G."))
        {
            obiekty.lastElement().nazwa = "G";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaGwiazdki(i);
            iloscGwiazdek++;
        }
        else if(mapaTxt.get(i).startsWith("Z."))
        {
            obiekty.lastElement().nazwa = "Z";
            przesuniecie += obiekty.lastElement().nazwa.length() + 1;
            pobierzDlaZmiany(i);
        }
    }





    private int pobierzX(int i)
    {
        String zawartoscX = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscX += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().x = Integer.parseInt(zawartoscX);
        przesuniecie += zawartoscX.length() + 1;
        return obiekty.lastElement().x;

    }
    private int pobierzY(int i)
    {
        String zawartoscY = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscY += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().y = Integer.parseInt(zawartoscY);
        przesuniecie += zawartoscY.length() + 1;
        return obiekty.lastElement().y;
    }
    private int pobierzCzasPojawiania(int i)
    {
        String zawartoscCzasuPojawiania = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscCzasuPojawiania += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().czasPojawiania = Integer.parseInt(zawartoscCzasuPojawiania);
        przesuniecie += zawartoscCzasuPojawiania.length() + 1;
        return obiekty.lastElement().czasPojawiania;
    }
    private int pobierzKierunek(int i)
    {
        String zawartoscKierunku = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscKierunku += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().kierunek = Integer.parseInt(zawartoscKierunku);
        przesuniecie += zawartoscKierunku.length() + 1;
        return obiekty.lastElement().kierunek;
    }
    private int pobierzCzasZnikania(int i)
    {
        String zawartoscCzasuZnikania = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscCzasuZnikania += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().czasZnikania = Integer.parseInt(zawartoscCzasuZnikania);
        przesuniecie += zawartoscCzasuZnikania.length() + 1;
        return obiekty.lastElement().czasZnikania;
    }
    private int pobierzV(int i)
    {
        String zawartoscV = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscV += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().V = Integer.parseInt(zawartoscV);
        przesuniecie += zawartoscV.length() + 1;
        return obiekty.lastElement().V;
    }
    private int pobierzS(int i)
    {
        String zawartoscS = "";
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscS += mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().s = Integer.parseInt(zawartoscS);
        przesuniecie += zawartoscS.length() + 1;
        return obiekty.lastElement().s;
    }
    private char pobierzKolor(int i)
    {
        char zawartoscKolor = 0;
        for(int j = przesuniecie; mapaTxt.get(i).charAt(j) != '.'; j++)
        {
            zawartoscKolor = mapaTxt.get(i).charAt(j);
        }
        obiekty.lastElement().kolor = zawartoscKolor;
        przesuniecie += 1;
        return obiekty.lastElement().kolor;
    }




    private void pobierzDlaLini(int i)
    {
        pobierzX(i);
        pobierzY(i);
    }
    private void pobierzDlaliniObracanej(int i)
    {
        pobierzX(i);
        pobierzY(i);
        pobierzV(i);
    }
    private void pobierzDlaRespawn(int i)
    {
        RESPAWN = new Vector2(pobierzX(i),pobierzY(i));
    }
    private void pobierzDlaLiniZnikajacej(int i)
    {
        pobierzX(i);
        pobierzY(i);
        pobierzCzasPojawiania(i);
        pobierzCzasZnikania(i);
    }
    private void pobierzDlaZmiany(int i)
    {
        pobierzX(i);
        pobierzY(i);
    }
    private void pobierzDlaLiniPodnoszonej(int i)
    {
        pobierzX(i);
        pobierzY(i);
        pobierzS(i);
        pobierzV(i);
        pobierzKierunek(i);
    }

    private void pobierzDlaGwiazdki(int i)
    {
        pobierzX(i);
        pobierzY(i);
        pobierzS(i);
        pobierzV(i);
        pobierzKolor(i);
    }

    private void pobierzDlaKolcow(int i)
    {
        pobierzX(i);
        pobierzY(i);
        pobierzS(i);
        pobierzV(i);
    }

    public Vector<Struct>getObiekty()
    {
        return obiekty;
    }
    public Tlo getTlo() {
        return tlo;
    }
    public void wczytajTlo(String sciezka)
    {
        tlo = new Tlo(0,0,sciezka);
    }

    public void przeksztalcMape()
    {
        for(int i = 0; i != mapaTxt.size(); i++)
        {
            obiekty.add(new Struct());
            pobierzWlasciwosci(i);
            przesuniecie = 0;
        }
    }
}
