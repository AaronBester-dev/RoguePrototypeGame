package rogue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;


public class TextUI extends JFrame {
   private SwingTerminal terminal;
   private static Rogue theGame;
   public static final int WIDTH = 700;
   public static final int HEIGHT = 800;
   public static final int COLS = 80;
   public static final int ROWS = 24;
   private static final int TEXTSIZE = 50;
   private static final int PLAYERTEXTSIZE = 10;
   private static final int INVENTORYY = 100;
   private static final int INVENTORYX = 20;
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
      setUpMenuBar();
      pack();
      start();
    }

    private void setWindowDefaults(Container newContentPane) {
        setTitle("Rogue!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newContentPane.setLayout(new BorderLayout());
    }

    private void setUpMenuBar() {
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("File");
      menuBar.add(fileMenu);
      JMenuItem saveFile = new JMenuItem("Save");
      saveFile.addActionListener(ev -> save());
      fileMenu.add(saveFile);
      JMenuItem loadFile = new JMenuItem("Load");
      loadFile.addActionListener(ev -> loadGameWithBrowser());
      fileMenu.add(loadFile);
      JMenuItem changePlayerName = new JMenuItem("Change Player Name");
      changePlayerName.addActionListener(ev -> changePlayerName());
      fileMenu.add(changePlayerName);
      JMenuItem loadMap = new JMenuItem("Load New Map");
      loadMap.addActionListener(ev -> changeJsonFile());
      fileMenu.add(loadMap);
    }

     private void setTerminal() {
        JPanel terminalPanel = new JPanel();
        terminal = new SwingTerminal();
        terminalPanel.add(terminal);
        contentPane.add(terminalPanel, BorderLayout.CENTER);
    }

    private void setUpPanels() {
        JPanel textPanel = new JPanel();
        JPanel inventoryPanel = new JPanel();
        setUpTextPanel(textPanel);
        setUpInventoryPanel(inventoryPanel);
        setTerminal();
    }

    private void setUpTextPanel(JPanel thePanel) {
        thePanel.setLayout(new FlowLayout());
        Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        thePanel.setBorder(prettyLine);
        JTextField playerName = new JTextField("Name", PLAYERTEXTSIZE);
        playerName.setHorizontalAlignment(JTextField.CENTER);
        playerName.setEditable(false);
        thePanel.add(playerName);
        JTextField message = new JTextField("Welcome To My Rogue Game", TEXTSIZE);
        message.setHorizontalAlignment(JTextField.CENTER);
        message.setEditable(false);
        thePanel.add(message);
        contentPane.add(thePanel, BorderLayout.NORTH);
    }

    private void setUpInventoryPanel(JPanel thePanel) {
      JTextArea inventoryText = new JTextArea(INVENTORYY, INVENTORYX);
      inventoryText.setEditable(false);
      thePanel.add(inventoryText);
      contentPane.add(thePanel, BorderLayout.EAST);
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
*Changes message in the message label.
*@param message message you want to add to the label
*/
    public void changeMessage(String message) {
      JPanel messagePanel = (JPanel) contentPane.getComponent(0);
      JTextField messageText = (JTextField) messagePanel.getComponent(1);
      messageText.setText(message);
    }

/**
*Changes player name in the player text field.
*/
    public void changePlayerName() {
      String newName = getPlayerName();
      JPanel messagePanel = (JPanel) contentPane.getComponent(0);
      JTextField nameText = (JTextField) messagePanel.getComponent(0);
      nameText.setText(newName);
      theGame.getPlayer().setName(newName);
    }

    private String getPlayerName() {
      String name = "";
      JOptionPane namePane = new JOptionPane();
      name = namePane.showInputDialog("Enter your name.");
      return name;
    }


        /**
*Changes inventory text in the inventory text field.
*@param newInventory new inventory string.
*/
    public void changeInventoryText(String newInventory) {
      JPanel inventoryPanel = (JPanel) contentPane.getComponent(1);
      JTextArea inventoryText = (JTextArea) inventoryPanel.getComponent(0);
      inventoryText.setText(newInventory);
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
      while (keyStroke == null) {
        try {
          keyStroke = screen.pollInput();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return getInputPart2(keyStroke);
    }

    private char getInputPart2(KeyStroke keyStroke) {
      char returnChar;
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
* Saves a rogue object as a binary file.
*/
    public void save() {
      JFrame saveFrame = new JFrame();
      JFileChooser fileBrowser = new JFileChooser();
      int returnValue = fileBrowser.showSaveDialog(saveFrame);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        String filename = fileBrowser.getSelectedFile().toString();
        try {
          //Saving of object in a file
          FileOutputStream outPutStream = new FileOutputStream(filename);
          ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream);
          // Method for serialization of object
          outPutDest.writeObject(theGame);
          outPutDest.close();
          outPutStream.close();
        } catch (IOException ex) {
          showErrorMessage("Error occurred while saving.", "Save Error");
        }
      }
    }

    private void loadGameWithBrowser() {
      JFrame loadFrame = new JFrame();
      JFileChooser fileBrowser = new JFileChooser();
      int returnValue = fileBrowser.showOpenDialog(loadFrame);
      String filename = "";
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        filename = fileBrowser.getSelectedFile().toString();
        loadGame(filename);
      }
      draw("Loaded " + filename, theGame.getNextDisplay());
      changeMessage("Loaded " + filename);
      changeInventoryText(theGame.getInventoryString());
    }

    private void showErrorMessage(String errorMessage, String errorTitle) {
      JPanel errorPanel = new JPanel();
      JOptionPane errorPane = new JOptionPane();
      errorPane.showMessageDialog(errorPanel, errorMessage, errorTitle, JOptionPane.WARNING_MESSAGE);
    }

/**
* Loads a binary file containing the rogue object.
* @param filename name of file containing the saved rogue object.
*/
    public void loadGame(String filename) {
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)); ) {
             // Method for deserialization of object
            theGame = (Rogue) in.readObject();   //we're casting it to the object we know it is
        } catch (IOException ex) {
          showErrorMessage("Error occurred while loading.", "Load Error");
        } catch (ClassNotFoundException ex) {
          showErrorMessage("Error occurred while loading.", "Load Error");
        }
    }

    private void changeJsonFile() {
      JFrame loadFrame = new JFrame();
      JFileChooser fileBrowser = new JFileChooser();
      fileBrowser.setFileFilter(new FileNameExtensionFilter("json", "json"));
      int returnValue = fileBrowser.showOpenDialog(loadFrame);
      String filename = "";
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        filename = fileBrowser.getSelectedFile().toString();
        try {
          theGame = new Rogue(new RogueParser("fileLocations.json", filename));
        } catch (Exception e) {
          showErrorMessage("Incompatible Json file.", "Json Load Error");
        }
      }
      draw("Loaded " + filename, theGame.getNextDisplay());
      changeMessage("Loaded " + filename);
      changeInventoryText(theGame.getInventoryString());
    }

    private void sayByeBye() {
      clearDisplay();
      draw("Goodbye! Hope you had fun!", "");
      changeMessage("Goodbye! Hope you had fun!");
    }

    private void getUserInput(Room oldRoom) {
      char userInput = 'h';
      while (userInput != 'q') {
        userInput = getInput();
        if (userInput == 'w' || userInput == 'e' || userInput == 't') {
          moveInventory(userInput);
        } else {
          oldRoom = movePlayer(oldRoom, userInput);
        }
      }
    }

    private void moveInventory(char userInput) {
      String message = "";
      theGame.openInventoryPanel(userInput);
      clearDisplay();
      draw("Select item to use.", theGame.getNextDisplay());
      changeMessage("Select item to use.");
      while (userInput != 'i') {
        userInput = getInput();
        theGame.moveThroughInventoryPanel(userInput);
        draw("Select item to use.", theGame.getNextDisplay());
        changeMessage("Select item to use.");
      }
      message = theGame.useCurrentItem();
      clearDisplay();
      draw(message, theGame.getNextDisplay());
      changeMessage(message);
      changeInventoryText(theGame.getInventoryString());
    }

    private Room movePlayer(Room oldRoom, char userInput) {
      String message = "";
      try {
        message = theGame.makeMove(userInput);
        /*oldRoom is used to check if the player has moved rooms and then clears the display if they have*/
        if (oldRoom != theGame.getPlayer().getCurrentRoom()) {
          clearDisplay();
        }
        draw(message, theGame.getNextDisplay());
        changeInventoryText(theGame.getInventoryString());
        changeMessage(message);
      } catch (InvalidMoveException badMove) {
        message = "I didn't understand what you meant, please enter a command";
        setMessage(message);
        changeMessage(message);
      }
      oldRoom = theGame.getPlayer().getCurrentRoom();
      return oldRoom;
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
      theGame = new Rogue(parser);
      theGameUI.changePlayerName();
      oldRoom = theGame.getPlayer().getCurrentRoom();
      if (oldRoom == null) {
        theGameUI.programExitError();
      }
      message = "Welcome to my Rogue game";
      theGameUI.changeMessage(message);
      theGameUI.draw(message, theGame.getNextDisplay());
      theGameUI.setVisible(true);
      theGameUI.getUserInput(oldRoom);
      theGameUI.sayByeBye();
    }
}
