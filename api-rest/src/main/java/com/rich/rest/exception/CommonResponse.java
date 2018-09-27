package com.rich.rest.exception;

import com.rich.rest.utils.Constants;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CommonResponse {
    
    private int code = HttpStatus.OK.value();
    
    private String message = Constants.SUCCESS;
    
    private String description;

    public CommonResponse() {}

    public CommonResponse(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }
    
}
