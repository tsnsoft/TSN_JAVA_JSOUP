package tsn_java_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TSN_JAVA_JSOUP {

    public static void main(String[] args) throws IOException {
        try {
            String BASE_URL = "http://kazfin.info/kazakhstan"; // Адрес с котировками
            StringBuilder data = new StringBuilder();
            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get(); // Создание документа JSOUP из html
            data.append("Курсы валюты:\n"); // Считываем заголовок страницы
            data.append(String.format("%12s %12s %12s %12s\n", "валюта", "наименование", "курс", "изменение"));
            Elements e = doc.select("div.exchange"); // Ищем в документе "<div class="exchange"> с данными о валютах
            Elements tables = e.select("table"); // Ищем таблицы с котировками
            Element table = tables.get(0); // Берем 1 таблицу для физических лиц
            // Цикл по строкам таблицы
            int i = 0;
            for (Element row : table.select("tr")) { // Пропускаем 1 строку с заголовком (eq - конкретная , lt - ниже, gt - выше)
                // Цикл по столбцам таблицы
                for (Element col : row.select("td")) { //
                    data.append(String.format("%12s ", col.text())); // Считываем данные с ячейки таблицы
                }
                if (i++ > 2) {
                    break;
                } else {
                    data.append("\n"); // Добавляем переход на следующую строку;
                }
            }
            System.out.println(data.toString());
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}
