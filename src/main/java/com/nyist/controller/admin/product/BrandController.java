package com.nyist.controller.admin.product;

import com.github.pagehelper.PageInfo;
import com.nyist.dao.WarehouseMapper;
import com.nyist.entity.Warehouse;
import com.nyist.service.WarehouseService;
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
 * @date ：Created in 2019/4/9 11:16
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class BrandController {
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private WarehouseService warehouseService;
    /**
     * 获取品牌信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getWarehouse")
    @ResponseBody
    public LayuiPageVo getWarehouse(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        LayuiPageVo lpv = new LayuiPageVo();
        PageInfo<Warehouse> pageInfo = null;
        pageInfo = warehouseService.pageList(currentPage, pageSize);
        List<Warehouse> productList = pageInfo.getList();
        lpv.setCode(1);
        lpv.setMsg("获取数据成功");
        lpv.setTotal(pageInfo.getTotal());
        lpv.setData(productList);
        return lpv;
    }


    /**
     * 编辑商品品牌信息
     * @param request
     * @return
     */
    @RequestMapping("/editBrand")
    @ResponseBody
    public ResultVo editBrand(HttpServletRequest request) {
        Integer whNo = Integer.parseInt(request.getParameter("whNo"));
        String name = request.getParameter("name");
        Warehouse warehouse = new Warehouse();
        warehouse.setWhNo(whNo);
        warehouse.setWhName(name);
        if (warehouseService.updateByPrimaryKeySelective(warehouse) != 0) {
            return new ResultVo(1, "信息更新成功", null);
        } else {
            return new ResultVo(0, "信息更新失败", null);
        }

    }

    /**
     * 添加商品品牌信息
     * @param request
     * @return
     */
    @RequestMapping("/addBrand")
    @ResponseBody
    public ResultVo addBrand(HttpServletRequest request) {
        String name = request.getParameter("name");
        Warehouse warehouse = new Warehouse();
        warehouse.setWhName(name);
        if (warehouseService.insertSelective(warehouse)!=0) {
            return new ResultVo(1, "品牌添加成功", null);
        } else {
            return new ResultVo(0, "品牌添加失败", null);
        }

    }



    /**
     * 删除商品品牌信息
     * @param request
     * @return
     */
    @RequestMapping("/delBrand")
    @ResponseBody
    public ResultVo delKind(HttpServletRequest request) {
        Integer whNo = Integer.parseInt(request.getParameter("whNo"));
        Warehouse warehouse = new Warehouse();
        warehouse.setWhNo(whNo);
        warehouse.setFlag("0");
        if (warehouseService.updateByPrimaryKeySelective(warehouse)!=0) {
            return new ResultVo(1, "品牌删除成功", null);
        } else {
            return new ResultVo(0, "品牌删除失败", null);
        }

    }

}
