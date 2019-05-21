package com.neo.controller;

import com.neo.entity.CodeInfo;
import com.neo.remote.HelloRemote;
import com.neo.service.ConsumerService;
import com.neo.unit.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = {"demo接口1111"})
@RestController
@RequestMapping("/influx")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private HelloRemote helloRemote;

    @ApiOperation(value = "新增")
    @PostMapping("/insert")
    public Map<String, Object> index(@RequestBody List<CodeInfo> list) {
        consumerService.add(list);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("massege", "成功");
        resultMap.put("code", "200");
        return resultMap;
    }

    @ApiOperation(value = "查询")
    @GetMapping("/get")
    public Result<CodeInfo> getMassege() {
        List<CodeInfo> lists = consumerService.getCodeInfo();
        Result<CodeInfo> result = new Result(200, "", lists);
        return result;
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Map<String, Object> delete(String tagCode) {
        consumerService.delete(tagCode);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("massege", "");
        resultMap.put("code", "200");
        return resultMap;
    }

    @ApiOperation(value = "Figin测试")
    @GetMapping("/figin")
    public String figin(){
        return helloRemote.hello();
    }

    private List<CodeInfo> getQueryData(List<String> columns,
                                        List<List<Object>> values) {
        List<CodeInfo> lists = new ArrayList<CodeInfo>();
        for (List<Object> list : values) {
            CodeInfo info = new CodeInfo();
            BeanWrapperImpl bean = new BeanWrapperImpl(info);
            for (int i = 0; i < list.size(); i++) {
                String propertyName = setColumns(columns.get(i));// 字段名
                Object value = list.get(i);// 相应字段值
                bean.setPropertyValue(propertyName, value);
            }
            lists.add(info);
        }
        return lists;
    }

    private String setColumns(String column) {
        String[] cols = column.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cols.length; i++) {
            String col = cols[i].toLowerCase();
            if (i != 0) {
                String start = col.substring(0, 1).toUpperCase();
                String end = col.substring(1).toLowerCase();
                col = start + end;
            }
            sb.append(col);
        }
        return sb.toString();
    }

}