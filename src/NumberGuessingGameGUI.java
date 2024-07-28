import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class NumberGuessingGame 
{
    private int targetNumber;
    private int guessCount;

    public NumberGuessingGame() 
    {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        guessCount = 0;
    }

    public int getTargetNumber()
    {
        return targetNumber;
    }

    public int getGuessCount() 
    {
        return guessCount;
    }

    public void incrementGuessCount()
    {
        guessCount++;
    }
}

public class NumberGuessingGameGUI extends JFrame
{
    private NumberGuessingGame game;
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel countLabel;

    public NumberGuessingGameGUI() {
        game = new NumberGuessingGame();
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel instructionsLabel = new JLabel("Guess a number between 1 and 100:");
        panel.add(instructionsLabel);

        guessField = new JTextField();
        panel.add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        panel.add(guessButton);

        messageLabel = new JLabel(" ");
        panel.add(messageLabel);

        countLabel = new JLabel("Guesses: 0");
        panel.add(countLabel);

        add(panel);
    }

    private class GuessButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                game.incrementGuessCount();

                if (userGuess == game.getTargetNumber()) 
                {
                    messageLabel.setText("Congratulations! You've guessed the correct number.");
                } 
                else if (userGuess > game.getTargetNumber()) 
                {
                    messageLabel.setText("Your guess is too high. Try again.");
                } 
                else 
                {
                    messageLabel.setText("Your guess is too low. Try again.");
                }

                countLabel.setText("Guesses: " + game.getGuessCount());
            } 
            catch (NumberFormatException ex)
            {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI frame = new NumberGuessingGameGUI();
            frame.setVisible(true);
        });
    }
}
