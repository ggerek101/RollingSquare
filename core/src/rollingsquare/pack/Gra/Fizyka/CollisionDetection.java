package rollingsquare.pack.Gra.Fizyka;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import rollingsquare.pack.Gra.Fizyka.obiekty.Bohater;
import rollingsquare.pack.Gra.Fizyka.obiekty.CeleMapy.Gwiazdka;
import rollingsquare.pack.Gra.Fizyka.obiekty.Kolce;
import rollingsquare.pack.Gra.Fizyka.obiekty.Linia;
import rollingsquare.pack.Gra.Fizyka.obiekty.LiniaProsta;
import rollingsquare.pack.Gra.Fizyka.obiekty.Obiekt;
import rollingsquare.pack.Gra.Fizyka.obiekty.Zmiana;

/**
 * Created by aaa on 2015-03-15.
 */
public class CollisionDetection implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

     /*   if(a.getUserData() != null && a.getUserData().equals("foot")) {
            if(b.getBody().getPosition().y - a.getBody().getPosition().y > 30 * Swiat.WTB)
            Bohater.WlasciwosciBohatera.BohaterKoliduje = true;
        }
        if(b.getUserData() != null && b.getUserData().equals("foot")) {
            if(a.getBody().getPosition().y - b.getBody().getPosition().y > 30 * Swiat.WTB)
            Bohater.WlasciwosciBohatera.BohaterKoliduje = true;
        }
        */
        if(bodyA.getUserData() instanceof Bohater && bodyB.getUserData() instanceof Kolce)
        {
            Bohater b = (Bohater) bodyA.getUserData();
            b.umarl = true;
            Kolce k = (Kolce) bodyB.getUserData();
            k.umarl = true;
        }
        if(bodyA.getUserData() instanceof Kolce && bodyB.getUserData() instanceof Bohater)
        {
            Bohater b = (Bohater) bodyB.getUserData();
            b.umarl = true;
            Kolce k = (Kolce) bodyA.getUserData();
            k.umarl = true;
        }

        if(bodyA.getUserData() instanceof Bohater && bodyB.getUserData() instanceof Gwiazdka)
        {
            Bohater h = (Bohater) bodyA.getUserData();
            Gwiazdka g = (Gwiazdka) bodyB.getUserData();
            if(h.getKolor() == g.getKolor()) {
                h.zebralGwiazdke = true;
                g.umarl = true;
            }}
        if(bodyA.getUserData() instanceof Gwiazdka && bodyB.getUserData() instanceof Bohater)
        {
            Bohater h = (Bohater) bodyB.getUserData();
            Gwiazdka g = (Gwiazdka) bodyA.getUserData();
            if(h.getKolor() == g.getKolor()) {
                h.zebralGwiazdke = true;
                g.umarl = true;
            }
        }

        if(fixtureA.getUserData() == "heroFoot" && bodyB.getUserData() instanceof Obiekt)
        {
            Bohater h = (Bohater) bodyA.getUserData();
            h.moznaSkakac = true;
        }
        if(bodyA.getUserData() instanceof Obiekt && fixtureB.getUserData() == "heroFoot")
        {
            Bohater h = (Bohater) bodyB.getUserData();
            h.moznaSkakac = true;
        }

        if(bodyA.getUserData() instanceof Bohater && bodyB.getUserData() instanceof Zmiana)
        {
            Bohater h = (Bohater) bodyA.getUserData();
            h.zmienKolor = true;
        }
        if(bodyA.getUserData() instanceof Zmiana && bodyB.getUserData() instanceof Bohater)
        {
            Bohater h = (Bohater) bodyB.getUserData();
            h.zmienKolor = true;
        }
    }

    @Override
    public void endContact(Contact contact)
    {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() instanceof Bohater && bodyB.getUserData() instanceof Zmiana)
        {
            Bohater h = (Bohater) bodyA.getUserData();
            h.zmienKolor = false;
        }
        if(bodyA.getUserData() instanceof Zmiana && bodyB.getUserData() instanceof Bohater)
        {
            Bohater h = (Bohater) bodyB.getUserData();
            h.zmienKolor = false;
        }

        /*
        if(a.getUserData() == "heroBody" && b.getUserData() == "gwiazdka")
        {
            Bohater h = (Bohater) a.getUserData();
            h.stan = Obiekt.Stan.Zniszczony;
            h.zebralGwiazdke = false;
            Gwiazdka g = (Gwiazdka) b.getUserData();
            g.stan = Obiekt.Stan.Zniszczony;
        }
        if(a.getUserData() == "gwiazdka" && b.getUserData() == "heroBody")
        {
            Bohater h = (Bohater) b.getUserData();
            h.stan = Obiekt.Stan.Zniszczony;
            h.zebralGwiazdke = false;
            Gwiazdka l = (Gwiazdka) a.getUserData();
            l.stan = Obiekt.Stan.Zniszczony;
        }
        */

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
