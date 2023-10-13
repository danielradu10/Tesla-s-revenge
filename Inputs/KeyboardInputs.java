package Inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utile.Constante.Directions.*;
import static utile.Constante.GameStates.MENUSTATE;

public class KeyboardInputs implements KeyListener {
    //inherits the template KeyListener which comes with some virtual methods that HAVE to be IMPLEMENTED

    private GamePanel gamePanel;

    public int lastGameState;


    public KeyboardInputs(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // "citirea" evenimentului si verificarea tastei pe care am apasat-o
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                if(!gamePanel.getGame().getPlayer().getblockJumping())
                    gamePanel.getGame().getPlayer().setPreparing(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);


                //gamePanel.getGame().getLvl1().setMoveleft(true);
                break;
            case KeyEvent.VK_ESCAPE:
                gamePanel.getGame().getLvl2().setSaved(true);
                break;
            //daca apas M, salvez starea in care eram pentru a putea reveni la loc si generez meniul secundar
            case KeyEvent.VK_M:
                lastGameState = gamePanel.getGame().getState();
                gamePanel.getGame().setGameState(MENUSTATE);
                //gamePanel.setDirection(-1);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setPreparing(false);

                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);

            case KeyEvent.VK_ESCAPE:
                gamePanel.getGame().getLvl2().setSaved(false);
                break;
            //revin in ultima stare in care eram.
            case KeyEvent.VK_R:
                gamePanel.getGame().setGameState(lastGameState);



            default:
                gamePanel.getGame().getPlayer().setUp(false);
                break;

                //gamePanel.setDirection(-1);
        }

    }
}
