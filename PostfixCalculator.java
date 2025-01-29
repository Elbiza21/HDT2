package calculator.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PostfixCalculator implements IPostfixCalculator {
    private Stack<Double> stack;
    private String lastExpression;

    public PostfixCalculator(Stack<Double> stack) {
        this.stack = stack;
        this.lastExpression = "";
    }

    @Override
    public void readFromFile(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("El path del archivo no puede ser nulo o vacío.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            lastExpression = br.readLine();
            if (lastExpression == null || lastExpression.trim().isEmpty()) {
                throw new IllegalArgumentException("El archivo está vacío o no contiene una expresión válida.");
            }
        }
    }

    @Override
    public double evaluateExpression() throws IllegalArgumentException {
        if (lastExpression == null || lastExpression.isEmpty()) {
            throw new IllegalArgumentException("No hay una expresión cargada. Usa readFromFile() primero.");
        }

        stack.clear();
        String[] tokens = lastExpression.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Expresión inválida: faltan operandos para la operación.");
                }

                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("Error: División por cero.");
                        stack.push(a / b);
                        break;
                    case "%":
                        if (b == 0) throw new ArithmeticException("Error: División por cero.");
                        stack.push(a % b);
                        break;
                    default:
                        throw new IllegalArgumentException("Operador no reconocido: " + token);
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expresión inválida: operandos sobrantes o expresión mal formada.");
        }

        return stack.pop();
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
