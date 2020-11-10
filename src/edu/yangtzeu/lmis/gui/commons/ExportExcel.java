package edu.yangtzeu.lmis.gui.commons;
//

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {
    public void write_data_to_excel() {
        try {
            WritableWorkbook wwb = null;

            // 创建可写入的Excel工作簿
            String fileName = "D://book.xls";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

            //查询数据库中所有的数据
//            List<Stu> list = StuService.getAllByDb();
            //要插入到的Excel表格的行号，默认从0开始
            Label labelId = new Label(0, 0, "编号(id)");//表示第
            Label labelName = new Label(1, 0, "姓名(name)");
            Label labelSex = new Label(2, 0, "性别(sex)");
            Label labelNum = new Label(3, 0, "薪水(num)");

            ws.addCell(labelId);
            ws.addCell(labelName);
            ws.addCell(labelSex);
            ws.addCell(labelNum);
//            for (int i = 0; i < list.size(); i++) {
//
////                Label labelId_i = new Label(0, i + 1, list.get(i).getId() + "");
////                Label labelName_i = new Label(1, i + 1, list.get(i).getName());
////                Label labelSex_i = new Label(2, i + 1, list.get(i).getSex());
////                Label labelNum_i = new Label(3, i + 1, list.get(i).getNum() + "");
////                ws.addCell(labelId_i);
////                ws.addCell(labelName_i);
////                ws.addCell(labelSex_i);
////                ws.addCell(labelNum_i);
//            }

            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            System.out.println("数据导出成功!");
            wwb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
