import javax.swing.JFrame;

public class CalculatorApp {
    public static void main(String[] args) {
        CapaDatos datos = new CapaDatos();
        CapaNegocio negocio = new CapaNegocio(datos);
        CapaPresentacion presentacion = new CapaPresentacion(negocio, datos);

        presentacion.setSize(600, 700);
        presentacion.setTitle("Calculadora Simple");
        presentacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        presentacion.setVisible(true);
    }
}


