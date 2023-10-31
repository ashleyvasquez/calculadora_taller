import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CalculatorModel {
    private List<Double> numeros;
    private List<String> operadores;
    private Queue<Double> memoria;

    public CalculatorModel() {
        numeros = new ArrayList<>();
        operadores = new ArrayList<>();
        memoria = new LinkedList<>();
    }

    public void addNumero(double numero) {
        numeros.add(numero);
    }

    public void addOperador(String operador) {
        operadores.add(operador);
    }

    public List<Double> getNumeros() {
        return numeros;
    }

    public List<String> getOperadores() {
        return operadores;
    }

    public Queue<Double> getMemoria() {
        return memoria;
    }

    public void clear() {
        numeros.clear();
        operadores.clear();
    }

    public void guardarEnBitacoraOp(String resultado) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true));
            for (int i = 0; i < numeros.size(); i++) {
                writer.write(String.valueOf(numeros.get(i)));
                if (i < (numeros.size() - 1)) {
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

    public void GuardarPrimoOBinario(String operacion, int numero, String resultado) {
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

    public void GuardarLista(String operacion, Double resultado) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Bitacora.txt", true));
            writer.write(operacion + " ");
            if (operacion.equals("M+")) {
                writer.write(String.valueOf(resultado));
                writer.write(" > ");
                for (Double elemento : memoria) {
                    writer.write(String.valueOf(elemento) + " ");
                }
            } else {
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

    public void GuardarEnMemoria(double numero) {
        String operacion = "M+";
        if (memoria.size() >= 10) {
            memoria.poll(); // Elimina el elemento más antiguo si la memoria está llena
        }
        memoria.offer(numero); // Agrega el número a la memoria
        GuardarLista(operacion, numero);
    }

    public Double CalcularPromedio() {
        String operacion = "Avg";
        double suma = 0.0;
        int count = 0;
        double promedio;
        for (Double numero : memoria) {
            suma += numero;
            count++;
        }
        if (count > 0) {
            promedio = suma / count;
            GuardarLista(operacion, promedio);
            return promedio;
        }
        else{
            return 0.0;
        }

    }

    public boolean esPrimo(int numero) {
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

    public Double calcular(String text) {
        getNumeros().add(Double.parseDouble(text));
        double resultado = getNumeros().get(0);
        int index = 0;
    
        for (String operador : getOperadores()) {
            double siguienteNumero = getNumeros().get(index + 1);
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
                    }
                    break;
            }
            index++;
        }
        
        guardarEnBitacoraOp(Double.toString(resultado));
        clear();
        
        return resultado;
    }
    

    
}
