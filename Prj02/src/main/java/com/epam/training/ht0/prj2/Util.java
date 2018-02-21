package com.epam.training.ht0.prj2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Util {

    public final static String RESULT_HTML = "result.html";

    private static Logger logDuplicates = LogManager.getLogger("Duplicates"); // Логеры на разные файлы
    private static Logger logNames = LogManager.getLogger("Names");


    public static void writeHTML(Map<String, Map<String, List<String>>> mapHTML) {

        try (FileWriter fw = new FileWriter(new File(RESULT_HTML))) {
            fw.write("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n<title>Title</title>\n</head>\n\n<body>\n\n");  // Заполняем хедер

            for (Map.Entry<String, Map<String, List<String>>> artist : mapHTML.entrySet()) { // Идем по артистам
                fw.write("<div><h1>" + artist.getKey() + "</h1>\n");    // Пишем артиста

                for (Map.Entry<String, List<String>> album : artist.getValue().entrySet()) {  // Идем по альбомам
                    fw.write("  <div><h2>" + album.getKey() + "</h2>\n");                 // Пишем альбом

                    for (String info : album.getValue()) {    // Идем по трекам и другой информации
                        fw.write(info + "\n");
                    }
                    fw.write("  </div>\n");
                }
                fw.write("</div>\n\n");
            }

            fw.write("</body>\n</html>");
        } catch (IOException e) {
            System.out.println("Невозможно произвести запись " + e.getMessage());
        }
    }

    public static void logDuplicates(Map<String, List<Path>> md5Duplicates) {
        int duplicateGroupCount = 1;                                                            // Для указания порядкового номера группы дубликатов
        for (Map.Entry<String, List<Path>> duplicatorsGroup : md5Duplicates.entrySet()) {
            if (duplicatorsGroup.getValue().size() > 1) {                                 // Проверяем наличие под одной конрольной суммой нескольких файлов
                logDuplicates.info("Дубликаты-" + duplicateGroupCount++ + ":");
                for (Path duplicatePath : duplicatorsGroup.getValue()) {             // Цикл по дубликатам
                    logDuplicates.info("+ " + duplicatePath);
                }
                logDuplicates.info("");
            }
        }
    }

    public static void logSameNames(Map<String, List<Path>> sameNames) {
        for (Map.Entry<String, List<Path>> sameNamesGroup : sameNames.entrySet()) {
            if (sameNamesGroup.getValue().size() > 1) {
                logNames.info(sameNamesGroup.getKey());
                for (Path samePath : sameNamesGroup.getValue()) {
                    logNames.info("+ " + samePath);
                }
                logNames.info("");
            }
        }
    }
}
