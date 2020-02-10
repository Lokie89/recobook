package main;

public class Main {
    public static void main(String[] args) {
        final String saveUrl = "C:\\Users\\amoeba\\Desktop\\www";
        final String keyword = "취미";
        final int limit = 50;
        SaveImageFile saveImageFile = new SaveImageFile(saveUrl);
        saveImageFile.generateFile(new GetBookHtml(new GetBookIds(keyword, limit).getIds()).getSrcs());
    }
}
