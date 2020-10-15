package hello.utils;

import hello.dao.pojo.BannerInfo;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<BannerInfo> excelToShopIdList(InputStream inputStream) {
        List<BannerInfo> bannerInfos = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum() - 1;
            System.out.println("总行数有多少行" + rowLength);
            //工作表的行
            Row row = sheet.getRow(0);

            //总列数
            int colLength = row.getLastCellNum();
            System.out.println("总列数有多少列" + colLength);
            //得到指定的单元格
            Cell cell = row.getCell(0);
            for (int i = 0; i < rowLength; i++) {
                BannerInfo bannerInfo = new BannerInfo();
                row = sheet.getRow(i);
                Cell bannerUrlCell = row.getCell(0);
                bannerInfo.setBannerUrl(bannerUrlCell.getStringCellValue());
                Cell titleCell = row.getCell(1);
                if (cell.getCellTypeEnum() == CellType.STRING) {
                    bannerInfo.setTitle(titleCell.getStringCellValue());
                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    bannerInfo.setTitle(titleCell.getNumericCellValue() + "");
                } else {

                }
                bannerInfos.add(bannerInfo);


            }

        } catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return bannerInfos;
    }


}
