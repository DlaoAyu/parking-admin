package com.laoayu.parking.system.controller;

import com.laoayu.parking.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: LaoAyu
 * @date: 2023/04/04
 **/

/**
 * 图片上传接口：修改图片名称，避免图片名称冲突导致不可上传图片，并返回图片名称
 */

@Api(tags = {"图片上传接口"})
@RestController
@RequestMapping("/file")
public class FileController {


    @ApiOperation("图片上传接口")
    @PostMapping(value = "/upLoad")
    @ResponseBody
    public Result upLoad(@RequestParam(value = "file", required = false) MultipartFile[] multipartFiles){

        StringBuffer sb = new StringBuffer();
        String filePath = "D:\\ParkingManagementSystem\\Parking\\park_pic";
        File fileDir = new File(filePath);

        try {
            // 不传图片，此处捕获空指针异常，代码照常运行
            for(int x = 0; x<multipartFiles.length; x++){
                MultipartFile uploadFile = multipartFiles[x];
                // 存储图片
                File uploadDir = new File(fileDir.getAbsolutePath() + File.separator);
                if ( uploadFile != null) {
                    //获得上传文件的文件名
                    String oldName = uploadFile.getOriginalFilename();
                    System.out.println("[上传的文件名]：" + oldName);
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    String newName = uuid + oldName;
                    System.out.println("[新文件名]：" + newName);
                    if (sb.toString().equals("")){
                        sb.append("http://localhost:9999/pic/"+newName);
                        System.out.println("[图片访问路径]：" + sb.toString());
                    }else {
                        sb.append("|" + newName);
                    }
                    String imagePath = sb.toString();
                    System.out.println(imagePath);
                    File pic = new File(uploadDir, newName);
                    try {
                        //保存图片
                        uploadFile.transferTo(pic);
                        //返回成功结果，附带文件的相对路径
                    }catch (IOException e) {
                        System.out.println("=====捕获异常1=====");
                        e.printStackTrace();
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("=====捕获异常2=====");
            e.printStackTrace();
        }
        // 存储其它数据
        String imagePath = sb.toString();
        return Result.success(imagePath,"图片地址");
    }
}
