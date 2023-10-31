import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        
        for (int i = 0; i <= 9; i++) {
            view.BotonNumeroListener(Integer.toString(i), new BotonNumeroListener(Integer.toString(i)));
        }
        view.BotonNumeroListener(".", new BotonNumeroListener("."));
        view.BotonOperacionListener("+", new BotonOperacionListener("+"));
        view.BotonOperacionListener("-", new BotonOperacionListener("-"));
        view.BotonOperacionListener("*", new BotonOperacionListener("*"));
        view.BotonOperacionListener("/", new BotonOperacionListener("/"));
        view.BotonIgualListener( new BotonIgualListener());
        view.BotonPrimoListener( new BotonPrimoListener());
        view.BotonBinarioListener( new BotonBinarioListener());
        view.BotonMemoriaListener( new BotonMemoriaListener());
        view.BotonAvgListener( new BotonAvgListener());
        view.BotonDataListener( new BotonDataListener());
        view.BotonClearListener( new BotonClearListener());


    }

    public class BotonNumeroListener implements ActionListener {
        private String numero;

        public BotonNumeroListener(String numero) {
            this.numero = numero;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getDisplayText().equals("True") || view.getDisplayText().equals("False")) {
                view.setDisplayText(numero);
            } else {
                view.setDisplayText(view.getDisplayText() + numero);
            }
        }
    }

    public class BotonOperacionListener implements ActionListener {
        private String operacion;

        public BotonOperacionListener(String operacion) {
            this.operacion = operacion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            model.getNumeros().add(Double.parseDouble(view.getDisplayText()));
            model.getOperadores().add(operacion);
            view.setDisplayText("");
        }
    }

    public class BotonClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setDisplayText("");
            model.clear();
        }
    }

    public class BotonIgualListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String resultado = Double.toString(model.calcular(view.getDisplayText()));
            view.setDisplayText(resultado);
        }
    }

    public class BotonPrimoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = view.getDisplayText();
            if (model.esPrimo(Integer.parseInt(input))) {
                view.setDisplayText("True");
            } else {
                view.setDisplayText("False");
            }
        }
    }

    public class BotonBinarioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String operacion = "Decimal a binario";
            String input = view.getDisplayText();
            int decimal = Integer.parseInt(input);
            String binario = Integer.toBinaryString(decimal);
            view.setDisplayText(binario);
            model.GuardarPrimoOBinario(operacion, decimal, binario); //arreglar esto
        }
    }

    public class BotonMemoriaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {  
            String input = view.getDisplayText();
            double numero = Double.parseDouble(input);
            model.GuardarEnMemoria(numero);
        }
    }

    public class BotonAvgListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String resultado = Double.toString(model.CalcularPromedio());
            view.setDisplayText(resultado);
        }
    }

    public class BotonDataListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.mostrarBitacora();
        }
    }

}
