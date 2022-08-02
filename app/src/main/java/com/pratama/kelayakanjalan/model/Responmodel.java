package com.pratama.kelayakanjalan.model;

import com.pratama.kelayakanjalan.Ruas_Class;

import java.util.List;

public class Responmodel {

    String code, message;
    List<Ruas_Model> result;

    public List<Ruas_Model> getResult() {
        return result;
    }

    public void setResult(List<Ruas_Model> result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
