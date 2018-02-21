package com.epam.training.ht0.prj2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) throws IOException {

        Set<String> inputFolders = new HashSet<>(Arrays.asList(args));  // Исключаем повторяющиеся каталоги из коммандной строки
        MP3Visitor visitor = new MP3Visitor();

        System.out.printf("Количетсво каталогов для поиска: %d\n", inputFolders.size());

        for (String folderPathStr : inputFolders) {      // Обход дерва каталогов может начинаться с различных корневых директорий

            Path folderPath = Paths.get(folderPathStr);

            if (Files.isDirectory(folderPath)) {            // Проверка каталога на валидность
                Files.walkFileTree(folderPath, visitor);    // Обход дерева каталогов
            }
        }

        System.out.printf("Всего обработано mp3-файлов: %d\n", visitor.getCounter());

        Util.writeHTML(visitor.getMapHTML());  // Создаём HTML-файл
        System.out.printf("Произведена запись информации в HTML-формате\n");

        Util.logDuplicates(visitor.getMd5Duplicates());  // Логируем дубликаты
        System.out.printf("Произведена запись информации в лог о дубликатах\n");

        Util.logSameNames(visitor.getSameNames());
        System.out.printf("Произведена запись информации в лог о неидентичных файлах с одинаковыми композициями\n");
    }

}
