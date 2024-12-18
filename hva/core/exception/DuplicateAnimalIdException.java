package hva.core.exception;

import java.io.Serial;

public class DuplicateAnimalIdException extends IdException {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public DuplicateAnimalIdException(String key){
        super(key);
    }
}
