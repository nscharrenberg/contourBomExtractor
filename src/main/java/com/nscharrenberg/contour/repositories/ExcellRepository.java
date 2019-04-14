package com.nscharrenberg.contour.repositories;

import com.google.common.collect.HashBiMap;
import com.nscharrenberg.contour.domain.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class ExcellRepository {
    public String write(List<Product> data, String path) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);

        for (Product p : data) {
            Sheet sheet = workbook.createSheet(p.getId().toString());

            Row header = sheet.createRow(0);
            Row row = sheet.createRow(1);

            /** Header Cell Style **/
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
            headerStyle.setFont(font);

            /** Normal Cell Style **/
            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

            List<String> fields = new ArrayList<>(
                    Arrays.asList(
                            "id",
                            "default_code",
                            "active",
                            "product_tmpl_id",
                            "barcode",
                            "volume",
                            "weight",
                            "message_last_post",
                            "activity_date_deadline",
                            "create_uid",
                            "create_date",
                            "write_uid",
                            "write_date"
                    )
            );

            List<String> f = new ArrayList<>();
            try {
                f.add(p.getId() != null ? p.getId().toString() : "-");
                f.add(p.getDefaultCode() != null ? p.getDefaultCode() : "-");
                f.add(p.isActive() != null ? p.isActive().toString() : "-");
                f.add(p.getProductTmplId() != null ? p.getProductTmplId().toString() : "-");
                f.add(p.getBarcode() != null ? p.getBarcode() : "-");
                f.add(Double.toString(p.getVolume()));
                f.add(p.getWeight() != null ? p.getWeight().toString() : "-");
                f.add(p.getMessageLastPost() != null ? p.getMessageLastPost().toString() : "-");
                f.add(p.getActivityDateDeadline() != null ? p.getActivityDateDeadline().toString() : "-");
                f.add(p.getCreateUid() != null ? p.getCreateUid().toString() : "-");
                f.add(p.getCreateDate() != null ? p.getCreateDate().toString() : "-");
                f.add(p.getWriteUid() != null ? p.getWriteUid().toString() : "-");
                f.add(p.getWriteDate() != null ? p.getWriteDate().toString() : "-");
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * Write Product Information
             */
            IntStream.range(0, fields.size()).forEach(i -> {
                sheet.setColumnWidth(i, fields.get(i).length() * 1000);
                this.createCell(fields.get(i), i, header, headerStyle);

                this.createCell(f.get(i), i, row, style);
            });
        }

        workbook.write(fos);
        workbook.close();
        fos.close();
        return String.format("%s products saved in %s of application directory", data.size(), path);
    }


    private Cell createCell(String text, int cell, Row row, CellStyle style) {
        Cell c = row.createCell(cell);
        c.setCellValue(text);
        c.setCellStyle(style);

        return c;
    }
}
