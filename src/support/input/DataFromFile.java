package support.input;

import support.exceptions.NotInIntervalException;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataFromFile implements GetData{
    private final Scanner s;
    public DataFromFile(FileInputStream input){
        s = new Scanner(input);
    }
    @Override
    public BigDecimal[][] getTable(int size) {
        BigDecimal[][] answer = new BigDecimal[size][size + 1];
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size + 1; i1++) {
                answer[i][i1] = getDouble();
            }
        }
        return answer;
    }

    @Override
    public BigDecimal getAccuracy() {
        BigDecimal answer = getDouble();
        if (!((answer.compareTo(new BigDecimal("0.00000001")) >= 0) && (answer.compareTo(BigDecimal.ONE) <= 0))){
            throw new NotInIntervalException("Точность должна быть нецелой, положительной, не меньше 0,00000001 и не больше 1.0");
        }
        return answer;
    }

    @Override
    public int getIterationCount() {
        int answer = getInteger();
        if (!((answer >= 1) && (answer <= 10000))){
            throw new NotInIntervalException("Количество итераций должно быть целым, положительным и меньше 10001");
        }
        return answer;
    }

    @Override
    public int getTableSize() {
        int answer = getInteger();
        if (!((answer >= 1) && (answer <= 20))){
            throw new NotInIntervalException("Размер матрицы должен быть целым, положительным и меньше 21");
        }
        return answer;
    }

    private BigDecimal getDouble() throws NoSuchElementException, NumberFormatException {
        return new BigDecimal(s.next().replace(',', '.'));
    }

    private int getInteger() throws NoSuchElementException, NumberFormatException {
        return Integer.parseInt(s.next());
    }

}
