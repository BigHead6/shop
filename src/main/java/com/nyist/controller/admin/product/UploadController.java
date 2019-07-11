package com.nyist.controller.admin.product;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.nyist.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/4 9:09
 * @description:上传图片
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class UploadController {
    @PostMapping("/upload")
    @ResponseBody
    public ResultVo uplaod(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
        //1. 接受上传的文件  @RequestParam("file") MultipartFile file
        //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        try {
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;

            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）

            File destFile = new File(destFileName);
            if (!destFile.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                destFile.getParentFile().mkdirs();
            }
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResultVo(0,e.getMessage(),null);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultVo(0,e.getMessage(),null);
        }
        return new ResultVo(1,"上传成功",fileName);
    }
}
