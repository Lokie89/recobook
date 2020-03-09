package main;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String saveUrl = "C:\\Users\\traeu\\Desktop\\www";
        final String keyword = "심리";
        final int limit = 150;
        final int startPage = 1;
        SaveImageFile saveImageFile = new SaveImageFile(saveUrl);
        saveImageFile.generateFile(new GetBookHtml(new GetBookIds(keyword, limit, startPage).getIds()).getSrcs());
    }
}
