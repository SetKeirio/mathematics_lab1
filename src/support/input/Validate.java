package support.input;

import java.math.BigDecimal;

public class Validate {
    public static boolean validateTable(BigDecimal[][] table){
        if (table.length == 0){
            return false;
        }
        else{
            for (int i = 0; i < table.length; i++){
                if (table[i].length != table.length + 1){
                    return false;
                }
            }
        }
        return true;
    }
}
