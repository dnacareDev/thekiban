package com.thekiban.Controller;

import com.thekiban.Entity.Breed;
import com.thekiban.Service.BreedService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/genome")
public class BreedController {

  private static final Logger log = LoggerFactory.getLogger(BreedController.class);

  @Autowired
  private BreedService service;

  @Autowired
  private FileController fileController;

  // 품종 관리 페이지
  @GetMapping(value = "breed")
  public ModelAndView getGeneticList(ModelAndView mv) {

    mv.setViewName("genome/breed");

    return mv;
  }

  // 엑셀 등록
  @ResponseBody
  @RequestMapping("breed/insertExcel")
  public ModelAndView InsertExcel(ModelAndView mv, @RequestParam("breed") String excel, @RequestParam("file") MultipartFile file) throws IOException {
    String[] extension = file.getOriginalFilename().split("\\.");

    String report_file = fileController.ChangeFileName(extension[1]);
    String report_origin_file = file.getOriginalFilename();

    String path = "upload";

    File filePath = new File(path);

    if (!filePath.exists())
      filePath.mkdirs();

    Path fileLocation = Paths.get(path).toAbsolutePath().normalize();
    Path targetLocation = fileLocation.resolve(report_file);

    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

    JSONArray arr = new JSONArray(excel);

    List<Breed> excels = new ArrayList<Breed>();

    int result = service.InsertExcel(excels);

    if (result == 0) {
      mv.setViewName("redirect:/genome/breed");
    } else {
      mv.setViewName("redirect:/genome/breed");
    }

    return mv;

  }

  // zhsthfxptmxm cnffur
  @ResponseBody
  @RequestMapping("test")
  public int Test(@RequestParam("excel") String excel) {
//    System.out.println(excel);
    JSONArray arr = new JSONArray(excel);

    for (int i = 0; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);
//      System.out.println("obj : " + obj);
    }

    return 0;
  }
  
  // DB 등록
  @PostMapping("breed/insertBreed")
  public int InsertBreed(@RequestParam("excel") String excel) {

    JSONArray arr = new JSONArray(excel);

    for(int i = 0; i < arr.length(); i++) {
      JSONObject obj = arr.getJSONObject(i);
      System.out.println("obj : " + obj);
    }

    return 0;
  }
}
