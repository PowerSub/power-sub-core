package com.powersub.core.entity;

import com.powersub.core.exception.GenericExceptionCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {

    private GenericExceptionCodes code;
    private String msg;

}
