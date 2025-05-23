package org.example.connectfour.service;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public static class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
      super(message);
    }
  }

  public static class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
      super(message);
    }
  }
}
