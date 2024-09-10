package org.com;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class GUIcalculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[11];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton, sqrButton, sqrtButton;
    JPanel panel;

    Font myFont = new Font("Arial", Font.BOLD, 20);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean ErrorMessage = false;

    // Constructor
    public GUIcalculator() {
        frame = new JFrame("GUI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        // Text field setup
        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        frame.add(textfield);

        // Button setup
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");
        sqrButton = new JButton("x²");
        sqrtButton = new JButton("√");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = sqrButton;
        functionButtons[10] = sqrtButton;

        for (int i = 0; i < functionButtons.length; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        panel.add(sqrButton);
        panel.add(sqrtButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUIcalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Handle the number button presses
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    if (ErrorMessage) {
                        textfield.setText(""); // Clear the error message
                        ErrorMessage = false; // Reset the error
                    }
                    textfield.setText(textfield.getText().concat(String.valueOf(i)));
                    return; // Exit after handling the button press
                }
            }

            // Handle decimal point button
            if (e.getSource() == decButton) {
                // Prevent multiple decimals in a number
                if (!textfield.getText().contains(".")) {
                    textfield.setText(textfield.getText().concat("."));
                }
                return;
            }

            // Handle operator buttons
            if (e.getSource() == addButton) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '+';
                textfield.setText("");
            } else if (e.getSource() == subButton) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '-';
                textfield.setText("");
            } else if (e.getSource() == mulButton) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '*';
                textfield.setText("");
            } else if (e.getSource() == divButton) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '/';
                textfield.setText("");
            }

            // Handle equals button
            if (e.getSource() == equButton) {
                num2 = Double.parseDouble(textfield.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        result = num1 / num2;
                        break;
                }
                textfield.setText(String.valueOf(result));
                num1 = result;
            }

            // Handle clear button
            if (e.getSource() == clrButton) {
                textfield.setText("");
                ErrorMessage = false; // Reset error state
            }

            // Handle delete button
            if (e.getSource() == delButton) {
                String text = textfield.getText();
                if (text.length() > 0) {
                    textfield.setText(text.substring(0, text.length() - 1));
                }
            }

            // Handle negate button
            if (e.getSource() == negButton) {
                if (textfield.getText().length() > 0) {
                    double temp = Double.parseDouble(textfield.getText());
                    temp *= -1;
                    textfield.setText(String.valueOf(temp));
                }
            }

            // Handle square button
            if (e.getSource() == sqrButton) {
                try {
                    double value = Double.parseDouble(textfield.getText());
                    value = value * value;
                    textfield.setText(String.valueOf(value));
                } catch (NumberFormatException ex) {
                    textfield.setText("Please enter a number");
                    ErrorMessage = true; // Set error state
                }
            }

            // Handle square root button
            if (e.getSource() == sqrtButton) {
                try {
                    double value = Double.parseDouble(textfield.getText());
                    if (value < 0) {
                        throw new ArithmeticException("Cannot take the square root of a negative number");
                    }
                    value = Math.sqrt(value);
                    textfield.setText(String.valueOf(value));
                } catch (NumberFormatException ex) {
                    textfield.setText("Please enter a number");
                    ErrorMessage = true; // Set error state
                } catch (ArithmeticException ex) {
                    textfield.setText("Error: " + ex.getMessage());
                    ErrorMessage = true; // Set error state
                }
            }

        } catch (NumberFormatException ex) {
            textfield.setText("Invalid input");
            ErrorMessage = true; // Set error state
        } catch (ArithmeticException ex) {
            textfield.setText("Error: " + ex.getMessage());
            ErrorMessage = true; // Set error state
        }
    }
}
