package com.example.scical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

//KYLE ANGELA PART XML AND BUTTON CALLING

public class MainActivity extends AppCompatActivity {
    private TextView displayText, finalAnswer;
    private final StringBuilder equationBuilder = new StringBuilder();

    private SharedPreferences sharedPreferences;
    private static final String EQUATION_KEY = "equation";
    private static final String ANSWER_KEY = "answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText = findViewById(R.id.displayText);
        finalAnswer = findViewById(R.id.finalAnswer);
        sharedPreferences = getSharedPreferences("equation_pref", MODE_PRIVATE);

        String savedEquation = sharedPreferences.getString(EQUATION_KEY, "");
        equationBuilder.append(savedEquation);
        displayText.setText(equationBuilder.toString());

        String savedAnswer = sharedPreferences.getString(ANSWER_KEY, "");
        finalAnswer.setText(savedAnswer);

        Button sin = findViewById(R.id.sin);
        Button cos = findViewById(R.id.cos);
        Button tan = findViewById(R.id.tan);
        Button log = findViewById(R.id.log);
        Button ln = findViewById(R.id.ln);
        Button pi = findViewById(R.id.pi);
        Button squareroot = findViewById(R.id.squareroot);
        Button negate = findViewById(R.id.negate);
        Button power = findViewById(R.id.power);
        Button square = findViewById(R.id.square);
        Button factorial = findViewById(R.id.factorial);
        Button inverse = findViewById(R.id.inverse);
        Button dot = findViewById(R.id.dot);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button clear = findViewById(R.id.clear);
        Button clearAll = findViewById(R.id.clear_all);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button minus = findViewById(R.id.minus);
        Button add = findViewById(R.id.add);
        Button openBracket = findViewById(R.id.open_bracket);
        Button zero = findViewById(R.id.zero);
        Button closeBracket = findViewById(R.id.close_bracket);
        Button equals = findViewById(R.id.equals);

        sin.setOnClickListener(v -> onButtonClick("sin("));
        cos.setOnClickListener(v -> onButtonClick("cos("));
        tan.setOnClickListener(v -> onButtonClick("tan("));
        log.setOnClickListener(v -> onButtonClick("log("));
        ln.setOnClickListener(v -> onButtonClick("ln("));
        pi.setOnClickListener(v -> onButtonClick("π"));
        squareroot.setOnClickListener(v -> onButtonClick("√"));
        negate.setOnClickListener(v -> onButtonClick("(-"));
        power.setOnClickListener(v -> onButtonClick("^"));
        square.setOnClickListener(v -> onButtonClick("²"));
        factorial.setOnClickListener(v -> onButtonClick("!"));
        inverse.setOnClickListener(v -> onButtonClick("1/"));
        dot.setOnClickListener(v -> onButtonClick("."));
        seven.setOnClickListener(v -> onButtonClick("7"));
        eight.setOnClickListener(v -> onButtonClick("8"));
        nine.setOnClickListener(v -> onButtonClick("9"));
        clear.setOnClickListener(v -> clearText());
        clearAll.setOnClickListener(v -> clearAllText());
        four.setOnClickListener(v -> onButtonClick("4"));
        five.setOnClickListener(v -> onButtonClick("5"));
        six.setOnClickListener(v -> onButtonClick("6"));
        multiply.setOnClickListener(v -> onButtonClick("×"));
        divide.setOnClickListener(v -> onButtonClick("÷"));
        one.setOnClickListener(v -> onButtonClick("1"));
        two.setOnClickListener(v -> onButtonClick("2"));
        three.setOnClickListener(v -> onButtonClick("3"));
        minus.setOnClickListener(v -> onButtonClick("-"));
        add.setOnClickListener(v -> onButtonClick("+"));
        openBracket.setOnClickListener(v -> onButtonClick("("));
        zero.setOnClickListener(v -> onButtonClick("0"));
        closeBracket.setOnClickListener(v -> onButtonClick(")"));
        equals.setOnClickListener(v -> calculate());
    }

    //JORIZ VON GUTIERREZ PART FUNCTIONS AND CONDITIONS ON REACHING THE INTENDED CALCULATIONS
    private void onButtonClick(String buttonText) {
        if (buttonText.equals("!")) {
            String currentEquation = equationBuilder.toString();
            if (!currentEquation.isEmpty() && Character.isDigit(currentEquation.charAt(currentEquation.length() - 1))) {
                equationBuilder.append(buttonText);
                displayText.setText(equationBuilder.toString());
            }
        } else if (buttonText.equals("²")) {
            String currentEquation = equationBuilder.toString();
            if (!currentEquation.isEmpty() && Character.isDigit(currentEquation.charAt(currentEquation.length() - 1))) {
                equationBuilder.append("^2");
                displayText.setText(equationBuilder.toString());
            }
        } else if (buttonText.equals("1/")) {
            String currentEquation = equationBuilder.toString();
            if (!currentEquation.isEmpty() && Character.isDigit(currentEquation.charAt(currentEquation.length() - 1))) {
                equationBuilder.append("^(-1)");
                displayText.setText(equationBuilder.toString());
            }
        } else if (buttonText.equals("π")) {
            String currentEquation = equationBuilder.toString();
            if (currentEquation.isEmpty() || isOperator(currentEquation.charAt(currentEquation.length() - 1))) {
                equationBuilder.append("π");
                displayText.setText(equationBuilder.toString());
            } else {
                equationBuilder.append("×π");
                displayText.setText(equationBuilder.toString());
            }
        } else if (Character.isDigit(buttonText.charAt(0))) {
            String currentEquation = equationBuilder.toString();
            if (!currentEquation.isEmpty() && currentEquation.endsWith("π")) {
                equationBuilder.append("×");
            }
            equationBuilder.append(buttonText);
            displayText.setText(equationBuilder.toString());
        } else {
            equationBuilder.append(buttonText);
            displayText.setText(equationBuilder.toString());
        }
    }
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '^';
    }

    private void clearText() {
        if (equationBuilder.length() > 0) {
            equationBuilder.deleteCharAt(equationBuilder.length() - 1);
            displayText.setText(equationBuilder.toString());
        }
    }

    private void clearAllText() {
        equationBuilder.setLength(0);
        displayText.setText("");
        finalAnswer.setText("");
    }

    private void calculate() {
        String equation = equationBuilder.toString();
        try {
            double result = evaluateExpression(equation);
            if (!Double.isNaN(result)) {
                finalAnswer.setText(String.valueOf(result));
            } else {
                finalAnswer.setText("Error: Invalid expression");
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(EQUATION_KEY, equationBuilder.toString());
            editor.putString(ANSWER_KEY, String.valueOf(result));
            editor.apply();
        } catch (Exception e) {
            finalAnswer.setText("Error: " + e.getMessage());
        }
    }

    private double evaluateExpression(String expression) {
        expression = expression.replaceAll("÷", "/").replaceAll("×", "*");
        expression = expression.replaceAll("\\s+", "");
        expression = expression.replaceAll("π", String.valueOf(Math.PI));
        return eval(expression);
    }

    int factorial(int n) {
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseTerm();
                    else if (eat('/')) x /= parseTerm();
                    else return x;
                }
            }
