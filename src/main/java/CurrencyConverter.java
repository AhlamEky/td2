/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import javax.swing.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {
    // Déclaration des composants
    private JTextField amountField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    // Tableau de devises
    private String[] currencies = {"USD", "EUR", "GBP", "JPY"};

    // Taux de change (exemple)
    private double[][] exchangeRates = {
            {1.0, 0.85, 0.72, 110.32, 1.30},  // USD
            {1.18, 1.0, 0.85, 130.25, 1.54},   // EUR
            {1.39, 1.18, 1.0, 153.48, 1.81},   // GBP
            {0.0091, 0.0077, 0.0065, 1.0, 0.012}, // JPY
    };

    public CurrencyConverter() {
        // Configuration de la fenêtre
        setTitle("Currency Converter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des composants
        amountField = new JTextField(10);
        fromCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result:");

        // Ajout des composants à la fenêtre
        JPanel panel = new JPanel();
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("From:"));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("To:"));
        panel.add(toCurrencyComboBox);
        panel.add(convertButton);
        panel.add(resultLabel);
        add(panel);

        // Ajout d'un écouteur d'événements au bouton de conversion
        convertButton.addActionListener(this);
    }

    // Méthode pour effectuer la conversion de devises
    private double convertCurrency(double amount, int fromIndex, int toIndex) {
        double fromRate = exchangeRates[fromIndex][toIndex];
        double toRate = exchangeRates[toIndex][fromIndex];
        return amount * fromRate / toRate;
    }

    // Méthode pour gérer les événements de clic sur le bouton de conversion
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                // Récupérer le montant saisi par l'utilisateur
                double amount = Double.parseDouble(amountField.getText());
                
                // Récupérer l'index des devises sélectionnées dans les listes déroulantes
                int fromIndex = fromCurrencyComboBox.getSelectedIndex();
                int toIndex = toCurrencyComboBox.getSelectedIndex();

                // Effectuer la conversion
                double result = convertCurrency(amount, fromIndex, toIndex);

                // Afficher le résultat de la conversion
                resultLabel.setText("Result: " + String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
