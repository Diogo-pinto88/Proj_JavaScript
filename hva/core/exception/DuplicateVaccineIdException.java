package hva.core.exception;

import java.io.Serial;

public class DuplicateVaccineIdException extends IdException{

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public DuplicateVaccineIdException(String key){
        super(key);
    }

}
