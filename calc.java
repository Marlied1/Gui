import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calc extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton, clearButton, negateButton, decimalButton;

    private double num1, num2, result;
    private String input, operator;

    public calc() {
        this.setTitle("Calculator");
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        this.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        String[] operators = {"+", "-", "*", "/"};
        operationButtons = new JButton[operators.length];
        for (int i = 0; i < operators.length; i++) {
            operationButtons[i] = new JButton(operators[i]);
            operationButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            operationButtons[i].addActionListener(this);
            buttonPanel.add(operationButtons[i]);
        }

        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        negateButton = new JButton("+/-");
        decimalButton = new JButton(".");
        
        JButton[] otherButtons = {equalsButton, clearButton, negateButton, decimalButton};
        for (JButton button : otherButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        this.add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (Character.isDigit(command.charAt(0))) {
            input += command;
            textField.setText(input);
        } else if (command.equals(".") && !input.contains(".")) {
            input += command;
            textField.setText(input);
        } else if (command.equals("C")) {
            input = "";
            textField.setText("");
        } else if (command.equals("+/-")) {
            if (!input.isEmpty()) {
                double value = Double.parseDouble(input);
                value = -value;
                input = String.valueOf(value);
                textField.setText(input);
            }
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(input);
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            input = "";
        } else {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                operator = command;
                input = "";
            }
        }
    }

    public static void main(String[] args) {
        new calc();
    }
}

