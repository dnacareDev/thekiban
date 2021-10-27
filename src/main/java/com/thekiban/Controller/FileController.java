package com.thekiban.Controller;

import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class FileController {

  public String ChangeFileName(String extension)
  {
    String fileName = "";

    Date date = new Date();

    fileName = Long.toString(date.getTime()) +"." + extension;

    return fileName;
  }

}
