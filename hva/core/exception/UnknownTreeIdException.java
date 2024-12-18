package hva.core.exception;

import hva.app.exception.Message;

import java.io.Serial;
import java.security.Key;

public class UnknownTreeIdException extends IdException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownTreeIdException(String key) {
    super(key);
  }
}
