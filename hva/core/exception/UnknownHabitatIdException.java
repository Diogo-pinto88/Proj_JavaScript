package hva.core.exception;

import java.io.Serial;

public class UnknownHabitatIdException extends IdException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownHabitatIdException(String key) {
    super(key);
  }
}