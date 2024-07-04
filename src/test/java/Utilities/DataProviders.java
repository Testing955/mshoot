package Utilities;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="LinkData")
    public Object[][] getLinkData() throws IOException {
        String path = "/Users/hestabit/Downloads/Portfolio_AiMl.xlsx";

        File excelFile = new File(path);
        if (!excelFile.exists()) {
            throw new IOException("File not found at specified path: " + path);
        }

        ExcelUtility xlutil = new ExcelUtility(path);

        int totalRows = xlutil.getRowCount("Portfoliolink");
        int totalCols = xlutil.getCellCount("Portfoliolink", 1);
        
        // Assuming URLs are in the first column (index 0)
        String[][] linkData = new String[totalRows][1];

        for (int i = 1; i <= totalRows; i++) {
            linkData[i-1][0] = xlutil.getCellData("Portfoliolink", i, 0);
        }
        return linkData;
    }
}
