package com.example.team5ad.entity;

import lombok.Data;

@Data
public class Result {

    private int code = 0;
    private String msg = "success";
    private Object data;

}
