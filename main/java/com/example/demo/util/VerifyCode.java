package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {

    private int width = 70;
    private int height = 35;
    private Random r = new Random();

    private String[] font = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
    private String code = "234567890abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Color bgcolor = new Color(255,255,255);

    private String text;

//    public static void main(String[] args) throws IOException {
//        String code = runVerifyCode();
//        System.out.println(code);
//    }

    public static String runVerifyCode() throws IOException{
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.output(verifyCode.getImage(),new FileOutputStream("/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/test.jpg"));
//        verifyCode.output(verifyCode.getImage(),new FileOutputStream("/home/lighthouse/java/novel/static/images/test.jpg"));
        String emailCode = verifyCode.getText();
        return emailCode;
    }

    public void output(BufferedImage image,OutputStream out) throws IOException{
        ImageIO.write(image,"JPEG",out);
    }

    public Color randomColor(){
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red,green,blue);
    }

    public Font randomFont(){
        int index = r.nextInt(font.length);
        String fontName = font[index];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 24;
        return new Font(fontName,style,size);
    }

    public void drawLine(BufferedImage image){
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0;i < num;i++){
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE);
            g2.drawLine(x1,y1,x2,y2);
        }
    }

    public char randomChar(){
        int index = r.nextInt(code.length());
        return code.charAt(index);
    }

    public BufferedImage createImage(){
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgcolor);
        g2.fillRect(0,0,width,height);
        return image;
    }

    public BufferedImage getImage(){
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i < 4;i++){
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * width / 4;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s,x,height - 5);
        }

        this.text = sb.toString();
        drawLine(image);
        return image;

    }
    public String getText(){
        return text;
    }


}
