package com.leyou.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    private static  final List<String>  CONTENT_TYPES = Arrays.asList("image/jpeg","image/gif");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public String upload(MultipartFile file) {

        //获取文件名
        String originalFilename = file.getOriginalFilename();

        //检查文件类型
        String contentType = file.getContentType();

        if(!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法{}",originalFilename);
            return null;
        }

        try {
            //效验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null){
                  LOGGER.info("文件内容不合法:{}",originalFilename);
                  return null;
            }

            //保存到服务器
            //file.transferTo(new File("E:\\leyou\\images\\" + originalFilename));

            String ext  = StringUtils.substringAfterLast(originalFilename,".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(),file.getSize(),ext,null);
            return  "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}
