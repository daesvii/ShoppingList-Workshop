package com.example.bdfinal.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@RequiredArgsConstructor
public class Constants {
    public enum Field{
        ID,
        NAME,
        QUANTITY,
        UNIT_PRICE,
        TOTAL_PRICE
    }
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String DATA_REPEATS_ITSELF_EXCEPTION_MESSAGE = "There are %s that are repeated";
    public static final String DATA_CANNOT_BE_UPDATED_EXCEPTION_MESSAGE = "%s cannot be updated";

}
