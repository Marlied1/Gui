import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Bible extends JFrame {

    private JTextField chapterField;
    private JTextField verseField;
    private JButton searchButton;
    private JLabel scriptureLabel;

    public Bible() {
        setTitle("Bible");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        chapterField = new JTextField(10);
        verseField = new JTextField(10);
        searchButton = new JButton("Search");
        scriptureLabel = new JLabel("Scripture goes here");

        add(new JLabel("Chapter:"));
        add(chapterField);
        add(new JLabel("Verse:"));
        add(verseField);
        add(searchButton);
        add(scriptureLabel);

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Bible();
    }
}

