package com.joeytman.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.joeytman.flappybird.FlappyDemo;
import com.joeytman.flappybird.sprites.Bird;
import com.joeytman.flappybird.sprites.Tube;

/**
 * Created by Joey Thaidigsman on 5/16/2017.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 250;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 400);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("background.png");

        tubes = new Array<Tube>();
        for (int i = 0; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(350 + i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosBotTube().x + tube.TUBE_WIDTH) {
                tube.reposition(tube.getPosTopTube().x + ((tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
                break;
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PlayState disposed");
    }
}
