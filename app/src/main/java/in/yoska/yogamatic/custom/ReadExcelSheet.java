package in.yoska.yogamatic.custom;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class ReadExcelSheet {
    String fileName;
    Workbook workbook = new HSSFWorkbook();

    public void setFileName(String inputFile){
        this.fileName = inputFile;
    }

    public void readSheet(Context context) throws IOException {

        ArrayList importedExcelData = new ArrayList<>();

//        File file = new File(context.getExternalFilesDir(null), fileName);
        File file = new File(fileName);
        InputStream fileInputStream = null;

        Workbook workbook;
        try {
            AssetManager assetMgr = context.getAssets();

//            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(fileName);
//            FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
//            FileInputStream stream = new FileInputStream(fileDescriptor);

//            OPCPackage pkg = OPCPackage.open(new File(fileName));
//            XSSFWorkbook wb = new XSSFWorkbook(pkg);

            fileInputStream = assetMgr.open(fileName);
            workbook = new HSSFWorkbook(fileInputStream);

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {
                    // Iterate through all the cells in a row (Excluding header row)
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        // Check cell type and format accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                // Print cell value
                                System.out.println(cell.getNumericCellValue());
                                break;

                            case Cell.CELL_TYPE_STRING:
                                System.out.println(cell.getStringCellValue());
                                break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            Log.e(TAG, "Error Reading Exception: ", e);
        } catch (Exception e) {
            Log.e(TAG, "Failed to read file due to Exception: ", e);
        } finally {
            try {
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
