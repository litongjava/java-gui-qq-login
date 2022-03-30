package com.ppnt.qq.utils;

import java.net.URL;

import javax.swing.ImageIcon;

import com.litongjava.utils.reflection.ClassPathUtils;

/**
 * @author create by Ping E Lee on 2022年3月30日 下午5:12:25 
 *
 */

public class ImageIconUtils {
  //private static final Logger log = LoggerFactory.getLogger(ImageIconUtils.class);

  public static ImageIcon getImageIcon(String filePath) {
    URL resource = ClassPathUtils.getResource(filePath);
    //log.info("resource:{}", resource);
    return new ImageIcon(resource);
  }
}
