package com.noz.identity.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)//loai bo message khi tra ve code 1000
public class ApiResponse<T> {
    private  int code = 1000;
    private String message;
    private T result;
}
