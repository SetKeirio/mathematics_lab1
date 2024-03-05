package algorithm;

import support.exceptions.MatrixIsNotValidException;
import support.input.Console;
import support.input.Validate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Matrix {
    private BigDecimal[][] table;
    private final int size;

    public Matrix(BigDecimal[][] table){
        if (!(Validate.validateTable(table))){
            throw new MatrixIsNotValidException("Одно или несколько уравнений введены неверно");
        }
        this.table = new BigDecimal[table.length][table.length + 1];
        size = table.length;
        for (int i = 0; i < table.length; i++){
            this.table[i] = Arrays.copyOf(table[i], size + 1);
        }
    }

    public BigDecimal[][] getTable(){
        return table;
    }
    public BigDecimal[] getVector(){
        BigDecimal[] answer = new BigDecimal[size];
        for (int i = 0; i < size; i++){
            answer[i] = table[i][size];
        }
        return answer;
    }

    public void print(){
        for (int i = 0; i < size; i++){
            Console.printEquation(table[i]);
        }
    }

    public void normalize() {
        BigDecimal[][] answer = new BigDecimal[size][size + 1];
        for (int i = 0; i < size; i++){
            answer[i] = Arrays.copyOf(table[i], table[i].length);
        }
        for (int i = 0; i < size; i++) {
            if (table[i][i].compareTo(BigDecimal.ZERO) == 0){
                print();
                throw new MatrixIsNotValidException("Ноль в диагонали");
            }
            for (int i1 = 0; i1 < size; i1++) {
                if (i == i1){
                    answer[i][i1] = BigDecimal.ZERO;
                }
                else{
                    answer[i][i1] = table[i][i1].divide(table[i][i], table[i][i1].toString().length() + table[i][i].toString().length(), RoundingMode.HALF_EVEN).negate();
                }
            }
            answer[i][size] = table[i][size].divide(table[i][i], table[i][size].toString().length() + table[i][i].toString().length(), RoundingMode.HALF_EVEN).stripTrailingZeros();

        }
        for (int i = 0; i < size; i++){
            table[i] = Arrays.copyOf(answer[i], answer[i].length);
        }
    }

    @Override
    public String toString() {
        String answer = "";
        for (int i = 0; i < size; i++){
            for (int i1 = 0; i1 < size; i1++){
                answer += table[i][i1] + " ";
            }
            answer = answer.substring(0, answer.length()) + "\n";
        }
        answer.replace("E", " * 10^");
        return answer;
    }
}
