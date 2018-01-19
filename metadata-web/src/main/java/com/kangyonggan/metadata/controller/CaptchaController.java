package com.kangyonggan.metadata.controller;

import com.kangyonggan.metadata.constants.AppConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码生成
 *
 * @author kangyonggan
 * @since 16/6/23
 */
@Controller
@RequestMapping("/")
@Log4j2
public class CaptchaController {

    private static final int WIDTH = 110; // 图片的宽度
    private static final int HEIGHT = 34; // 图片的高度
    private static final int COUNT_CHAR = 4; // 字符个数
    private static final int COUNT_LINE = 30; // 干扰线的数量

    @RequestMapping(value = "captcha", method = RequestMethod.GET)
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 图像
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        // 随机数
        Random random = new Random();
        // 将图像填充为白色
        graphics.setColor(Color.WHITE);
        // 图片位置宽高
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        // 字体
        Font font = new Font("Fixedsys", Font.BOLD, HEIGHT - 6);
        graphics.setFont(font);

        // 随机产生干扰线
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < COUNT_LINE; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int xl = random.nextInt(10);
            int yl = random.nextInt(10);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuilder captcha = new StringBuilder();

        // 随机产生验证码。
        for (int i = 0; i < COUNT_CHAR; i++) {
            // 随机产生验证码 0 ~ 9
            String code = String.valueOf(random.nextInt(10));
            // 随机产生颜色

            // 画验证码
            graphics.setColor(Color.black);
            graphics.drawString(code, (i + 1) * 20, HEIGHT - 6);

            // 拼接验证码
            captcha.append(code);
        }

        // 把验证码保存到session中
        HttpSession session = req.getSession();
        session.setAttribute(AppConstants.KEY_CAPTCHA, captcha.toString());
        log.info("验证码captcha为: {}", captcha);

        // 禁止缓存
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");

        // 输出图像
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }

}
