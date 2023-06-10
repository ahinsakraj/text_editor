import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TextEditor implements ActionListener {

    // Declaring properties of Text Editor
    JFrame frame;

    JMenuBar menuBar;
    
    JTextArea textArea;

    JMenu file, edit;

    // File menu items
    JMenuItem newFile, saveFile, openFile;

    // Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    TextEditor() {
        // Initialising a frame
        frame = new JFrame(null, null);

        // Initialising a menubar
        menuBar = new JMenuBar();

        //Initialising text area
        textArea = new JTextArea();
        // Initialise menu items
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialise file menu items
        newFile = new JMenuItem("New File");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");

        // Add action listeners to file menu items
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);

        // Add menu items to file menu
        file.add(newFile);
        file.add(saveFile);
        file.add(openFile);

        // Initialise edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add actionListeners to edti menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        // Set menubar to frame
        frame.setJMenuBar(menuBar);

        // Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));

        // Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollPane);
        // Add panel to frame
        frame.add(panel);

        // Set dimensions of frame
        frame.setBounds(200, 100, 400, 400);
        frame.setTitle("Text Editor by Ahinsak Raj");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cut) {
            textArea.cut();
        } else if(actionEvent.getSource() == copy) {
            textArea.copy();
        } else if(actionEvent.getSource() == paste) {
            textArea.paste();
        } else if(actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        } else if(actionEvent.getSource() == close) {
            // Perform close editor Operation
            System.exit(0);
        } else if(actionEvent.getSource() == openFile) {
            // Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // If we have clicked on Open buttton
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                // Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of selected file
                String filePath = file.getPath();
                try {
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // Initialize buffered reader
                    BufferedReader br = new BufferedReader(fileReader);
                    
                    String intermediate = "", output = "";
                    // Read content of file line by line
                    while((intermediate = br.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    // Set the output string to text area
                    textArea.setText(output);
                    br.close();
                } catch(FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else if(actionEvent.getSource() == saveFile) {
            // Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                // Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffered writer
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    // write contents of text area to file
                    textArea.write(bw);
                    bw.close();
                } catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else if(actionEvent.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}