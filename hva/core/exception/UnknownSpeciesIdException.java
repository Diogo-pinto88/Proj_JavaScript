package hva.core.exception;

import java.io.Serial;

public class UnknownSpeciesIdException extends IdException {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public UnknownSpeciesIdException(String key) {
    super(key);
  }
}
