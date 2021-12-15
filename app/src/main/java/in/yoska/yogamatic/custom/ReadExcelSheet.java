package in.yoska.yogamatic.custom;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.yoska.yogamatic.data.model.YogData;

import static android.content.ContentValues.TAG;

public class ReadExcelSheet {
    String fileName;

    public void setFileName(String inputFile){
        this.fileName = inputFile;
    }

    public ArrayList<YogData> readSheet(Context context) throws IOException {

        ArrayList<YogData> importedExcelData = new ArrayList<YogData>();
        InputStream fileInputStream = null;

        Workbook workbook;
        try {
            AssetManager assetMgr = context.getAssets();

            fileInputStream = assetMgr.open(fileName);
            workbook = new HSSFWorkbook(fileInputStream);

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through each row
            for (Row row : sheet) {
                int index = 0;
                List<String> rowDataList = new ArrayList<>();

                if (row.getRowNum() > -1) {
                    // Iterate through all the cells in a row
                    Iterator<Cell> cellIterator = row.cellIterator();
                    //inside a row, one cell at a time
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        // Check cell type and format accordingly

                        if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                            rowDataList.add(index, cell.getStringCellValue());
                            index++;
                        }
                    }
                    // Adding cells data to object
                    if (rowDataList.size() == 5) {
                        importedExcelData.add(getYogaDataObject(rowDataList));
                    }else{
                        System.out.println("Error! Row data - " + String.valueOf(index));
                    }

                }

            }
//            System.out.println(importedExcelData.toString());
            return importedExcelData;

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
        return importedExcelData;
    }

    public YogData getYogaDataObject(List<String> dataList){

        YogData dataObject = new YogData();
        dataObject.setCategory(dataList.get(0));
        dataObject.setAilment(dataList.get(1));
        dataObject.setType(dataList.get(4));
        dataObject.setImageName(dataList.get(2));
        dataObject.setDescription(dataList.get(3));

        return dataObject;

    }

}
