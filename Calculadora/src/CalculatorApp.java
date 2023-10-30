import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CalculatorApp extends JFrame {
    private JTextField pantalla;
    private List<Double> numeros;
    private List<String> operadores;
    private Queue<Double> memoria = new LinkedList<>(); // Usar una cola para mantener los últimos 10 números

    public CalculatorApp() {
        pantalla = new JTextField(10);
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);

        numeros = new ArrayList<>();
        operadores = new ArrayList<>();

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
        JButton botonMemoria = new JButton("M+"); // Botón para guardar en memoria
        JButton botonAvg = new JButton("Avg"); // Botón para calcular el promedio
        JButton botonData = new JButton("Data"); // Botón para calcular el promedio

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 6));
        panelBotones.add(boton7);
        panelBotones.add(boton8);
        panelBotones.add(boton9);
        panelBotones.add(botonDivision);
        panelBotones.add(botonAvg); // Agrega el botón "Avg"
        panelBotones.add(boton4);
        panelBotones.add(boton5);
        panelBotones.add(boton6);
        panelBotones.add(botonMultiplicacion);  
        panelBotones.add(botonMemoria); // Agrega el botón "M+"     
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
                calcular();
            }
        });

        botonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.setText("");
                numeros.clear();
                operadores.clear();
            }
        });

        botonEsPrimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = pantalla.getText();
                if (esPrimo(Integer.parseInt(input))) {
                    pantalla.setText("True");
                } else {
                    pantalla.setText("False");
                }
            }
        });

        botonDecimalABinario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operacion = "Decimal a binario";
                String input = pantalla.getText();
                int decimal = Integer.parseInt(input);
                String binario = Integer.toBinaryString(decimal);
                pantalla.setText(binario);
                GuardarPrimoOBinario(operacion, decimal, binario);
            }
        });

        botonMemoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operacion = "M+";
                String input = pantalla.getText();
                double numero = Double.parseDouble(input);
                if (memoria.size() >= 10) {
                    memoria.poll(); // Elimina el elemento más antiguo si la memoria está llena
                }
                memoria.offer(numero); // Agrega el número a la memoria
                GuardarLista(operacion, numero);
            }
        });

        botonAvg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operacion = "Avg";
                double suma = 0.0;
                int count = 0;
                for (Double numero : memoria) {
                    suma += numero;
                    count++;
                }
                if (count > 0) {
                    double promedio = suma / count;
                    pantalla.setText(String.valueOf(promedio));
                    GuardarLista(operacion, promedio);
                }
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
            numeros.add(Double.parseDouble(pantalla.getText()));
            operadores.add(operacion);
            pantalla.setText("");
        }
    }

    private boolean esPrimo(int numero) {
        String operacion = "Es primo";
        if (numero <= 1) {
            GuardarPrimoOBinario(operacion, numero, "False");
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                GuardarPrimoOBinario(operacion, numero, "False");
                return false;
            }
        }
        GuardarPrimoOBinario(operacion, numero, "False");
        return true;
    }

        private void guardarEnBitacoraOp( String resultado) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true));
            for (int i = 0; i < numeros.size(); i++ ) {
                writer.write(String.valueOf(numeros.get(i)));
                if(i < (numeros.size()-1)){
                    writer.write(operadores.get(i));
                }                                           
            }
            writer.write("="); 
            writer.write(resultado); 
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void GuardarPrimoOBinario(String operacion, int numero, String resultado) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true));
            writer.write(operacion + " "); 
            writer.write(String.valueOf(numero + " ")); 
            writer.write(resultado); 
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void GuardarLista(String operacion, Double resultado) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true));
            writer.write(operacion + " ");
            if (operacion.equals("M+")){
                writer.write(String.valueOf(resultado)); 
                writer.write(" > "); 
                for (Double elemento : memoria) {
                    writer.write(String.valueOf(elemento) + " ");
                }
            }
            else{
                for (Double elemento : memoria) {
                    writer.write(String.valueOf(elemento + " "));                                          
                }
                writer.write(" = "); 
                writer.write(String.valueOf(resultado));

            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    private void calcular() {
        numeros.add(Double.parseDouble(pantalla.getText()));
        double resultado = numeros.get(0);
        int index = 0;

        for (String operador : operadores) {
            double siguienteNumero = numeros.get(index + 1);
            switch (operador) {
                case "+":
                    resultado += siguienteNumero;
                    break;
                case "-":
                    resultado -= siguienteNumero;
                    break;
                case "*":
                    resultado *= siguienteNumero;
                    break;
                case "/":
                    if (siguienteNumero != 0) {
                        resultado /= siguienteNumero;
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
            }
            index++;
        }
        pantalla.setText(String.valueOf(resultado));
        guardarEnBitacoraOp(String.valueOf(resultado));
        numeros.clear();
        operadores.clear();
    }

    public static void main(String[] args) {
        CalculatorApp calculadora = new CalculatorApp();
        calculadora.setSize(600, 700);
        calculadora.setTitle("Calculadora Simple");
        calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculadora.setVisible(true);
    }
}
