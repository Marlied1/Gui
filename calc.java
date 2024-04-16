import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calc extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton, clearButton, negateButton, decimalButton;

    private String input;
    private double result;

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
        
        if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
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
            if (!input.isEmpty()) {
                result = evaluateExpression(input);
                textField.setText(input + " = " + result);
                input = String.valueOf(result);
            }
        } else {
            if (!input.isEmpty()) {
                input += " " + command + " ";
                textField.setText(input);
            }
        }
    }

    private double evaluateExpression(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        new calc();
    }
}
