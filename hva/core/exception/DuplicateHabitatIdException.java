package hva.core.exception;

import java.io.Serial;

public class DuplicateHabitatIdException extends IdException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public DuplicateHabitatIdException(String key){
        super(key);
    }
}
