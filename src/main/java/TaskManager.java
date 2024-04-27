/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManager extends JFrame implements ActionListener {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton completeButton;

    public TaskManager() {
        // Configuration de la fenêtre
        setTitle("Task Manager");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation du modèle de liste de tâches
        taskListModel = new DefaultListModel<>();

        // Initialisation de la liste de tâches
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Initialisation du champ de texte pour ajouter de nouvelles tâches
        taskField = new JTextField(20);

        // Initialisation des boutons
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        completeButton = new JButton("Complete");

        // Ajout des composants à la fenêtre
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Ajout des écouteurs d'événements aux boutons
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        completeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Ajouter une nouvelle tâche à la liste
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            }
        } else if (e.getSource() == deleteButton) {
            // Supprimer la tâche sélectionnée de la liste
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            }
        } else if (e.getSource() == completeButton) {
            // Marquer la tâche sélectionnée comme terminée
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                String task = taskListModel.getElementAt(selectedIndex);
                taskListModel.setElementAt("[DONE] " + task, selectedIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskManager taskManager = new TaskManager();
            taskManager.setVisible(true);
        });
    }
}
