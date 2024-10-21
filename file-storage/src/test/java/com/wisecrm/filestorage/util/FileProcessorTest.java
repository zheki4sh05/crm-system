package com.wisecrm.filestorage.util;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void createPath() {

        List<String> props = Arrays.asList(String.valueOf(1L),
                "Категория",
                "file.txt"
        );
        String path = FileProcessor.createPath(props, "/");

        assertEquals(path, "1/Категория/file.txt");

    }
}