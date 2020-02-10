package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

public class SaveImageFile {
    private String saveUrl;

    public SaveImageFile(String saveUrl) {
        if (!existFolder(saveUrl)) {
            mkdirFolder(saveUrl);
        }
        this.saveUrl = saveUrl;
    }

    private boolean existFolder(String saveUrl) {
        File folder = new File(saveUrl);
        return folder.exists();
    }

    private void mkdirFolder(String saveUrl) {
        File folder = new File(saveUrl);
        folder.mkdir();
    }

//    private BufferedImage getResize(BufferedImage bi, int width, int height) {
//        Image editImage = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics graphics = newImage.getGraphics();
//        graphics.drawImage(editImage, 0, 0, null);
//        graphics.dispose();
//        return newImage;
//    }

    private void saveFile(String imgUrl, String imageName) {
        File outputFile = new File(saveUrl + "/" + imageName + ".jpg");
        try {
            URL url = new URL(imgUrl);
            BufferedImage bufferedImage = ImageIO.read(url);
//            BufferedImage newImage = getResize(bufferedImage, 600, 700);
            ImageIO.write(bufferedImage, "jpg", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateFile(List<String> urls) {
        final int urlsSize = urls.size();
        for (int i = 0; i < urlsSize; i++) {
            String url = urls.get(i);
            String fileName = url.replace("https://bookthumb-phinf.pstatic.net/cover/","");
            fileName = fileName.split("/")[2];
            fileName = fileName.split(".jpg")[0];
            saveFile(url, fileName);
        }

    }
}
