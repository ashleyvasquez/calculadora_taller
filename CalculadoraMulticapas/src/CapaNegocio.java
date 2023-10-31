import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class CapaNegocio {

    private CapaDatos datos;
    private List<Double> numeros;
    private List<String> operadores;
    private Queue<Double> memoria;

    public CapaNegocio(CapaDatos datos) {
        this.datos = datos;
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

    public void GuardarEnMemoria(double numero) {
        String operacion = "M+";
        if (memoria.size() >= 10) {
            memoria.poll(); // Elimina el elemento más antiguo si la memoria está llena
        }
        memoria.offer(numero); // Agrega el número a la memoria
        datos.GuardarLista(operacion, numero, memoria);
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
            datos.GuardarLista(operacion, promedio, memoria);
            return promedio;
        }
        else{
            return 0.0;
        }

    }

    public boolean esPrimo(int numero) {
        String operacion = "Es primo";
        if (numero <= 1) {
            datos.GuardarPrimoOBinario(operacion, numero, "False");
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                datos.GuardarPrimoOBinario(operacion, numero, "False");
                return false;
            }
        }
        datos.GuardarPrimoOBinario(operacion, numero, "False");
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
        
        datos.guardarEnBitacoraOp(Double.toString(resultado), numeros, operadores);
        clear();
        
        return resultado;
    }
    

    
}

