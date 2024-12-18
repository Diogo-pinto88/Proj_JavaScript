package hva.core.exception;

import java.io.Serial;

public class UnknownEmployeeIdException extends IdException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownEmployeeIdException(String key) {
    super(key);
  }
}
