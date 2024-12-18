package hva.core.exception;

import hva.app.exception.Message;


import java.io.Serial;

public class UnknownVaccineIdException extends IdException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownVaccineIdException(String key) {
    super(key);
  }
}
