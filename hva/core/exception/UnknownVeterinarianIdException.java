package hva.core.exception;

import hva.app.exception.Message;

import java.io.Serial;

public class UnknownVeterinarianIdException extends Exception {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public UnknownVeterinarianIdException(String id) {
    super(Message.unknownVeterinarianKey(id));
  }
}
