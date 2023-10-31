import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class CalculatorView extends JFrame {
    private JTextField pantalla;
    private JButton boton0; 
    private JButton boton1; 
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private JButton botonPunto;
    private JButton botonSuma;
    private JButton botonResta;
    private JButton botonMultiplicacion;
    private JButton botonDivision;
    private JButton botonIgual;
    private JButton botonClear;
    private JButton botonEsPrimo;
    private JButton botonDecimalABinario;
    private JButton botonMemoria; 
    private JButton botonAvg; 
    private JButton botonData;    

    public CalculatorView() {
        pantalla = new JTextField(10);
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);

        boton0 = new JButton("0");
        boton1 = new JButton("1");
        boton2 = new JButton("2");
        boton3 = new JButton("3");
        boton4 = new JButton("4");
        boton5 = new JButton("5");
        boton6 = new JButton("6");
        boton7 = new JButton("7");
        boton8 = new JButton("8");
        boton9 = new JButton("9");
        botonPunto = new JButton(".");
        botonSuma = new JButton("+");
        botonResta = new JButton("-");
        botonMultiplicacion = new JButton("*");
        botonDivision = new JButton("/");
        botonIgual = new JButton("=");
        botonClear = new JButton("C");
        botonEsPrimo = new JButton("Es Primo");
        botonDecimalABinario = new JButton("Binario");
        botonMemoria = new JButton("M+"); 
        botonAvg = new JButton("Avg"); 
        botonData = new JButton("Data"); 

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 6));
        panelBotones.add(boton7);
        panelBotones.add(boton8);
        panelBotones.add(boton9);
        panelBotones.add(botonDivision);
        panelBotones.add(botonAvg); 
        panelBotones.add(boton4);
        panelBotones.add(boton5);
        panelBotones.add(boton6);
        panelBotones.add(botonMultiplicacion);  
        panelBotones.add(botonMemoria);     
        panelBotones.add(boton1);
        panelBotones.add(boton2);
        panelBotones.add(boton3);
        panelBotones.add(botonResta);
        panelBotones.add(botonData);
        panelBotones.add(boton0);
        panelBotones.add(botonClear);
        panelBotones.add(botonPunto);
        panelBotones.add(botonSuma);
        panelBotones.add(botonIgual);
        panelBotones.add(botonEsPrimo);
        panelBotones.add(botonDecimalABinario);
        
        add(pantalla, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }

    public void BotonNumeroListener(String number, ActionListener listener) {
        switch (number) {
            case"0":
                boton0.addActionListener(listener);
                break;
            case "1":
                boton1.addActionListener(listener);
                break;
            case "2":
                boton2.addActionListener(listener);
                break;
            case "3":
                boton3.addActionListener(listener);
                break;
            case "4":
                boton4.addActionListener(listener);
                break;
            case "5":
                boton5.addActionListener(listener);
                break;
            case "6":
                boton6.addActionListener(listener);
                break;
            case "7":
                boton7.addActionListener(listener);
                break;
            case "8":
                boton8.addActionListener(listener);
                break;
            case "9":
                boton9.addActionListener(listener);
                break;
            case ".":
                botonPunto.addActionListener(listener);
                break;
        }
    }

    public void BotonOperacionListener(String operacion, ActionListener listener) {
        switch(operacion){
            case"+":
                botonSuma.addActionListener(listener);
                break;
            case"-":
                botonResta.addActionListener(listener); 
                break; 
            case"*":
                botonMultiplicacion.addActionListener(listener);
                break;
            case"/":
                botonDivision.addActionListener(listener); 
                break;   
        }
    }

    public void BotonIgualListener(ActionListener listener) {
        botonIgual.addActionListener(listener);
    }

    public void BotonClearListener(ActionListener listener) {
        botonClear.addActionListener(listener);
    }

    public void BotonPrimoListener(ActionListener listener) {
        botonEsPrimo.addActionListener(listener);
    }

    public void BotonBinarioListener(ActionListener listener) {
        botonDecimalABinario.addActionListener(listener);
    }

    public void BotonMemoriaListener(ActionListener listener) {
        botonMemoria.addActionListener(listener);
    }

    public void BotonAvgListener(ActionListener listener) {
        botonAvg.addActionListener(listener);
    }

    public void BotonDataListener(ActionListener listener) {
        botonData.addActionListener(listener);
    }

    public void setDisplayText(String text) {
        pantalla.setText(text);
    }

    public String getDisplayText() {
        return pantalla.getText();
    }


    public void mostrarBitacora() {
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Bitacora.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line);
                textArea.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame frame = new JFrame("Bitácora");
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
