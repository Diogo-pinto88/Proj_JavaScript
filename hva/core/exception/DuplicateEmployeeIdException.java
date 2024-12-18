package hva.core.exception;

import java.io.Serial;

public class DuplicateEmployeeIdException extends IdException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public DuplicateEmployeeIdException(String key){
        super(key);
    }
}
