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
        return data.getAccuracy();
    }

    @Override
    public int getIterationCount() {
        return data.getIterationCount();
    }

    @Override
    public int getTableSize() {
        return data.getTableSize();
    }

    public static BigDecimal[][] generateTable(int size, double minimumCoefficient, double maximumCoefficient){
        BigDecimal[][] answer = new BigDecimal[size][size + 1];
        double scale = Math.pow(10, 1);
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size + 1; i1++) {
                answer[i][i1] = new BigDecimal(Math.round(minimumCoefficient + (maximumCoefficient - minimumCoefficient) * Math.random() * scale) / scale);
                answer[i][i1] = answer[i][i1].setScale(1, RoundingMode.HALF_EVEN);
            }
        }
        return answer;


    }
}
