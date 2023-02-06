package com.powersub.core.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ExceptionDTO {

    private final String code;
    private final String msg;

}
