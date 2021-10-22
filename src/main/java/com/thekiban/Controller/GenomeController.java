package com.thekiban.Controller;

import com.thekiban.Entity.ExcelData;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GenomeController {

  // 품종 관리 페이지
  @GetMapping(value = "/genetic")
  public ModelAndView getGeneticList(ModelAndView mv) {

    mv.setViewName("genome/genetic");

    return mv;
  }

  // 엑셀 읽기
  @PostMapping("/genetic")
  public String readExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {

    List<ExcelData> dataList = new ArrayList<>();
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());

    if (!extension.equals("xlsx") && !extension.equals("xls")) {
      throw new IOException("give me excel!");
    }

    Workbook workbook = null;

    if (extension.equals("xlsx")) {
      workbook = new XSSFWorkbook(file.getInputStream());
    } else if (extension.equals("xls")) {
      workbook = new HSSFWorkbook(file.getInputStream());
    }

//    assert workbook != null;
    Sheet worksheet = workbook.getSheetAt(0);

    for(int i = 3; i < worksheet.getPhysicalNumberOfRows(); i++) {
      Row row = worksheet.getRow(i);

      ExcelData data = new ExcelData();

      data.setCode(row.getCell(0).getStringCellValue());
      data.setName(row.getCell(1).getStringCellValue());
      data.setKind(row.getCell(2).getStringCellValue());
      data.setMom(row.getCell(3).getStringCellValue());
      data.setDad(row.getCell(4).getStringCellValue());
      data.setSale_num(row.getCell(5).getStringCellValue());
      data.setSale_date(row.getCell(6).getDateCellValue());
      data.setApply_num(row.getCell(7).getStringCellValue());
      data.setApply_date(row.getCell(8).getDateCellValue());
      data.setEnroll_num(row.getCell(9).getStringCellValue());
      data.setEnroll_date(row.getCell(10).getDateCellValue());
      data.setLocal((int) row.getCell(11).getNumericCellValue());
      data.setPurpose(row.getCell(12).getStringCellValue());
      data.setFirst_form(row.getCell(13).getStringCellValue());
      data.setSecond_form(row.getCell(14).getStringCellValue());
      data.setFirst_crop(row.getCell(15).getStringCellValue());
      data.setSecond_crop(row.getCell(16).getStringCellValue());
      data.setComment(row.getCell(17).getStringCellValue());

      dataList.add(data);
    }

    model.addAttribute("datas", dataList);

    return "genome/genetic";
  }

  // 원종 관리 페이지
  @GetMapping(value = "origin")
  public ModelAndView getOriginList(ModelAndView mv) {

    mv.setViewName("genome/origin");

    return mv;
  }

  //도입자원 관리 페이지
  @GetMapping(value = "intro_resource")
  public ModelAndView getIntroResourceList(ModelAndView mv) {

    mv.setViewName("genome/intro_resource");

    return mv;
  }

  //시교자원 관리 페이지
  @GetMapping(value = "test_harvest")
  public ModelAndView getTestHarvestList(ModelAndView mv) {

    mv.setViewName("genome/test_harvest");

    return mv;
  }
}
