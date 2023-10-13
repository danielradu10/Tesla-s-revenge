package Incercari;

import Levels.LogIn;
import Levels.LogInRoadMap;
import Levels.LogInRoadMapProxy;
import entities.Fulger;
import entities.Seahorse;
import utile.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Username {

    JPanel usernamepanel;
    BufferedImage background;

    boolean visible = true;

    private JLabel label;
    private JTextField username;

    Fulger f1 = new Fulger(100, 50, 512, 256, 2);

    //un obiect de tip roadmap
    LogIn roadmap;

    String username_text;

    public Username(){
        background =LoadSave.importImg("/menubg.png");
    }

    //creez un nou panel pentru a introduce numele
    public void initUsername(LogIn roadmap1)
    {
        usernamepanel = new JPanel();

        Dimension dim = new Dimension(250, 100);
        usernamepanel.setMinimumSize(dim);
        usernamepanel.setPreferredSize(dim);
        usernamepanel.setMaximumSize(dim);

        usernamepanel.setLocation(150,150);

        usernamepanel.setBackground(new Color(255,255,255));
        usernamepanel.setLayout(null);


        label = new JLabel("Username");



        label.setBounds(20,20, 70,20);


        usernamepanel.add(label);
        username = new JTextField();
        username.setBounds(20,40,193,28);
        usernamepanel.add(username);
        {

        }
        JButton button = new JButton("Login");
        button.setBounds(150,50,100,75);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(102,178,255));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cand apas login preiau textul introdus si instantiez road mapul corespunzator
                username_text = username.getText();
                System.out.println(username_text);
                roadmap = new LogInRoadMapProxy();
                roadmap.connect(username_text);
                visible = false;
                System.out.println(username_text);

            }
        });


        usernamepanel.add(button);


        usernamepanel.setVisible(false);

    }

    public void render(Graphics g)
    {
        g.drawImage(background, 0 , 0, 1280, 720, null);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(background, 0 , 0, 1280, 720, null);
        Font fnt0 = new Font("arial", Font.BOLD, 70);
        g.setFont(fnt0);
        g.setColor(new Color(102, 178, 255));
        g.drawString("TESLA'S", 450, 350);
        // g.setColor(new Color(153, 204, 255));
        g.drawString("REVENGE", 600, 420);
        f1.render(g);

        if(roadmap != null)
        {
            roadmap.render(g);
        }
    }

    public void update(){
        f1.update();

    }

    public JPanel getPanel()
    {
        return this.usernamepanel;
    }



    public void setVisible()
    {
        usernamepanel.setVisible(true);
    }

    public void setInvisible(){ usernamepanel.setVisible(false);}

    public LogIn getRoadMap(){
        return this.roadmap;
    }

    public boolean getVisbility()
    {
        return visible;
    }

    public String getUsername_text(){
        return username_text;
    }






}
