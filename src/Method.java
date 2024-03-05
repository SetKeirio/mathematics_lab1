import algorithm.Answer;
import algorithm.Matrix;
import algorithm.MatrixReformer;
import support.exceptions.CannotBeDiagonalException;
import support.exceptions.MatrixIsNotValidException;
import support.exceptions.NoAnswersException;
import support.exceptions.NotInIntervalException;
import support.input.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Method {
    //ahahaha

    private GetData getter;
    private static GetData createData(String[] flags){
        if (flags.length > 1){
            printGuide();
            System.exit(0);
        }
        boolean create = false;
        boolean fromFile = false;
        String file = "";
        for (int i = 0; i < flags.length; i++){
            if (flags[i].toLowerCase().equals("-c")){
                create = true;
            }
            else if (flags[i].toLowerCase().startsWith("-f=")){
                fromFile = true;
                file = flags[i].substring(3);
            }
            else {
                Console.print("Непонятный параметр: " + flags[i], "к");
                printGuide();
                System.exit(0);
            }
        }
        return chooseData(create, file, fromFile);
    }

    private static GetData chooseData(boolean create, String file, boolean fromFile){
        if (create){
            return new GeneratedData(new DataFromConsole(), -10, 30);
        }
        if (fromFile) {
            try {
                return new DataFromFile(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                Console.print("Файла с названием \"" + file + "\" нет(", "к");
                System.exit(0);
            }
        }
        return new DataFromConsole();
    }

    private static void printGuide(){
        Console.print("Привет! Стартовать программу можно так:", "б");
        Console.print("Без параметров", "а");
        Console.print("С параметром -f= или -F=: после равно задать имя файла, где есть входные данные", "п");
        Console.print("С параметром -c или -C: входные данные сгенерируются автоматически", "г");
        Console.print("Не используйте параметр -f или параметр -g вместе, пожалуйста", "б");
    }

    public static void main(String[] args) {
        GetData getter = createData(args);
        try {
            int tableSize = getter.getTableSize();
            int iterationCount = getter.getIterationCount();
            BigDecimal accuracy = getter.getAccuracy();
            BigDecimal[][] table = getter.getTable(tableSize);
            algorithm(tableSize, iterationCount, accuracy, table);
        }
        catch (MatrixIsNotValidException | NotInIntervalException e){
            Console.print(e.getMessage(), "к");
        }
        catch (NumberFormatException e){
            Console.print(e.getMessage(), "к");
            Console.print("Число введено некорректно!", "к");
        }
        catch (NoSuchElementException e){
            Console.print(e.getMessage(), "к");
            Console.print("Входные данные неполны!", "к");
        }
    }

    public static void algorithm(int tableSize, int iterationCount, BigDecimal accuracy, BigDecimal[][] table){
        try{
            Matrix matrix = new Matrix(table);
            Console.print("Программа получила на вход следующую матрицу:", "а");
            matrix.print();
            try {
                MatrixReformer.diagonalPrevalence(matrix);
                Console.print("\nУдалось диагонализировать матрицу:", "а");
            }
            catch (CannotBeDiagonalException e){
                Console.print(e.getMessage(), "к");
                Console.print("\nМатрица после попытки диагонализирования:", "а");
            }
            matrix.print();
            matrix.normalize();
            table = matrix.getTable();
            Console.print("\nНормализованная матрица:", "п");
            matrix.print();
            BigDecimal[] delta = new BigDecimal[tableSize];
            BigDecimal[] answer = new BigDecimal[tableSize];
            BigDecimal[] compare = new BigDecimal[tableSize];
            BigDecimal[] vector = matrix.getVector();
            int count = 0;
            answer = Arrays.copyOf(vector, vector.length);
            try {
                boolean finishAnswer = false;
                while (count < iterationCount) {
                    //Console.printEquation(answer);
                    count += 1;
                    BigDecimal maximumDelta = BigDecimal.ZERO;
                    compare = Arrays.copyOf(answer, tableSize);
                    for (int i = 0; i < tableSize; i++) {
                        answer[i] = vector[i];
                        for (int i1 = 0; i1 < tableSize; i1++) {
                            answer[i] = answer[i].add(table[i][i1].multiply(compare[i1]));
                        }
                        delta[i] = answer[i].subtract(compare[i]).abs();
                        maximumDelta = maximumDelta.max(delta[i]);

                    }
                    if (maximumDelta.compareTo(accuracy) <= 0) {
                        finishAnswer = true;
                        break;
                    }
                }
                if (finishAnswer == false) {
                    throw new NoAnswersException();
                }
            }
            catch (NoAnswersException e){
                Console.print("Решения с такой погрешностью за такое количество интервалов не удалось найти!", "к");
            }
            int scale = 0;
            BigDecimal temp = accuracy;
            while ((temp.compareTo(new BigDecimal("1")) <= 0) && (scale <= 10)){
                scale += 1;
                temp = temp.multiply(new BigDecimal("10"));
            }
            for (int i = 0; i < answer.length; i++){
                answer[i] = answer[i].setScale(scale, RoundingMode.HALF_EVEN);
            }
            for (int i = 0; i < delta.length; i++){
                delta[i] = delta[i].setScale(scale, RoundingMode.HALF_EVEN);
            }
            Console.print("\nРешения: ", "п");
            Console.printArray(answer, "б");
            Console.print("\nАбсолютные отклонения на последней итерации: ", "п");
            Console.printArray(delta, "б");
            Console.print("\nИтерации: " + count, "п");


        }
        catch (MatrixIsNotValidException | NotInIntervalException e){
            Console.print(e.getMessage(), "к");
        }
        catch (NumberFormatException e){
            Console.print(e.getMessage(), "к");
            Console.print("Число введено некорректно!", "к");
        }
        catch (NoSuchElementException e){
            Console.print(e.getMessage(), "к");
            Console.print("Входные данные неполны!", "к");
        }
    }

}
