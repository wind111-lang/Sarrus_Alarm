import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Alarm extends JFrame implements Runnable {
    private JPanel panel1;
    private JButton uph1;
    private JButton uph2;
    private JButton upm1;
    private JButton upm2;
    private JButton downh1;
    private JButton downh2;
    private JButton downm1;
    private JButton downm2;
    private JButton SETButton;
    private JTextField answer;
    private JLabel a;
    private JLabel b;
    private JLabel c;
    private JLabel d;
    private JLabel e;
    private JLabel f;
    private JLabel g;
    private JLabel h;
    private JLabel i;
    private JLabel kakko1;
    private JLabel kakko2;
    private JLabel equal;
    private JLabel koron;
    private JLabel h1;
    private JLabel h2;
    private JLabel m1;
    private JLabel m2;
    private JLabel timeset;
    private JButton Answer;
    private String Setting;
    private int h1_time;
    private int h2_time;
    private int m1_time;
    private int m2_time;
    private int sarrus;
    private boolean AlarmMode = false;
    private boolean AlarmRing = false;
    private Clip clip;

    public void AlarmStart() {
        //System.out.println("set");
        AlarmMode = true;
        Thread Alarm = new Thread(this);
        Alarm.start();
    }

    public void AlarmStop() {
        clip.stop();
        AlarmMode = false;
    }

    public Alarm() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(800, 800, 800, 800);
        setContentPane(panel1);
        setTitle("alarm");
        Random rand = new Random();
        //answer.setVisible(false);
        Answer.setEnabled(false);
        Alarm.this.revalidate();

        SETButton.addActionListener(set -> {
            uph1.setEnabled(false);
            uph2.setEnabled(false);
            downh1.setEnabled(false);
            downh2.setEnabled(false);
            upm1.setEnabled(false);
            upm2.setEnabled(false);
            downm1.setEnabled(false);
            downm2.setEnabled(false);
            Alarm.this.revalidate();
            //GregorianCalendar time = new GregorianCalendar();
            Setting = h1.getText() + h2.getText() + koron.getText() + m1.getText() + m2.getText();
            //System.out.println(time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE));
            if (h1.getText().equals("0") && m1.getText().equals("0")) {
                Setting = h2.getText() + koron.getText() + m2.getText();
            } else if (h1.getText().equals("0")) {
                Setting = h2.getText() + koron.getText() + m1.getText() + m2.getText();
            } else if (m1.getText().equals("0")) {
                Setting = h1.getText() + h2.getText() + koron.getText() + m2.getText();
            }
            timeset.setText(h1.getText() + h2.getText() + koron.getText() + m1.getText() + m2.getText() + "にアラームが鳴ります");

            if (SETButton.getText().equals("SET")) {
                SETButton.setText("STOP");
                //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                AlarmStart();
            } else {
                if (!AlarmRing) {
                    timeset.setText("");
                    Answer.setText("");
                    Alarm.this.revalidate();
                    uph1.setEnabled(true);
                    uph2.setEnabled(true);
                    downh1.setEnabled(true);
                    downh2.setEnabled(true);
                    upm1.setEnabled(true);
                    upm2.setEnabled(true);
                    downm1.setEnabled(true);
                    downm2.setEnabled(true);
                    answer.setEnabled(false);
                    SETButton.setText("SET");
                    kakko1.setText("");
                    kakko2.setText("");
                    equal.setText("");
                    a.setText("");
                    b.setText("");
                    c.setText("");
                    d.setText("");
                    e.setText("");
                    f.setText("");
                    g.setText("");
                    h.setText("");
                    i.setText("");
                    AlarmStop();
                } else {
                    Alarm.this.revalidate();
                    Answer.setEnabled(true);
                    Answer.setText("Answer");
                    timeset.setText("");

                    Alarm.this.revalidate();
                    //answer.setVisible(true);
                    answer.setEnabled(true);

                    kakko1.setText("(");
                    kakko2.setText(")");
                    equal.setText("=");
                    a.setText(String.valueOf(rand.nextInt(9) + 1));
                    b.setText(String.valueOf(rand.nextInt(9) + 1));
                    c.setText(String.valueOf(rand.nextInt(9) + 1));
                    d.setText(String.valueOf(rand.nextInt(9) + 1));
                    e.setText(String.valueOf(rand.nextInt(9) + 1));
                    f.setText(String.valueOf(rand.nextInt(9) + 1));
                    g.setText(String.valueOf(rand.nextInt(9) + 1));
                    h.setText(String.valueOf(rand.nextInt(9) + 1));
                    i.setText(String.valueOf(rand.nextInt(9) + 1));

                    sarrus = (Integer.parseInt(a.getText()) * Integer.parseInt(e.getText()) * Integer.parseInt(i.getText())) +
                            (Integer.parseInt(b.getText()) * Integer.parseInt(f.getText()) * Integer.parseInt(g.getText())) +
                            (Integer.parseInt(c.getText()) * Integer.parseInt(d.getText()) * Integer.parseInt(h.getText())) -
                            (Integer.parseInt(c.getText()) * Integer.parseInt(e.getText()) * Integer.parseInt(g.getText())) -
                            (Integer.parseInt(a.getText()) * Integer.parseInt(h.getText()) * Integer.parseInt(f.getText())) -
                            (Integer.parseInt(b.getText()) * Integer.parseInt(d.getText()) * Integer.parseInt(i.getText()));
                }
            }
        });
        uph1.addActionListener(e -> {
            if (h1.getText().equals("2")) {
                h1.setText("0");
                h1_time = 0;
            } else {
                h1_time++;
                h1.setText(String.valueOf(h1_time));
                if (h1.getText().equals("2") && h2.getText().matches("[^0123]")) {
                    h2.setText("0");
                    h2_time = 0;
                }
            }
        });
        uph2.addActionListener(e -> {
            if (h1.getText().equals("2") && h2.getText().equals("3")) {
                h2.setText("0");
                h2_time = 0;
            } else if (h2.getText().equals("9")) {
                h2.setText("0");
                h2_time = 0;
            } else {
                h2_time++;
                h2.setText(String.valueOf(h2_time));
            }
        });
        downh1.addActionListener(e -> {
            if (h1.getText().equals("0")) {
                h1.setText("2");
                h1_time = 2;
                if (h2.getText().matches("[^0123]")) {
                    h2.setText("0");
                    h2_time = 0;
                }
            } else {
                h1_time--;
                h1.setText(String.valueOf(h1_time));
            }
        });
        downh2.addActionListener(e -> {
            if (h2.getText().equals("0")) {
                h2.setText("9");
                h2_time = 9;
                if (h1.getText().equals("2")) {
                    h1.setText("0");
                    h1_time = 0;
                }
            } else {
                h2_time--;
                h2.setText(String.valueOf(h2_time));
            }
        });
        upm1.addActionListener(e -> {
            if (m1.getText().equals("5")) {
                m1.setText("0");
                m1_time = 0;
            } else {
                m1_time++;
                m1.setText(String.valueOf(m1_time));
            }
        });
        upm2.addActionListener(e -> {
            if (m2.getText().equals("9")) {
                m2.setText("0");
                m2_time = 0;
            } else {
                m2_time++;
                m2.setText(String.valueOf(m2_time));
            }
        });
        downm1.addActionListener(e -> {
            if (m1.getText().equals("0")) {
                m1.setText("5");
                m1_time = 5;
            } else {
                m1_time--;
                m1.setText(String.valueOf(m1_time));
            }
        });
        downm2.addActionListener(e -> {
            if (m2.getText().equals("0")) {
                m2.setText("9");
                m2_time = 9;
            } else {
                m2_time--;
                m2.setText(String.valueOf(m2_time));
            }
        });
        Answer.addActionListener(set -> {
            if (Answer.getText().equals("Answer")) {
                System.out.println(sarrus);
                if (!String.valueOf(sarrus).equals(answer.getText())) {
                    timeset.setText("不正解");
                } else {
                    timeset.setText("正解 STOPを押して目覚ましを止めてね");
                    sarrus = 0;
                    answer.setText("");
                    equal.setText("");
                    AlarmRing = false;
                    Answer.setText("");
                    Answer.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void run() {
        while (AlarmMode) {
            if (isAlarmTime()) {
                while (AlarmMode) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.exit(1);
                    }
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
    }

    private boolean isAlarmTime() {
        GregorianCalendar time = new GregorianCalendar();
        try {
            if ((time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE)).equals(Setting)) {
                System.out.println("zikandayo");
                timeset.setText("時間です");
                AlarmRing = true;
                try {
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(new File("Alarm.wav")));
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                    ex.printStackTrace();
                }
                return true;
            }
            System.out.println(time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE));
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Alarm().setVisible(true));
    }
}
