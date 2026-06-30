import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boarderWidth = 600;
    int boarderHeigth = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");

    JLabel textLabel = new JLabel();

    JPanel textPanel = new JPanel();
    JPanel playPanel = new JPanel();

    JButton[][] buttons = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe(){
       
        frame.setSize(boarderWidth, boarderHeigth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);

        playPanel.setLayout(new GridLayout(3,3));
        playPanel.setBackground(Color.GRAY);

        for(int r=0; r<buttons.length; r++){
            for(int c=0; c<buttons[r].length; c++){
                JButton button = new JButton();
                //buttons[r][c] = button;
                button.setBackground(Color.darkGray);
                button.setForeground(Color.white);
                button.setFocusable(false);
                button.setFont(new Font("Arial", Font.BOLD, 100));

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         if(gameOver) return;
                        JButton button = (JButton) e.getSource();
                         
                        if(button.getText() == ""){
                            button.setText(currentPlayer); 
                                            
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                            
                        }
                        
                    }
                });

                
               
                playPanel.add(button);
            }
        }
        
        frame.setVisible(true);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(playPanel, BorderLayout.CENTER);
    }

}
