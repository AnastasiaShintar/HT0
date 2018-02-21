package com.epam.training.ht0.prj2;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MP3Visitor extends SimpleFileVisitor<Path> {

    private Map<String, Map<String, List<String>>> mapHTML = new HashMap<>(); // Структурируем информацию об mp3 для записи в HTML
    private Map<String, List<Path>> md5Duplicates = new HashMap<>();        // Для поиска дубликатов по контрольной сумме
    private Map<String, List<Path>> sameNames = new HashMap<>();      // Для схожих по названию
    private int counter = 0;


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().toLowerCase().endsWith(".mp3")) {
            try {
                Mp3File mp3File = new Mp3File(file);  // Обертка над mp3-файлом позволит получить нужные атрибуты
                if (mp3File.hasId3v2Tag()) {         // Работаем со второй версией формата тегов

                    counter++;

                    putMapHTML(mp3File, file);
                    putMd5Duplicates(file);
                    putSameNamesMap(mp3File.getId3v2Tag(), file);
                }

            } catch (UnsupportedTagException | InvalidDataException e) {
                System.out.println("Из такого файла мы необходимую информацию не получим!");
                e.printStackTrace();
            }
        }
        return FileVisitResult.CONTINUE;
    }

    private void putSameNamesMap(ID3v2 id3v2Tag, Path file) {
        String artist = id3v2Tag.getArtist();
        String album = id3v2Tag.getAlbum();
        String title = id3v2Tag.getTitle();
        String comparableInfo = String.format("Исполнитель %s, Альбом %s, Композиция %s:", artist, album, title);

        if (sameNames.containsKey(comparableInfo)) {
            sameNames.get(comparableInfo).add(file);
        } else {
            List<Path> sameInfo = new ArrayList<>();
            sameInfo.add(file);
            sameNames.put(comparableInfo, sameInfo);
        }
    }

    private void putMd5Duplicates(Path file) {
        byte[] md5Byte = Hash.MD5.checksum(file.toFile());   // Получение контрольной суммы файла
        String md5Hex = toHex(md5Byte);                      // Приведение к 16-й форме
        if (md5Duplicates.containsKey(md5Hex)) {             // Совпадение контрольных сумм
            md5Duplicates.get(md5Hex).add(file);             // Запись дубликатов
        } else {
            List<Path> duplicates = new ArrayList<>();
            duplicates.add(file);
            md5Duplicates.put(md5Hex, duplicates);           // Для новой контрольной суммы
        }
    }

    private void putMapHTML(Mp3File mp3File, Path file) {
        ID3v2 id3v2Tag = mp3File.getId3v2Tag();      // Получаем необходимые атрибуты mp3
        String artist = id3v2Tag.getArtist();
        String album = id3v2Tag.getAlbum();
        String title = id3v2Tag.getTitle();
        long length = mp3File.getLengthInSeconds();

        String info = new StringBuilder()     // Для тега с подробной инф. об mp3
                .append("    <h3>")
                .append(title)
                .append(" ")
                .append(length)
                .append("sec")
                .append(" (")
                .append(file)
                .append(")</h3>").toString();

        if (mapHTML.containsKey(artist)) {   // Если уже встречали артиста

            if (mapHTML.get(artist).containsKey(album)) {     // Если уже встречали данный альбом
                mapHTML.get(artist).get(album).add(info);     // Тогда дописываем инф. под уже существующим альбомом
            } else {                                        // Встретили новый альбом
                List<String> infos = new ArrayList<>();     // Лист для новых треков
                infos.add(info);
                mapHTML.get(artist).put(album, infos); // Записали новый альбом вместе с инф. о новых треках
            }

        } else {                                      // Если встретили нового артиста, дописываем новую информацию под нового
            List<String> infos = new ArrayList<>();
            infos.add(info);
            Map<String, List<String>> albums = new HashMap<>();
            albums.put(album, infos);
            mapHTML.put(artist, albums);
        }

    }

    private String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    public Map<String, Map<String, List<String>>> getMapHTML() {
        return mapHTML;
    }

    public Map<String, List<Path>> getMd5Duplicates() {
        return md5Duplicates;
    }

    public Map<String, List<Path>> getSameNames() {
        return sameNames;
    }

    public int getCounter() {
        return counter;
    }
}
