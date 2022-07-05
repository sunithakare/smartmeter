package com.envision.common.modal;

import lombok.Data;

@Data
public class RestResponse<T> {

    private String status;
    private int httpcode;
    private String message;

    private T data;
}