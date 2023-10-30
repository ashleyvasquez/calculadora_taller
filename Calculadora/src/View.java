import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTextField display;
    private JButton[] buttons;

    public View() {
        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            "Promedio", "Binario", "Primo"
        };

        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttonPanel.add(buttons[i]);
        }

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    public void displayApp() {
        frame.setVisible(true);
    }

    public void addButtonListener(ActionListener listener, int index) {
        buttons[index].addActionListener(listener);
    }

    public void updateDisplay(String text) {
        display.setText(text);
    }
}