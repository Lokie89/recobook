package main;

public class Main {
    public static void main(String[] args) {
        final String saveUrl = "C:\\Users\\traeu\\Desktop\\test";
        final String keyword = "연애";
        final int limit = 50;
        final int startPage = 1;
        SaveImageFile saveImageFile = new SaveImageFile(saveUrl);
        saveImageFile.generateFile(new GetBookHtml(new GetBookIds(keyword, limit, startPage).getIds()).getSrcs());
    }
}
