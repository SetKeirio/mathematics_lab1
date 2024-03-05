package support.input;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataFromConsole implements GetData{

    private final Scanner s;
    public DataFromConsole(){
        s = new Scanner(System.in);
    }
    @Override
    public BigDecimal[][] getTable(int size) {
        Console.print("Напишите, пожалуйста, коэффициенты в уравнениях", "г");
        BigDecimal[][] answer = new BigDecimal[size][size + 1];
        for (int i = 0; i < size; i++){
            for (int i1 = 0; i1 < size + 1; i1++){
                answer[i][i1] = getDouble();
            }
        }
        return answer;
    }

    @Override
    public BigDecimal getAccuracy() {
        Console.print("Напишите, пожалуйста, абсолютное отклонение, при котором происходит последняя итерация", "г");
        BigDecimal answer = getDoubleInInterval(new BigDecimal("0.00000001"), new BigDecimal("1"));
        return answer;
    }

    @Override
    public int getIterationCount() {
        Console.print("Напишите, пожалуйста, количество итераций", "г");
        int answer = getIntegerInInterval(1, 10000);
        return answer;
    }

    @Override
    public int getTableSize() {
        Console.print("Напишите, пожалуйста, размер матрицы уравнений", "г");
        int answer = getIntegerInInterval(1, 20);
        return answer;
    }

    private BigDecimal getDouble() {
        BigDecimal answer;
        while (1 > 0){
            try{
                answer = new BigDecimal(s.next().replace(",", "."));
                break;
            }
            catch (NumberFormatException e){
                s.nextLine();
                Console.print("Не удалось прочитать нецелое число! Еще раз:", "б");
            }
        }
        return answer;
    }

    private int getInteger() {
        Integer answer;
        while (1 > 0){
            try{
                answer = Integer.parseInt(s.next());
                break;
            }
            catch (NumberFormatException e){
                s.nextLine();
                Console.print("Не удалось прочитать целое число! Еще раз:", "б");
            }
        }
        return answer;
    }

    private int getIntegerInInterval(int minimum, int maximum){
        Console.print("Напишите, пожалуйста, целое число от " + minimum + " до " + maximum, "а");
        int answer = getInteger();
        while (!((answer >= minimum) && (answer <= maximum))){
            Console.print("Число не входит в диапазон от " + minimum + " до " + maximum + "! Еще раз:", "б");
            answer = getInteger();
        }
        return answer;

    }

    private BigDecimal getDoubleInInterval(BigDecimal minimum, BigDecimal maximum){
        Console.print("Напишите, пожалуйста, нецелое число от " + minimum.toString().replace("E", " * 10^") + " до " + maximum.toString().replace("E", " * 10^"), "а");
        BigDecimal answer = getDouble();
        while (!((answer.compareTo(minimum) >= 0) && (answer.compareTo(maximum) <= 0))){
            Console.print("Число не входит в диапазон от " + minimum.toString().replace("E", " * 10^") + " до " + maximum.toString().replace("E", " * 10^") + "! Еще раз:", "б");
            answer = getDouble();
        }
        return answer;
    }


}
