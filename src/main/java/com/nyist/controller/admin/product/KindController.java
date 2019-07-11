package com.nyist.controller.admin.product;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Kind;
import com.nyist.service.KindService;
import com.nyist.vo.LayuiPageVo;
import com.nyist.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/9 10:33
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class KindController {
    @Autowired
    private KindService kindService;

    /**
     * 获取商品类型信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getKindAll")
    @ResponseBody
    public LayuiPageVo getKindAll(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        LayuiPageVo lpv = new LayuiPageVo();
        PageInfo<Kind> pageInfo = null;
        pageInfo = kindService.pageList(currentPage, pageSize);
        List<Kind> productList = pageInfo.getList();
        lpv.setCode(1);
        lpv.setMsg("获取数据成功");
        lpv.setTotal(pageInfo.getTotal());
        lpv.setData(productList);
        return lpv;
    }

    /**
     * 编辑商品分类信息
     * @param request
     * @return
     */
    @RequestMapping("/editKind")
    @ResponseBody
    public ResultVo editKind(HttpServletRequest request) {
        Integer kindNo = Integer.parseInt(request.getParameter("kindNo"));
        String name = request.getParameter("name");
        Kind kind = new Kind();
        kind.setKindNo(kindNo);
        kind.setKindName(name);
        if (kindService.updateByPrimaryKeySelective(kind) != 0) {
            return new ResultVo(1, "信息更新成功", null);
        } else {
            return new ResultVo(0, "信息更新失败", null);
        }

    }

    /**
     * 添加商品分类信息
     * @param request
     * @return
     */
    @RequestMapping("/addKind")
    @ResponseBody
    public ResultVo addKind(HttpServletRequest request) {
        String name = request.getParameter("name");
        Kind kind = new Kind();
        kind.setKindName(name);
        if (kindService.insertSelective(kind)!=0) {
            return new ResultVo(1, "分类添加成功", null);
        } else {
            return new ResultVo(0, "分类添加失败", null);
        }

    }



    /**
     * 删除商品分类信息
     * @param request
     * @return
     */
    @RequestMapping("/delKind")
    @ResponseBody
    public ResultVo delKind(HttpServletRequest request) {
        int kindNo = Integer.parseInt(request.getParameter("kindNo"));
        Kind kind = new Kind();
        kind.setKindNo(kindNo);
        kind.setFlag("0");
        if (kindService.updateByPrimaryKeySelective(kind)!=0) {
            return new ResultVo(1, "分类删除成功", null);
        } else {
            return new ResultVo(0, "分类删除失败", null);
        }

    }

}
