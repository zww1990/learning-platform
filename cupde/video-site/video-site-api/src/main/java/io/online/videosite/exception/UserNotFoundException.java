package io.online.videosite.exception;

import jakarta.persistence.PersistenceException;

/**
 * 找不到用户异常
 *
 * @author 张维维
 * @since 2023-10-05 16:41:42
 */
public class UserNotFoundException extends PersistenceException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
