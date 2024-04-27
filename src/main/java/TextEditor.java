/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JTextField searchField, replaceField;
    private JCheckBox boldCheckBox, italicCheckBox, underlineCheckBox;

    public TextEditor() {
        setTitle("Éditeur de texte");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création de la barre de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem openMenuItem = new JMenuItem("Ouvrir");
        JMenuItem saveMenuItem = new JMenuItem("Enregistrer");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Édition");
        JMenuItem searchMenuItem = new JMenuItem("Rechercher");
        JMenuItem replaceMenuItem = new JMenuItem("Remplacer");
        editMenu.add(searchMenuItem);
        editMenu.add(replaceMenuItem);
        menuBar.add(editMenu);

        JMenu styleMenu = new JMenu("Style");
        menuBar.add(styleMenu);

        setJMenuBar(menuBar);

        // Création de la zone de texte
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Création des champs de saisie pour la recherche et le remplacement
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        JLabel searchLabel = new JLabel("Rechercher:");
        searchField = new JTextField(20);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        JLabel replaceLabel = new JLabel("Remplacer:");
        replaceField = new JTextField(20);
        searchPanel.add(replaceLabel);
        searchPanel.add(replaceField);

        getContentPane().add(searchPanel, BorderLayout.NORTH);

        // Création des boutons pour la mise en forme du texte
        JPanel stylePanel = new JPanel();
        stylePanel.setLayout(new FlowLayout());
        boldCheckBox = new JCheckBox("Gras");
        italicCheckBox = new JCheckBox("Italique");
        underlineCheckBox = new JCheckBox("Souligné");
        stylePanel.add(boldCheckBox);
        stylePanel.add(italicCheckBox);
        stylePanel.add(underlineCheckBox);

        getContentPane().add(stylePanel, BorderLayout.SOUTH);

        // Ajout des gestionnaires d'événements
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        searchMenuItem.addActionListener(this);
        replaceMenuItem.addActionListener(this);
        boldCheckBox.addActionListener(this);
        italicCheckBox.addActionListener(this);
        underlineCheckBox.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Gestion des événements des menus
        String command = e.getActionCommand();
        if (command.equals("Ouvrir")) {
            openFile();
        } else if (command.equals("Enregistrer")) {
            saveFile();
        } else if (command.equals("Rechercher")) {
            search();
        } else if (command.equals("Remplacer")) {
            replace();
        }
        // Gestion des événements des boutons de style
        else if (e.getSource() instanceof JCheckBox) {
            updateStyle();
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                textArea.write(writer);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void search() {
        String searchText = searchField.getText();
        String text = textArea.getText();
        int index = text.indexOf(searchText);
        if (index != -1) {
            textArea.setCaretPosition(index);
            textArea.setSelectionStart(index);
            textArea.setSelectionEnd(index + searchText.length());
        } else {
            JOptionPane.showMessageDialog(this, "Texte non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void replace() {
        String searchText = searchField.getText();
        String replaceText = replaceField.getText();
        String text = textArea.getText();
        text = text.replace(searchText, replaceText);
        textArea.setText(text);
    }

    private void updateStyle() {
        int style = Font.PLAIN;
        if (boldCheckBox.isSelected()) {
            style |= Font.BOLD;
        }
        if (italicCheckBox.isSelected()) {
            style |= Font.ITALIC;
        }
        Font font = textArea.getFont();
        textArea.setFont(font.deriveFont(style));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditor editor = new TextEditor();
            editor.setVisible(true);
        });
    }
}
