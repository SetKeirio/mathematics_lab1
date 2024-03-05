package support.input;

import java.math.BigDecimal;

public interface GetData {
    BigDecimal[][] getTable(int size);
    BigDecimal getAccuracy();
    int getIterationCount();
    int getTableSize();
}
