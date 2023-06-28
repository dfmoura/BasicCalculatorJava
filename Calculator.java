import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField display;

    private String currentInput;
    private String operator;
    private double result;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 300);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "A", "B", "C", "D",
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonsPanel.add(button);
        }

        frame.add(buttonsPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        currentInput = "";
        operator = "";
        result = 0;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                currentInput += command;
                display.setText(currentInput);
            } else if (command.equals("C")) {
                currentInput = "";
                operator = "";
                result = 0;
                display.setText("");
            } else if (command.matches("[+\\-*/]")) {
                operator = command;
                result = Double.parseDouble(currentInput);
                currentInput = "";
                display.setText("");
            } else if (command.equals("=")) {
                if (!currentInput.equals("") && !operator.equals("")) {
                    double operand = Double.parseDouble(currentInput);
                    switch (operator) {
                        case "+":
                            result += operand;
                            break;
                        case "-":
                            result -= operand;
                            break;
                        case "*":
                            result *= operand;
                            break;
                        case "/":
                            result /= operand;
                            break;
                    }
                    currentInput = Double.toString(result);
                    display.setText(currentInput);
                    operator = "";
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}
