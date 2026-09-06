package lld.designpatterns.behavioral;

import java.util.Map;

/**
 * Models a very small language: numbers and addition.
 */
public class InterpreterExample {
    interface Expression {
        int interpret(Map<String, Integer> values);
    }

    static class Number implements Expression {
        private final int value;

        Number(int value) {
            this.value = value;
        }

        public int interpret(Map<String, Integer> values) {
            return value;
        }
    }

    static class Variable implements Expression {
        private final String name;

        Variable(String name) {
            this.name = name;
        }

        public int interpret(Map<String, Integer> values) {
            return values.get(name);
        }
    }

    static class Add implements Expression {
        private final Expression left, right;

        Add(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        public int interpret(Map<String, Integer> values) {
            return left.interpret(values) + right.interpret(values);
        }
    }

    public static void main(String[] args) {
        Expression formula = new Add(new Variable("hours"), new Number(2));
        System.out.println(formula.interpret(Map.of("hours", 8)));
    }
}
