package com.powersub.core.entity;

import com.powersub.core.exception.GenericExceptionCodes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {

    private GenericExceptionCodes code;
    private  String msg;

}
