package rollingsquare.pack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import rollingsquare.pack.Gra.Fizyka.Fizyka;
import rollingsquare.pack.Gra.Gra;
import rollingsquare.pack.Menu.Menu;

public class RollingSquare extends Game {
	private enum StanGry{Intro,Menu,Gra}
	StanGry stanGry;
	Menu menu;
	Gra gra;
	Intro intro;
	public static Fizyka fizyka;

	private void stworzGre(int numerPoziomu)
	{
		gra = new Gra(numerPoziomu);
	}

	private void stworzMenu()
	{
		menu = new Menu();
	}

	public void create ()
	{
		Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height,false);
		Grafika.init();
		gra = null;
		menu = null;
		intro = null;
		stanGry = StanGry.Menu;
		fizyka = new Fizyka();
	}

	@Override
	public void render ()
	{
		Grafika.odswiez();
		switch(stanGry)
		{
			case Intro:
				if(intro == null)
				{
					intro = new Intro("intro.jpg");
				}
				if(intro.czyIscDalej)
				{
					stanGry = StanGry.Menu;
				}
				else
				{
					intro.aktualizuj();
					intro.rysuj();
				}
				break;
			case Menu:
				if(menu == null)
				{
					stworzMenu();
				}
				else
				{
					menu.aktualizuj();
				}
				if(menu.zacznijGre)
				{
					stanGry = StanGry.Gra;
				}
				break;
			case Gra:
				if(gra == null) {
					stworzGre(menu.getNumerPoziomu());
				}
				gra.rozgrywka();
				break;
		}
		Gdx.app.log("FPS", String.valueOf(Gdx.graphics.getFramesPerSecond()));
	}
}
