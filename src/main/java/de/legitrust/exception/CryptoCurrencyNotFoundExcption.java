package de.legitrust.exception;

public class CryptoCurrencyNotFoundExcption extends Exception {
  private static final long serialVersionUID = 1L;

  private static final String MESSAGE_FORMAT = "Crypto currency with id '%s' not found.";

  public CryptoCurrencyNotFoundExcption(String id) {
    super(String.format(MESSAGE_FORMAT, id));
  }
}
