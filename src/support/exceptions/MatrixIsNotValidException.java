package support.exceptions;

import algorithm.Matrix;

public class MatrixIsNotValidException extends RuntimeException{
    public MatrixIsNotValidException(String s){
        super(s);
    }
}
