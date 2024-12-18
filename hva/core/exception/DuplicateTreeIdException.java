package hva.core.exception;

import java.io.Serial;

public class DuplicateTreeIdException extends IdException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public DuplicateTreeIdException(String key){
        super(key);
    }
}