//ADRIAN ABRIOL PART CALCULATIONS FOR EACH FORMULAS
            long factorial(int n) {
                if (n < 0) {
                    throw new IllegalArgumentException("Factorial is not defined for negative numbers");
                }
                long result = 1;
                for (int i = 2; i <= n; i++) {
                    result *= i;
                }
                return result;
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch == '√') {
                    nextChar();
                    x = parseExpression();
                    if (x < 0) throw new RuntimeException("Square root of a negative number is undefined");
                    x = Math.sqrt(x);
                } else if ((ch >= 'a' && ch <= 'z') || ch == '.') {
                    while ((ch >= 'a' && ch <= 'z') || ch == '.') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sin")) {
                        x = Math.sin(Math.toRadians(x));
                    } else if (func.equals("cos")) {
                        x = Math.cos(Math.toRadians(x));
                    } else if (func.equals("tan")) {
                        x = Math.tan(Math.toRadians(x));
                    } else if (func.equals("log")) {
                        x = Math.log10(x);
                    } else if (func.equals("ln")) {
                        x = Math.log(x);
                    } else {
                        throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                while (eat('!')) {
                    int n = (int) x;
                    x = factorial(n);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());
                return x;
            }
        }.parse();

    }

    private double evaluateFunction(String functionName, double argument) {
        switch (functionName) {
            case "sin":
                return Math.sin(Math.toRadians(argument));
            case "cos":
                return Math.cos(Math.toRadians(argument));
            case "tan":
                return Math.tan(Math.toRadians(argument));
            case "log":
                return Math.log10(argument);
            case "ln":
                return Math.log(argument);
            case "sqrt":
                return Math.sqrt(argument);
            default:
                return Double.NaN;
        }
    }

    private int precedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    private void applyOperator(Stack<Double> operands, char operator) {
        double operand2 = operands.pop();
        double operand1 = operands.pop();
        double result = 0;
        switch (operator) {
            case '^':
                result = Math.pow(operand1, operand2);
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
        }
        operands.push(result);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EQUATION_KEY, equationBuilder.toString());
        editor.putString(ANSWER_KEY, finalAnswer.getText().toString());
        editor.apply();
    }
}
