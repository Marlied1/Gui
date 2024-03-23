// import the necessary packages from the Java Swing library 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// defines new class name
public class TemperatureConverter extends JFrame {
    //declare private instance variables
    //used to input the Celsius temperature
    private JTextField celsiusField;
    // display the converted Fahrenheit temperature,
    private JTextField fahrenheitField;


    //Initialized behavior 
    public TemperatureConverter() {
        // set the title of the JFrame window,
        setTitle("Temperature Converter");
        //program should exit when the window is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set the layout manager of the JFrame to a 3x2 grid layout with 5 pixels of horizontal and vertical spacing between components
        setLayout(new GridLayout(3, 2, 5, 5));

        //for inputting and displaying temperatures in Celsius and Fahrenheit.
        JLabel celsiusLabel = new JLabel("Celsius:");
        celsiusField = new JTextField();
        //The fahrenheitField is set to non-editable.
        JLabel fahrenheitLabel = new JLabel("Fahrenheit:");
        fahrenheitField = new JTextField();
        fahrenheitField.setEditable(false);

        //create a JButton
        JButton convertButton = new JButton("Convert");
        //When the button is clicked, the actionPerformed runs
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //converts the temperature from Celsius to Fahrenheit when the convertButton is clicked.
                try {
                    //into a double, performs the conversion, and sets the result in the fahrenheitField
                    double celsius = Double.parseDouble(celsiusField.getText());
                    double fahrenheit = (celsius * 9/5) + 32;
                    fahrenheitField.setText(Double.toString(fahrenheit));

                    //If the input is not a valid number, it sets the fahrenheitField to display "Invalid Input".
                } catch (NumberFormatException ex) {
                    fahrenheitField.setText("Invalid Input");
                }
            }
        });
            //When the button is clicked, it clears the text in both the celsiusField and fahrenheitField.
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                celsiusField.setText("");
                fahrenheitField.setText("");
            }
        });
        //adds all the GUI components to the JFrame in it's specified order.
        add(celsiusLabel);
        add(celsiusField);
        add(fahrenheitLabel);
        add(fahrenheitField);
        add(convertButton);
        add(clearButton);

        //pack all the components into the frame and center the frame on the screen.
        pack();
        setLocationRelativeTo(null);
    }

    //creates an instance of the TemperatureConverter class and sets it to be visible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}

