package calculator.com;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Debe proporcionar la ruta del archivo como argumento.");
            }

            String filePath = args[0];
            PostfixCalculator calculator = new PostfixCalculator(new VectorStack<>());
            calculator.readFromFile(filePath);
            double result = calculator.evaluateExpression();
            System.out.println("Resultado: " + result);

        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos de entrada: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error en la operación matemática: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}

