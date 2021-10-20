package com.thekiban.controller;

import com.thekiban.Entity.ExcelData;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

  // 뷰 리턴
  @GetMapping("excel")
  public String main() {
    return "excelList";
  }

  // 엑셀 읽기
  @PostMapping("/excel/test")
  public String readExcel(@RequestParam("file")MultipartFile file, Model model) throws IOException {

    // tika mime 타입 얻어내기
//    Tika tika = new Tika();
//    String detect = tika.detect(file.getBytes());

    List<ExcelData> dataList = new ArrayList<>();
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());

//    if(!isExcel(detect, extension)) {
//      throw new IOException("give me excel file!");
//    }

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

    return "excelList";
  }

}
