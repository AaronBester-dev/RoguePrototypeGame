package rogue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;


public class TextUI extends JFrame {
   private SwingTerminal terminal;
   public static final int WIDTH = 700;
   public static final int HEIGHT = 800;
   public static final int COLS = 80;
   public static final int ROWS = 24;
   private TerminalScreen screen;
   private final char startCol = 0;
   private final char msgRow = 1;
   private final char roomRow = 3;
   private static final int SLEEPTIME = 2000;
   private Container contentPane;

/**
Constructor for TextUI class.  Creates the screens, sets
cursor to top left corner and does nothing else.
*/
    public TextUI() {
      super("my awesome game");
      contentPane = getContentPane();
      setWindowDefaults(getContentPane());
      setUpPanels();
      pack();
      start();
    }

    private void setWindowDefaults(Container contentPanel) {
        setTitle("Rogue!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPanel.setLayout(new BorderLayout());

    }

     private void setTerminal() {
        JPanel terminalPanel = new JPanel();
        terminal = new SwingTerminal();
        terminalPanel.add(terminal);
        contentPane.add(terminalPanel, BorderLayout.CENTER);
    }

    private void setUpPanels() {
        JPanel labelPanel = new JPanel();
        setUpLabelPanel(labelPanel);
        setTerminal();
    }

    private void setUpLabelPanel(JPanel thePanel) {
        Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        thePanel.setBorder(prettyLine);
        JLabel exampleLabel = new JLabel("Tomorrow and tomorrow and tomorrow");
        thePanel.add(exampleLabel);
        JTextField dataEntry = new JTextField("Enter text here", ROWS);
        thePanel.add(dataEntry);
        JButton clickMe = new JButton("Click Me");
        thePanel.add(clickMe);
        contentPane.add(thePanel, BorderLayout.SOUTH);
    }

     private void start() {
        try {
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(TerminalPosition.TOP_LEFT_CORNER);
            screen.startScreen();
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**
Prints a string to the screen starting at the indicated column and row.
@param toDisplay the string to be printed
@param column the column in which to start the display
@param row the row in which to start the display
*/
    public void putString(String toDisplay, int column, int row) {
      Terminal t = screen.getTerminal();
      try {
        t.setCursorPosition(column, row);
        for (char ch: toDisplay.toCharArray()) {
          t.putCharacter(ch);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

/**
Changes the message at the top of the screen for the user.
@param msg the message to be displayed
*/
    public void setMessage(String msg) {
      putString("                                                ", 1, 1);
      putString(msg, startCol, msgRow);
    }

/**
Redraws the whole screen including the room and the message.
@param message the message to be displayed at the top of the room
@param room the room map to be drawn
*/
    public void draw(String message, String room) {
      try {
        setMessage(message);
        putString(room, startCol, roomRow);
        screen.refresh();
      } catch (IOException e) {

      }

    }

/**
Obtains input from the user and returns it as a char.  Converts arrow
keys to the equivalent movement keys in rogue.
@return the ascii value of the key pressed by the user
*/
    public char getInput() {
      KeyStroke keyStroke = null;
      char returnChar;
      while (keyStroke == null) {
        try {
          keyStroke = screen.pollInput();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (keyStroke.getKeyType() == KeyType.ArrowDown) {
        returnChar = Rogue.DOWN;  //constant defined in rogue
      } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
        returnChar = Rogue.UP;
      } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
        returnChar = Rogue.LEFT;
      } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
        returnChar = Rogue.RIGHT;
      } else {
        returnChar = keyStroke.getCharacter();
      }
      return returnChar;
    }

/**
*Clears the entire display.
*/
    public void clearDisplay() {
      screen.clear();
      try {
        screen.refresh();
      } catch (IOException p) {
        p.printStackTrace();
      }

    }
/**
*Says goodbye to user.
*/
    public void programExitError() {
      this.clearDisplay();
      this.draw("Error: Dungeon file doesn't work.", "");
      try {
        Thread.sleep(SLEEPTIME);
      } catch (InterruptedException q) {
        q.printStackTrace();
      }
      System.exit(-1);
    }


/**
the main method.
@param args command line arguments are unused at this point.
*/

    public static void main(String[] args) {
      char userInput = 'h';
      String message;
      Room oldRoom = null;
      String configurationFileLocation = "fileLocations.json";
      RogueParser parser = new RogueParser(configurationFileLocation);
      TextUI theGameUI = new TextUI();
      Rogue theGame = new Rogue(parser);
      oldRoom = theGame.getPlayer().getCurrentRoom();
      if (oldRoom == null) {
        theGameUI.programExitError();
      }
      message = "Welcome to my Rogue game";
      theGameUI.draw(message, theGame.getNextDisplay());
      theGameUI.setVisible(true);
      while (userInput != 'q') {
        userInput = theGameUI.getInput();
        if (userInput == 'w' || userInput == 'e' || userInput == 't') {
          theGame.openInventoryPanel(userInput);
          theGameUI.clearDisplay();
          theGameUI.draw("Select item to use.", theGame.getNextDisplay());
          while (userInput != 'i') {
            userInput = theGameUI.getInput();
            theGame.moveThroughInventoryPanel(userInput);
            theGameUI.draw("Select item to use.", theGame.getNextDisplay());
          }
          message = theGame.useCurrentItem();
          theGameUI.clearDisplay();
          theGameUI.draw(message, theGame.getNextDisplay());
        } else {
          try {
          message = theGame.makeMove(userInput);
          /*oldRoom is used to check if the player has moved rooms and then clears the display if they have*/
          if (oldRoom != theGame.getPlayer().getCurrentRoom()) {
            theGameUI.clearDisplay();
          }
          theGameUI.draw(message, theGame.getNextDisplay());
        } catch (InvalidMoveException badMove) {
          message = "I didn't understand what you meant, please enter a command";
          theGameUI.setMessage(message);
        }
        oldRoom = theGame.getPlayer().getCurrentRoom();
        }
      }
      theGameUI.clearDisplay();
      theGameUI.draw("Goodbye! Hope you had fun!", "");
      try {
        Thread.sleep(SLEEPTIME);
      } catch (InterruptedException q) {
        q.printStackTrace();
      }
    }
}
