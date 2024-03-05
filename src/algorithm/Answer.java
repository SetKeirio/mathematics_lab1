package algorithm;

import java.math.BigDecimal;

public class Answer {
    private final int iterationsCount;
    private final BigDecimal[] delta;
    private final BigDecimal[] answer;

    public Answer(int iterationsCount, BigDecimal[] delta, BigDecimal[] answer) {
        this.iterationsCount = iterationsCount;
        this.delta = delta;
        this.answer = answer;
    }

    public BigDecimal[] getAnswer() {
        return answer;
    }

    public BigDecimal[] getDelta() {
        return delta;
    }

    public int getIterationsCount() {
        return iterationsCount;
    }

}
