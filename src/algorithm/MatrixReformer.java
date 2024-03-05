package algorithm;

import support.exceptions.CannotBeDiagonalException;
import support.input.Console;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MatrixReformer {

    public static void swapArray(BigDecimal[] first, BigDecimal[] second){
        BigDecimal temp;
        for (int i = 0; i < first.length; i++){
            temp = first[i];
            first[i] = second[i];
            second[i] = temp;
        }
    }

    public static void swapOne(BigDecimal[] array, int first, int second){
        BigDecimal temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static void diagonalPrevalence(Matrix matrix){
        BigDecimal[][] table = matrix.getTable();
        BigDecimal[] vector = matrix.getVector();
        int[] maximumCoefficients = new int[table.length];
        for (int i = 0; i < table.length; i++){
            maximumCoefficients[i] = 0;
            for (int i1 = 0; i1 < table[i].length - 1; i1++){
                if (table[i][maximumCoefficients[i]].abs().compareTo(table[i][i1].abs()) < 0) {
                    maximumCoefficients[i] = i1;
                }
            }
        }
        for (int i = 0; i < table.length; i++){
            if (i != maximumCoefficients[i]){
                swapArray(table[i], table[maximumCoefficients[i]]);
                swapOne(vector, i, maximumCoefficients[i]);
                int temp = maximumCoefficients[i];
                maximumCoefficients[i] = maximumCoefficients[maximumCoefficients[i]];
                maximumCoefficients[temp] = temp;
            }
        }
        for (int i = 0; i < table.length; i++){
            if (i == maximumCoefficients[i]){
                BigDecimal temp = new BigDecimal(BigInteger.ZERO);
                for (int i1 = 0; i1 < table.length; i1++){
                    if (i != i1){
                        temp = temp.add(table[i][i1].abs());
                    }
                }
                if (temp.compareTo(table[i][i].abs()) > 0) {
                    throw new CannotBeDiagonalException("Не удалось достигнуть диагонального преобладания!");
                }
            }
            else{
                throw new CannotBeDiagonalException("Не удалось достигнуть диагонального преобладания при перемещении строк!");
            }
        }


    }
}
