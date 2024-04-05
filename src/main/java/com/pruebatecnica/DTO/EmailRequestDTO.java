package com.pruebatecnica.DTO;

import lombok.Data;

import java.io.File;

@Data
public class EmailRequestDTO {
    private String To;
    private String Body;
    private File file;
    private String Subject="Prueba técnica";
    private String From="carrilloacerojuanpablo@gmail.com";
}
