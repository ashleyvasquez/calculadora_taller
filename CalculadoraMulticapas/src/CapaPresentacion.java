import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CapaPresentacion extends JFrame {
    private CapaNegocio negocio;
    private CapaDatos datos;
    private JTextField pantalla;

    public CapaPresentacion(CapaNegocio negocio, CapaDatos datos) {
        this.negocio = negocio;
        this.datos = datos;
        pantalla = new JTextField(10);
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);

        JButton boton0 = new JButton("0");
        JButton boton1 = new JButton("1");
        JButton boton2 = new JButton("2");
        JButton boton3 = new JButton("3");
        JButton boton4 = new JButton("4");
        JButton boton5 = new JButton("5");
        JButton boton6 = new JButton("6");
        JButton boton7 = new JButton("7");
        JButton boton8 = new JButton("8");
        JButton boton9 = new JButton("9");
        JButton botonPunto = new JButton(".");
        JButton botonSuma = new JButton("+");
        JButton botonResta = new JButton("-");
        JButton botonMultiplicacion = new JButton("*");
        JButton botonDivision = new JButton("/");
        JButton botonIgual = new JButton("=");
        JButton botonClear = new JButton("C");
        JButton botonEsPrimo = new JButton("Es Primo");
        JButton botonDecimalABinario = new JButton("Binario");
        JButton botonMemoria = new JButton("M+"); 
        JButton botonAvg = new JButton("Avg"); 
        JButton botonData = new JButton("Data"); 

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

        boton0.addActionListener(new BotonNumeroListener("0"));
        boton1.addActionListener(new BotonNumeroListener("1"));
        boton2.addActionListener(new BotonNumeroListener("2"));
        boton3.addActionListener(new BotonNumeroListener("3"));
        boton4.addActionListener(new BotonNumeroListener("4"));
        boton5.addActionListener(new BotonNumeroListener("5"));
        boton6.addActionListener(new BotonNumeroListener("6"));
        boton7.addActionListener(new BotonNumeroListener("7"));
        boton8.addActionListener(new BotonNumeroListener("8"));
        boton9.addActionListener(new BotonNumeroListener("9"));
        botonPunto.addActionListener(new BotonNumeroListener("."));

        botonSuma.addActionListener(new BotonOperacionListener("+"));
        botonResta.addActionListener(new BotonOperacionListener("-"));
        botonMultiplicacion.addActionListener(new BotonOperacionListener("*"));
        botonDivision.addActionListener(new BotonOperacionListener("/"));

        
        botonIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = Double.toString(negocio.calcular(pantalla.getText()));
                pantalla.setText(resultado);
            }
        });

        botonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.setText("");
                negocio.clear();

            }
        });

        botonEsPrimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = pantalla.getText();
                if (negocio.esPrimo(Integer.parseInt(input))) {
                    pantalla.setText("True");
                } else {
                    pantalla.setText("False");
                }
            }
        });

        botonDecimalABinario.addActionListener(new ActionListener() { //agregar capa de datos
            @Override
            public void actionPerformed(ActionEvent e) {
                String operacion = "Decimal a binario";
                String input = pantalla.getText();
                int decimal = Integer.parseInt(input);
                String binario = Integer.toBinaryString(decimal);
                pantalla.setText(binario);
                datos.GuardarPrimoOBinario(operacion, decimal, binario);
            }
        });

        botonMemoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = pantalla.getText();
                double numero = Double.parseDouble(input);
                negocio.GuardarEnMemoria(numero);

            }
        });

        botonAvg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = Double.toString(negocio.CalcularPromedio());
                pantalla.setText(String.valueOf(resultado));

            }
        });

        botonData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarBitacora();
            }
        });

        add(pantalla, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }

    private class BotonNumeroListener implements ActionListener {
        private String numero;

        public BotonNumeroListener(String numero) {
            this.numero = numero;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pantalla.getText().equals("True") || pantalla.getText().equals("False")) {
                pantalla.setText(numero);
            } else {
                pantalla.setText(pantalla.getText() + numero);
            }
        }
    }

    private class BotonOperacionListener implements ActionListener {
        private String operacion;

        public BotonOperacionListener(String operacion) {
            this.operacion = operacion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            negocio.getNumeros().add(Double.parseDouble(pantalla.getText()));
            negocio.getOperadores().add(operacion);
            pantalla.setText("");
        }
    }

    private void mostrarBitacora() {
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