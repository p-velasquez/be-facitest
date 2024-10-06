package cl.facitest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExamResponseCodes {

    EXAM_CREATED(HttpStatus.CREATED, "00", "Prueba creada"),
    EXAM_ALREADY_EXISTS(HttpStatus.CONFLICT, "01", "Prueba ya existe"),
    EXAM_NOT_FOUND(HttpStatus.NOT_FOUND, "02", "Prueba no existe"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "99", "internal server error"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "98", "unauthorized");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}