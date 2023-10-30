public class Model {
    private double result;

    public void calculate(String expression) {
        try {
            result = new DoubleEvaluator().evaluate(expression);
        } catch (Exception e) {
            result = Double.NaN;
        }
    }

    public double getResult() {
        return result;
    }
}