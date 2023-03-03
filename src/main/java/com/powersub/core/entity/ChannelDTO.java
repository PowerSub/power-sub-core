package com.powersub.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO {

    @NotEmpty
    @Size(min = 2, max = 256)
    private String title;

    @Size(max = 1024)
    private String description;

}
