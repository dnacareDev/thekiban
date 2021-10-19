package com.thekiban.controller;

import com.thekiban.entity.Items;
import com.thekiban.service.ItemsService;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class TestController {

  @Autowired
  private ItemsService service;

  @RequestMapping("/test")
  public ModelAndView TestList(ModelAndView mv) {

    mv.setViewName("excel_test");

    return mv;
  }

  // 엑셀 업로드
  @ResponseBody
  @RequestMapping("uploadExcel")
  public void UploadExcel(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws EncryptedDocumentException, IOException {
    response.setContentType("text/html;charset=utf-8");

    PrintWriter out = response.getWriter();

    String[] extension = file.getOriginalFilename().split("\\.");

    try {
      int result = 0;

      if (!(extension[1].equals("xlsx") || extension[1].equals("xls"))) {
        out.println("<script>");
        out.println("alert('엑셀 파일 형식이 아닙니다.');");
        out.println("location.replace('/test')");
        out.println("</script>");
        out.close();
      } else {
        List<Items> items = ReadExcel(file);

        int count = 0;

        for (int i = 0; i < items.size(); i++) {
          if (items.get(i).getItems_code() == null || items.get(i).getItems_code().isEmpty()) {
            count = 1;
          } else if (items.get(i).getItems_title() == null || items.get(i).getItems_title().isEmpty()) {
            count = 1;
          } else if (items.get(i).getCategory() == null || items.get(i).getCategory().isEmpty()) {
            count = 1;
          } else if (items.get(i).getItems_account() == null || items.get(i).getItems_account().isEmpty()) {
            count = 1;
          } else {
            int cat_id = service.SelectCatId(items.get(i).getCategory());
            items.get(i).setCat_id(cat_id);
          }
        }

        if (count == 0) {
          for (int i = 0; i < items.size(); i++) {
            result = service.InsertItems(items.get(i));
          }
        }
      }

      if (result == 0) {
        out.println("<script>");
        out.println("alert('제품 등록에 실패하였습니다.');");
        out.println("location.replace('/test')");
        out.println("</script>");
        out.close();
      } else {
        out.println("<script>");
        out.println("alert('제품 등록이 완료되었습니다.');");
        out.println("location.replace('/test')");
        out.println("</script>");
        out.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 엑셀 값 읽기
  public List<Items> ReadExcel(@RequestParam("file") MultipartFile file) throws EncryptedDocumentException, IOException {
    List<Items> result = new ArrayList<>();

    // 웹 상에서 업로드 되어 MultipartFile인 경우 바로 InputStream으로 변경하여 사용.
    InputStream inputStream = new ByteArrayInputStream(file.getBytes());

//		String filePath = "D:\\student.xlsx";			// xlsx 형식
//		String filePath = "D:\\student.xls";			// xls 형식

//		InputStream inputStream = new FileInputStream(file);

    // 엑셀 로드
    Workbook workbook = WorkbookFactory.create(inputStream);

    // 시트 로드 0, 첫번째 시트 로드
    Sheet sheet = workbook.getSheetAt(0);

    Iterator<Row> rowItr = sheet.iterator();

    // 행만큼 반복
    while (rowItr.hasNext()) {
      Items items = new Items();

      Row row = rowItr.next();

      // 첫번째 행이 헤더인 경우 스킵, 2번째 행부터 data 로드
      if (row.getRowNum() == 0) {
        continue;
      }

      Iterator<Cell> cellItr = row.cellIterator();

      // 한 행이 한 열씩 읽기 (셀 읽기)
      while (cellItr.hasNext()) {
        Cell cell = cellItr.next();

        int index = cell.getColumnIndex();

        switch (index) {
          case 0:
            items.setItems_code((String.valueOf(getValueFromCell(cell))));
            break;
          case 1:
            items.setItems_title((String) getValueFromCell(cell));
            break;
          case 2:
            items.setCategory((String) getValueFromCell(cell));
            break;
          case 3:
            items.setItems_standard((String) getValueFromCell(cell));
            break;
          case 4:
            items.setItems_account((String) getValueFromCell(cell));
            break;
          case 5:
            items.setItems_price(((Double) getValueFromCell(cell)).intValue());      // 셀이 숫자형인 경우 Double형으로 변환 후 int형으로 변환
            break;
          case 6:
            items.setItems_contents((String) getValueFromCell(cell));
            break;
        }
      }

      if (!(items.getItems_code() == null || items.getItems_code().isEmpty() || items.getItems_code().equals(""))) {
        result.add(items);
      }
    }

    return result;
  }

  // 셀 서식에 맞게 값 읽기
  private static Object getValueFromCell(Cell cell) {
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();

      case BOOLEAN:
        return cell.getBooleanCellValue();

      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue();
        }

        return cell.getNumericCellValue();

      case FORMULA:
        return cell.getCellFormula();

      case BLANK:
        return "";

      default:
        return "";
    }
  }

}
