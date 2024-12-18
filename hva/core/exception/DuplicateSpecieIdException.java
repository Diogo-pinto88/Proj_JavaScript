package hva.core.exception;

import java.io.Serial;

public class DuplicateSpecieIdException extends IdException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public DuplicateSpecieIdException(String key){
        super(key);
    }
}
