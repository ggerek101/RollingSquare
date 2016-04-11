package rollingsquare.pack.Gra.Fizyka.obiekty;

/**
 * Created by aaa on 2015-03-11.
 */
public class LiniaObracana extends Linia
{
    float V;
    public LiniaObracana(float x, float y, float V, String sciezka) {
        super(x, y, sciezka);
        this.V = V / 6;
    }

    @Override
    public void uruchom()
    {
        switch (rand.nextInt(200)) {
            case 0:
                body.setLinearVelocity(V, body.getLinearVelocity().y);
                break;
            case 1:
                body.setLinearVelocity(-V, body.getLinearVelocity().y);
                break;
            case 3:
                body.setLinearVelocity(body.getLinearVelocity().x, body.getLinearVelocity().y);
                break;
        }
        switch (rand.nextInt(200)) {
            case 0:
                body.setLinearVelocity(body.getLinearVelocity().x, V);
                break;
            case 1:
                body.setLinearVelocity(body.getLinearVelocity().x, -V);
                break;
            case 3:
                body.setLinearVelocity(body.getLinearVelocity().x, body.getLinearVelocity().y);
                break;
        }
        body.setTransform(body.getWorldCenter(),body.getTransform().getRotation() + V / 100);
        sprite.setRotation((float) (body.getTransform().getRotation()   * (180/Math.PI)));
        sprite.setOriginCenter();
    }
}
