package com.taotao.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;


/**
 * 图片上传服务
 * <p>Title: PictureServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2019年9月19日下午3:25:46
 * @version 1.0
 */
@Service
public class PictureServiceImpl implements PictureService {

	// 配置文件IP地址
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	// 配置文件端口号
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	// 配置文件用户名
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	// 配置文件密码
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	// 配置文件务器存放图片路径
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	
	// 配置文件服务器IP
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
		
	
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try 
		{
		// 生成一个新的文件名
		// 取原始文件名
		String oldName = uploadFile.getOriginalFilename();
		// 生成新文件名
		//UUID.randomUUID();
		String newName = IDUtils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		// 图片上传
		String imagePath = new DateTime().toString("/yyyy/MM/dd");
		boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, 
				imagePath, newName, uploadFile.getInputStream());
		
		
		// 返回结果
		if(!result){
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
			return resultMap;
		}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
			return resultMap;
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常！");
			return resultMap;
		}
		
	}
}