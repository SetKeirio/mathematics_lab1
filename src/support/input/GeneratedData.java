package support.input;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeneratedData implements GetData{
    private final GetData data;
    private final double minimumCoefficient;
    private final double maximumCoefficient;

    public GeneratedData(GetData data, double minimumCoefficient, double maximumCoefficient) {
        this.data = data;
        this.minimumCoefficient = minimumCoefficient;
        this.maximumCoefficient = maximumCoefficient;
    }

    @Override
    public BigDecimal[][] getTable(int size) {
        BigDecimal[][] answer = generateTable(size, minimumCoefficient, maximumCoefficient);
        return answer;
    }

    @Override
    public BigDecimal getAccuracy() {
        /*BigDecimal temp = new BigDecimal(0.00000001 + (0.01 - 0.00000001) * Math.random());
        Console.print("Точность, при которой происходит последняя итерация: " + temp.toString(), "г");
        return temp;*/
        return data.getAccuracy();
    }

    @Override
    public int getIterationCount() {
        /*int temp = (int) ((Math.random() * (100 - 1) + 1));
        Console.print("Количество итераций: " + temp, "г");
        return temp;*/
        return data.getIterationCount();
    }

    @Override
    public int getTableSize() {
        /*int temp = (int) ((Math.random() * (20 - 1) + 1));
        Console.print("Размер матрицы: " + temp, "г");
        return temp;*/
        return data.getTableSize();
    }

    public static BigDecimal[][] generateTable(int size, double minimumCoefficient, double maximumCoefficient){
        BigDecimal[][] answer = new BigDecimal[size][size + 1];
        double scale = Math.pow(10, 1);
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size + 1; i1++) {
                if (i != i1) {
                    answer[i][i1] = new BigDecimal(Math.round(minimumCoefficient + (maximumCoefficient - minimumCoefficient) * Math.random() * scale) / scale);
                }
                else{
                    answer[i][i1] = new BigDecimal(Math.round(minimumCoefficient + maximumCoefficient * Math.random() * scale * size * size) / scale);
                }
                answer[i][i1] = answer[i][i1].setScale(1, RoundingMode.HALF_EVEN);
            }
        }
        return answer;


    }
}
