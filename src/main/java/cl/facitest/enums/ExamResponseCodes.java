package cl.facitest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@Getter
@AllArgsConstructor
public enum ExamResponseCodes {

    TEST_CREATED(HttpStatus.CREATED, "00", "Prueba creada"),
    TEST_ALREADY_EXISTS(HttpStatus.CONFLICT, "01", "Prueba ya existe"),
    TEST_NOT_FOUND(HttpStatus.NOT_FOUND, "02", "Prueba no existe"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "99", "internal server error"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "98", "unauthorized");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Setter
    private static MessageSource messageSource;

    public String getMessage() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(this.message, null, locale);
    }

}