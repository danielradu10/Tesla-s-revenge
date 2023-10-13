package Incercari;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.sql.*;
import java.util.Random;

public class Quiz{


    //sqls

    String username_text;

    JPanel panel;


    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();

    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();

    boolean visble = false;


    String[] questions = {"Legea lui Ohm: ", "Legea lui Ohm pe intreg circuitul", "Unitatea de masura pentru Intensitate:", "Unitatea de masura pentru Tensiune: ", "Unitatea de masura pentru Rezistenta:"};

    String[][] options = {
            {"I/U", "U/I", "U*I", "I/R+r"},
            {"E/(R+r)", "U/I", "E*(R+r)", "I/U"},
            {"Amper", "Volt", "Ohm", "Altceva"},
            {"Amper", "Volt", "Ohm", "Altceva"},
            {"Amper", "Volt", "Ohm", "Altceva"}

    };

    char[] answers = {'B', 'A', 'A', 'B', 'C'};

    char guess;
    char answer;
    int index = 0;
    int question_index;
    int correct_answers;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    boolean done = false;

    Random random = new Random();

    //timer.start() atunci cand e setat quizul visible
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Something happened, i dont know what");
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0){
                displayAnswer();
            }
        }
    });



    public Quiz(int total_questions)
    {
        this.total_questions = total_questions;
        create_table();
        insert_in_table();
        seconds = 10;



    }


    //elementel ede gui intializate
    public void initQuiz()
    {
        panel = new JPanel();
        Dimension dim = new Dimension(650, 650);
        panel.setMinimumSize(dim);
        panel.setPreferredSize(dim);
        panel.setMaximumSize(dim);



        panel.setBackground(new Color(25,25,25));
        panel.setLayout(null);
        //panel.setLayout(new BorderLayout());

        textfield.setBounds(0,0,650,50);
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        textfield.setText("AAAAAAAA");


        textarea.setBounds(0,50,650,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,25,25));
        textarea.setForeground(new Color(25,255,0));
        textarea.setFont(new Font("Ink Free", Font.BOLD, 30));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);
        textarea.setText("Intrbarea");

        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.setText("A");

        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ai apasat A");
                answer = 'A';
                if(answer==answers[index])
                {
                    correct_answers++;
                }
                displayAnswer();
            }
        });



        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.setText("B");
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ai apasat B");
                answer = 'B';
                if(answer==answers[index])
                {
                    correct_answers++;
                }
                displayAnswer();
            }

        });



        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.setText("C");
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ai apasat C");
                answer = 'C';
                if(answer==answers[index])
                {
                    correct_answers++;
                }
                displayAnswer();
            }
        });



        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.setText("D");
        buttonD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ai apasat D");
                answer = 'D';
                if(answer==answers[index])
                {
                    correct_answers++;
                }
                displayAnswer();
            }
        });

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(255,255,255));
        answer_labelA.setForeground(new Color(255,255,255));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));


        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));


        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));


        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));

        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535,475,100,25);
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 60));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("TIMER: ");

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(25, 225, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setText(String.valueOf(correct_answers));
        number_right.setEditable(false);

        //panel.add(number_right);
        panel.add(time_label);

        panel.add(seconds_left);
        panel.add(answer_labelA);
        panel.add(answer_labelB);
        panel.add(answer_labelC);
        panel.add(answer_labelD);

        panel.add(buttonA);
        panel.add(buttonB);
        panel.add(buttonC);
        panel.add(buttonD);





        panel.add(textfield);
        panel.add(textarea);


        panel.setVisible(false);

        nextQuestion();


    }

    public JPanel getPanel()
    {
      return this.panel;
    }

    public void setVisible()
    {

        timer.start();
        this.visble = true;
        panel.setVisible(true);
    }

    public void setInvisible(){
        panel.setVisible(false);
    }

    //in displat answers afisez cu culoarea rosu raspunsurile incorecte
    public void displayAnswer()
    {


        timer.stop();
        visble = true;
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        if (answers[question_index]!='A')
        {
            answer_labelA.setForeground(new Color(255,0,0));
        }
        if (answers[question_index]!='B')
        {
            answer_labelB.setForeground(new Color(255,0,0));
        }
        if (answers[question_index]!='C')
        {
            answer_labelC.setForeground(new Color(255,0,0));
        }
        if (answers[question_index]!='D')
        {
            answer_labelD.setForeground(new Color(255,0,0));
        }
        //dupa 2 secunde afisez urmatoarele interbari si raspunsuri dar cu verde, resetez de asemenea secundele
        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void nextQuestion(){

        //verific daca nu am depasit numarul de intrebari pe care sa le afisez
        if(index>=total_questions){
            results();
        }
        else {
            textfield.setText("Question "+(index+1));
            String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_4/tests.db";
            try
            {
                Class.forName("org.sqlite.JDBC");

            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                 int nr_question = random.nextInt(questions.length);
                 while(nr_question == 0)
                 {
                     nr_question = random.nextInt(questions.length);
                 }
                 //selectez intreabarea cu id ul generat cu random din tabel
                String verify = "SELECT * FROM questions WHERE id = '" + nr_question + "' ";
                 System.out.println(nr_question);
                ResultSet rs = stmt.executeQuery(verify);
                if(rs.next())
                {
                    System.out.println("Aleg intrebarea");
                    textarea.setText(rs.getString("question"));
                    answer_labelA.setText(rs.getString("answerA"));
                    answer_labelB.setText(rs.getString("answerB"));
                    answer_labelC.setText(rs.getString("answerC"));
                    answer_labelD.setText(rs.getString("answerD"));
                    question_index = rs.getInt("id") - 1;
                }
                else
                {
                    System.out.println("Nu am gasit asemenea id ");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            if(visble)
            {
                timer.start();
                System.out.println("Am dat start timerului");
                visble = false;
            }


        }

    }

    public void results(){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        textfield.setText("Cate carduri Tesla Wizzard ai castigat?");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText(String.valueOf(correct_answers));

        panel.add(number_right);

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("done");
            }
        });




        done = true;
        updateUsernamesTable();


       // panel.remove(buttonA);



    }

    public boolean getDone()
    {
        return done;
    }



    public void create_table(){

        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_4/tests.db";

        //verific daca tabelul exista deja
       String sql = "CREATE TABLE IF NOT EXISTS questions (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	question text NOT NULL,\n"
                + "	answerA text NOT NULL,\n"
                + " answerB text NOT NULL,\n"
                + " answerC text NOT NULL,\n"
                + " answerD text NOT NULL,\n"
                + " answer  text NOT NULL\n"
                + ");";
       try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {


            // create a new table
            stmt.execute(sql);




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //adaug in table doar intrebarile care nu sunt deja acolo(daca exista)
    void insert_in_table()
    {

        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_4/tests.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {




            //parcurg vectorul de intrebari
            for(int i=0; i<questions.length; i++)
            {
                //verific ca intrebarea nu exista deja
                String verify = "SELECT id FROM questions WHERE question = '" + questions[i] + "' ";
                ResultSet rs = stmt.executeQuery(verify);
                if(rs.next())
                {
                    if(rs.getInt("id")==0) {

                        System.out.println("Nu am gasit, asa ca adaug");
                        String INSERT = "INSERT INTO questions(question, answerA, answerB, answerC, answerD, answer)" + "VALUES ('" + questions[i] + "', '" + options[i][0] + "','" + options[i][1]+"','" + options[i][2]+"','" + options[i][3]+"','"+answers[i]+"');";
                        stmt.executeUpdate(INSERT);
                    }
                    else
                    {
                        System.out.println("Am gasit deja, nu mai adaug.");
                    }

                }
                else
                {
                    System.out.println("Adaug intrebare");
                    String INSERT = "INSERT INTO questions(question, answerA, answerB, answerC, answerD, answer)" + "VALUES ('" + questions[i] + "', '" + options[i][0] + "','" + options[i][1]+"','" + options[i][2]+"','" + options[i][3]+"','"+ answers[i] +"');";
                    stmt.executeUpdate(INSERT);

                }
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    //dau opdate in tabela de username-uri la: level1 (ca fiind rezolvat), dar si la numarul de interbari corecte

    public void updateUsernamesTable()
    {

        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            //salvez progresul pentru level1
            String update = "UPDATE Usernames set level1 = 1 where username='" + username_text + "';";

            stmt.executeUpdate(update);

            //salvez numarul de intrebari corecte
            PreparedStatement update_quiz = conn.prepareStatement("UPDATE Usernames set quiz = ? where username='" + username_text + "';");
            update_quiz.setString(1, Integer.toString(correct_answers));
            update_quiz.executeUpdate();

            //dau commit pentru tabela
            conn.commit();
            System.out.println("Cred ca e ok, pentru " + username_text +" am facut update-ul cerut");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "ceva nu e bine");
        }

    }

    public void setUsername_text(String username_text)
    {
        this.username_text = username_text;
    }

}


