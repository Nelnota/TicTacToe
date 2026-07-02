import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class TicTacToe {
    int boarderWidth = 600;
    int boarderHeigth = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");

    JLabel textLabel = new JLabel();
    JLabel xWinLabel;
	JLabel oWinLabel;

    JPanel textPanel = new JPanel();
    JPanel playPanel = new JPanel();
    JPanel restartWinPanel;
    JPanel xoPanel;
    JPanel xxPanel;
    
	 	 
	JButton restart;

    JButton[][] buttons = new JButton[3][3];
    List<JButton> buttonsList;
    

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    int turns = 0;
	int xWin = 0;
	int oWin = 0;

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

        restartWinPanel = new JPanel();
		restart = new JButton("Restart");

        xoPanel = new JPanel();
        xoPanel.setBackground(Color.LIGHT_GRAY);
        xxPanel = new JPanel();
        xxPanel.setBackground(Color.LIGHT_GRAY);
		
		xWinLabel = new JLabel("X's Win: " + Integer.toString(xWin));
		xWinLabel.setForeground(Color.black);
		xWinLabel.setBackground(Color.DARK_GRAY);
		xWinLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		
		oWinLabel = new JLabel("O's Win: " + Integer.toString(oWin));
		oWinLabel.setForeground(Color.black);
		oWinLabel.setBackground(Color.DARK_GRAY);
		oWinLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        xxPanel.add(xWinLabel);
        xoPanel.add(oWinLabel);
		
		restartWinPanel.setLayout(new BorderLayout());
        restartWinPanel.setBackground(Color.LIGHT_GRAY);
		restartWinPanel.add(restart, BorderLayout.CENTER);
		restartWinPanel.add(xxPanel, BorderLayout.WEST);
		restartWinPanel.add(xoPanel, BorderLayout.EAST);

        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                JButton button = new JButton();
                buttons[r][c] = button;
                button.setBackground(Color.darkGray);
                button.setForeground(Color.white);
                button.setFocusable(false);
                button.setFont(new Font("Arial", Font.BOLD, 100));

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                         if(gameOver) return;
                        JButton button = (JButton) e.getSource();
                         
                        if(button.getText() == ""){
                            turns++;
                            button.setText(currentPlayer);
                            checkWinner(); 
                                            
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

        // Restart the game
		restart.setFocusable(false);
		restart.setBackground(Color.LIGHT_GRAY);
		restart.setForeground(Color.red);
		restart.setFont(new Font("Arial",Font.BOLD, 20));
        
		
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textLabel.setText("Jogo da Velha");
				for(JButton[] buttonss : buttons)
				for(JButton button : buttonss) {
					button.setBackground(Color.darkGray);
					button.setForeground(Color.white);
					button.setText("");
					currentPlayer = playerX;
					gameOver = false;
					turns = 0;
					
					
				}

			}
		});
        
        
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(playPanel, BorderLayout.CENTER);
        frame.add(restartWinPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    void checkWinner(){
        for(int i = 0; i<buttons.length; i++) {
			for(int j=0; j<buttons.length; j++) {

				if(buttons[i][0].getText() == "") continue; //horizontal
				if(buttons[0][j].getText() == "") continue; //vertical
				if(buttons[1][1].getText() == "") continue; //Anti diagonal


				if(buttons[i][0].getText() == buttons[i][1].getText() &&
						buttons[i][0].getText() == buttons[i][2].getText()) {
					buttonsList = List.of(buttons[i][0],buttons[i][1], buttons[i][2]);
					setWinner(buttonsList);
					gameOver = true;
					
					PlayerWin();		
					
					return;
				}

				if(buttons[0][j].getText() == buttons[1][j].getText() &&
						buttons[0][j].getText() == buttons[2][j].getText()) {
					buttonsList = List.of(buttons[0][j],buttons[1][j], buttons[2][j]);
					setWinner(buttonsList);
					gameOver = true;
					
					PlayerWin();	
					
					return;
				}
				
				if(buttons[0][0].getText() == buttons[1][1].getText() &&
						buttons[0][0].getText() == buttons[2][2].getText()) {
					buttonsList = List.of(buttons[0][0],buttons[1][1], buttons[2][2]);
					setWinner(buttonsList);
					gameOver = true;
					
					PlayerWin();	
					
					return;
				}
				
				if(buttons[0][2].getText() == buttons[1][1].getText() &&
						buttons[0][2].getText() == buttons[2][0].getText()) {
					buttonsList = List.of(buttons[0][2],buttons[1][1], buttons[2][0]);
					setWinner(buttonsList);
					gameOver = true;
					
					PlayerWin();	
					
					return;
				}
				
				// Tie
				
				if(turns == 9) {
					tie();
					gameOver = true;
				} 
				
				
			}

		}
	}

    void setWinner(List<JButton> buttons) {
		for(JButton button : buttons) {
			button.setForeground(Color.CYAN);
			button.setBackground(Color.black);
			textLabel.setText("Player " + currentPlayer + " won");
			
		}
		
	}

    void tie() {
		for(int i=0; i<buttons.length; i++) {
			for(int j=0; j<buttons.length; j++) {
				buttons[i][j].setForeground(Color.LIGHT_GRAY);
				textLabel.setText("Tie");
			}
		}
	}

    void PlayerWin() {
		
		if(currentPlayer == playerX && turns<=9) {
			xWin++;
			xWinLabel.setText("X's Win: " + Integer.toString(xWin));
		}else {
			oWin++;
			oWinLabel.setText("O's Win: " + Integer.toString(oWin));
		}
	}
    }
        

