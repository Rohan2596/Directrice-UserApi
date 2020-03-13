package com.directrice.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Response {

    public String message;
    private Object data;

    public Response(String message, Object data) {
        this.message=message;
        this.data=data;
    }
}

