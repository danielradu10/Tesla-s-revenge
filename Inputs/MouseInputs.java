package Inputs;

import entities.TeslaCard;
import main.Game;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static utile.Constante.GameStates.*;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private boolean pressed;

    boolean shooting_pressed = true;
    public MouseInputs(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(shooting_pressed) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                gamePanel.getGame().getPlayer().setShoot(true);
                gamePanel.getGame().getPlayer().set_shoot_now(true);
                gamePanel.getGame().getPlayer().set_shoot_ani(true);
            }
        }
        //daca sunt in meniu si sunt deasupra butonului de play
        if(e.getButton() == MouseEvent.BUTTON1 && gamePanel.getGame().getMeniu().isPlay_almost_pressed() && !gamePanel.getGame().isLevel2())
        {
            gamePanel.getGame().getMeniu().setPlay_pressed(true);
            gamePanel.getGame().setGameState(1);
        }
        //in functie de ce buton din roadmap apas, setez gamestate ul in care o sa ma duc
        if(gamePanel.getGame().getUsername().getRoadMap() != null && !gamePanel.getGame().isLevel2()) {
            System.out.println("Mda.aa");
            if (e.getButton() == MouseEvent.BUTTON1 && gamePanel.getGame().getUsername().getRoadMap().getLvl1()) {
                gamePanel.getGame().setGameState(2);
            }

            if (e.getButton() == MouseEvent.BUTTON1 && gamePanel.getGame().getUsername().getRoadMap().getLvl2()) {
                gamePanel.getGame().setGameState(3);
            }
        }

        //verific daca vreunul din teslacarduri a fost apasat
        for( TeslaCard teslaCard : gamePanel.getGame().getLvl2().getTeslaList())
        {
            if(teslaCard.getClicked() == 1)
            {
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                    teslaCard.setUnclicked(0);
                    teslaCard.setLightning(true);
                }
            }
            if(e.getButton() == MouseEvent.BUTTON1 &&  teslaCard.get_almost_clicked() && teslaCard.getUnclicked() == 1)
            {
                teslaCard.set_clicked(1);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        System.out.println("Pressed: " + pressed);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        System.out.println("Pressed: " + pressed);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");

    }

    @Override
    public void mouseDragged(MouseEvent e) {


        //drag and drop function
        System.out.println("Ai mutat mouse");

    }

    @Override
    public void mouseMoved(MouseEvent e) {



        if(gamePanel.getGame().getState() == LEVEL_two)
        {
            for(TeslaCard tesla : gamePanel.getGame().getLvl2().getTeslaList())
            {
                if(tesla.getClicked() == 1)
                {
                    tesla.getVector2D().setVectX(e.getX());
                    tesla.getVector2D().setVectY(e.getY());
                }
            }

            for(TeslaCard tesla : gamePanel.getGame().getLvl2().getTeslaList())
            {
                if(e.getX() >= tesla.getVector2D().getVectX() && e.getX() <= tesla.getVector2D().getVectX()+64)
                {
                    if(e.getY() >= tesla.getVector2D().getVectY() && e.getY() <= tesla.getVector2D().getVectY()+64)
                    {
                        tesla.set_almost_clicked(true);
                        shooting_pressed = false;
                        System.out.println("Esti pe un card");
                    }
                    else
                    {
                        tesla.set_almost_clicked(false);
                        shooting_pressed = true;
                    }
                }
                else
                {
                    shooting_pressed = true;
                    tesla.set_almost_clicked(false);
                }
            }
        }

        if(gamePanel.getGame().getState() == MENIU) {


            if (e.getX() >= gamePanel.xDelta && e.getX() <= gamePanel.xDelta + 40) {
                if (e.getY() >= gamePanel.yDelta && e.getY() <= gamePanel.yDelta + 30) {
                    System.out.println("Este intr-o zona a patratului");
                }
            }

            if (e.getX() >= gamePanel.getGame().getMeniu().play.x && e.getX() <= gamePanel.getGame().getMeniu().play.x + 150) {

                if (e.getY() >= gamePanel.getGame().getMeniu().play.y && e.getY() <= gamePanel.getGame().getMeniu().play.y + 50) {

                    gamePanel.getGame().getMeniu().setPlay_almost_pressed(true);
                } else {
                    gamePanel.getGame().getMeniu().setPlay_almost_pressed(false);
                }
            } else {
                gamePanel.getGame().getMeniu().setPlay_almost_pressed(false);
            }

            if (e.getX() >= gamePanel.getGame().getMeniu().Quit.x && e.getX() <= gamePanel.getGame().getMeniu().Quit.x + 150) {

                if (e.getY() >= gamePanel.getGame().getMeniu().Quit.y && e.getY() <= gamePanel.getGame().getMeniu().Quit.y + 50) {

                    gamePanel.getGame().getMeniu().setQuit_almost_pressed(true);
                } else {
                    gamePanel.getGame().getMeniu().setQuit_almost_pressed(false);
                }
            } else {
                gamePanel.getGame().getMeniu().setQuit_almost_pressed(false);
            }

            if (e.getX() >= gamePanel.getGame().getMeniu().info.x && e.getX() <= gamePanel.getGame().getMeniu().info.x + 150) {

                if (e.getY() >= gamePanel.getGame().getMeniu().info.y && e.getY() <= gamePanel.getGame().getMeniu().info.y + 50) {

                    gamePanel.getGame().getMeniu().setInfo_almost_pressed(true);
                } else {
                    gamePanel.getGame().getMeniu().setInfo_almost_pressed(false);
                }
            } else {
                gamePanel.getGame().getMeniu().setInfo_almost_pressed(false);
            }
        }

        if(gamePanel.getGame().getState() == USERNAME) {



                if (gamePanel.getGame().getUsername().getRoadMap() != null) {
                    if(gamePanel.getGame().getUsername().getRoadMap().getLevel() == 1) {
                        if (e.getX() >= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().x && e.getX() <= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().x + 200) {
                            if (e.getY() >= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().y && e.getY() <= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().y + 50) {
                                gamePanel.getGame().getUsername().getRoadMap().setalmostLvl1(true);
                            }
                        } else {
                            gamePanel.getGame().getUsername().getRoadMap().setalmostLvl1(false);
                        }
                    }
                    else if (gamePanel.getGame().getUsername().getRoadMap().getLevel() == 2)
                        {
                            if (e.getX() >= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().x && e.getX() <= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().x + 200) {
                                if (e.getY() >= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().y && e.getY() <= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().y + 50) {
                                    gamePanel.getGame().getUsername().getRoadMap().setalmostLvl1(true);
                                }
                            }
                            else
                            {
                                gamePanel.getGame().getUsername().getRoadMap().setalmostLvl1(false);
                            }
                            if (e.getX() >= gamePanel.getGame().getUsername().getRoadMap().getLvl2Rect().x && e.getX() <= gamePanel.getGame().getUsername().getRoadMap().getLvl2Rect().x + 200)
                            {
                                if (e.getY() >= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().y && e.getY() <= gamePanel.getGame().getUsername().getRoadMap().getLvl1Rect().y + 50) {
                                    gamePanel.getGame().getUsername().getRoadMap().setalmostLvl2(true);
                                }

                            }
                            else
                            {
                                gamePanel.getGame().getUsername().getRoadMap().setalmostLvl2(false);
                            }
                        }
                }
            }

        }

    }

