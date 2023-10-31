import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

public class CapaDatos {

    public void guardarEnBitacoraOp(String resultado, List<Double> numeros, List<String> operadores) {
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

    public void GuardarLista(String operacion, Double resultado, Queue<Double> memoria) {
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
}
