//import needed files from the Java Swing library

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// defines a new class named Calculator that extends the JFrame class, making it a window frame.
public class Calculator extends JFrame {

    // declare private instance variables representing text fields 

    //for inputting numbers,
    private JTextField num1Field;
    private JTextField num2Field;
    //a text field for displaying the result, 
    private JTextField resultField;
    //and radio buttons for selecting mathematical operations
    private JRadioButton addRadio;
    private JRadioButton subtractRadio;
    private JRadioButton multiplyRadio;
    private JRadioButton divideRadio;


    // initialize GUI components behavior
    public Calculator() {
        //title
        setTitle("Simple Calculator");
        //exit code when closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set the layout manager of the JFrame to a 5x2 grid layout with 5 pixels of horizontal and vertical spacing between components.
        setLayout(new GridLayout(5, 2, 5, 5));

        //create JLabels and JTextFields for inputting numbers, as well as a JLabel for labeling the radio buttons.
        JLabel num1Label = new JLabel("Number 1:");
        num1Field = new JTextField();
        JLabel num2Label = new JLabel("Number 2:");
        num2Field = new JTextField();
        JLabel operatorLabel = new JLabel("Operator:");
        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new FlowLayout());
        addRadio = new JRadioButton("+");
        subtractRadio = new JRadioButton("-");
        multiplyRadio = new JRadioButton("*");
        divideRadio = new JRadioButton("/");
        ButtonGroup operatorGroup = new ButtonGroup();
        operatorGroup.add(addRadio);
        operatorGroup.add(subtractRadio);
        operatorGroup.add(multiplyRadio);
        operatorGroup.add(divideRadio);
        operatorPanel.add(addRadio);
        operatorPanel.add(subtractRadio);
        operatorPanel.add(multiplyRadio);
        operatorPanel.add(divideRadio);
        JLabel resultLabel = new JLabel("Result:");
        resultField = new JTextField();
        resultField.setEditable(false);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int num1 = Integer.parseInt(num1Field.getText());
                    int num2 = Integer.parseInt(num2Field.getText());
                    int result = 0;
                    if (addRadio.isSelected()) {
                        result = num1 + num2;
                    } else if (subtractRadio.isSelected()) {
                        result = num1 - num2;
                    } else if (multiplyRadio.isSelected()) {
                        result = num1 * num2;
                    } else if (divideRadio.isSelected()) {
                        result = num1 / num2;
                    }
                    resultField.setText(Integer.toString(result));
                } catch (NumberFormatException ex) {
                    resultField.setText("Invalid Input");
                } catch (ArithmeticException ex) {
                    resultField.setText("Cannot divide by zero");
                }
            }
        });

        add(num1Label);
        add(num1Field);
        add(num2Label);
        add(num2Field);
        add(operatorLabel);
        add(operatorPanel);
        add(resultLabel);
        add(resultField);
        add(new JLabel());
        add(calculateButton);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }
}
