package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PairsConverter {
    public static void main(String[] s) {
        try {
            List<String> pairCURRENCY_RUB = new ArrayList<>();
            List<String> pairUSD_CURRENCY = Files.readAllLines(Paths.get("D:\\IntelliJ IDEA projects\\SimpleProject\\src\\main\\java\\org\\example\\USDUZS_14072023_14112023.csv"));
            List<String> pairUSD_RUB = Files.readAllLines(Paths.get("D:\\IntelliJ IDEA projects\\SimpleProject\\src\\main\\java\\org\\example\\USDRUB_230714_231114.csv"));

            pairCURRENCY_RUB.add("<TICKER>;<PER>;<DATE>;<TIME>;<OPEN>;<HIGH>;<LOW>;<CLOSE>;<VOL>");

            for (int i = 1; i < Math.min(pairUSD_CURRENCY.size(), pairUSD_RUB.size()); i++) {
                String[] p_ey = pairUSD_CURRENCY.get(i).split(",");
                String[] p_er = pairUSD_RUB.get(i).split(",");

                double open_ar = Double.parseDouble(p_er[4]) / Double.parseDouble(p_ey[4]);
                double close_ar = Double.parseDouble(p_er[7]) / Double.parseDouble(p_ey[7]);
                pairCURRENCY_RUB.add("UZSRUB;D;"+p_er[2] +";0;" + String.format("%.7f",open_ar).replaceAll(",", ".")+";"+
                        ";"+
                        ";"+
                        String.format("%.7f",close_ar).replaceAll(",", ".")+";");

                Files.write(Paths.get("UZSRUB_230714_231114.csv"),pairCURRENCY_RUB);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
