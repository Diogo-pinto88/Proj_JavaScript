package hva.core.exception;

import java.io.Serial;

public abstract class IdException extends Exception{

    @Serial
    private static final long serialVersionUID = 202407081733L;
    private String _key;

    public IdException(String _key) {
        this._key = _key;
    }

    public String getKey() {
        return _key;
    }

}
