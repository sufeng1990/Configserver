package com.neo.service;

import com.neo.entity.CodeInfo;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 苏峰 on 2019/5/17.
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private String measurement = "aaaa";
    private String database = "my_db";
    @Autowired
    private InfluxDB influxDB;


    @Override
    public void add(List<CodeInfo> list) {
        Map<String, String> tags = new HashMap<String, String>();
        Map<String, Object> fields = new HashMap<String, Object>();
        for (CodeInfo info : list) {
            tags.put("TAG_CODE", info.getCode());
            tags.put("TAG_NAME", info.getName());
            fields.put("ID", info.getId());
            fields.put("FLAG", info.getFlag());
            fields.put("NAME", info.getName());
            fields.put("CODE", info.getCode());
            fields.put("DESCR", info.getDescr());
            fields.put("DESCR_E", info.getDescrE());
            fields.put("CREATED_BY", info.getCreatedBy());
            fields.put("CREATED_AT", info.getCreatedAt());
            fields.put("DATE", info.getDate());
            Point.Builder builder = Point.measurement(measurement);
            builder.tag(tags);
            builder.fields(fields);
            influxDB.write(database, "", builder.build());
        }
    }

    @Override
    public void delete(String tagCode) {
        String command = "delete from aaaa where TAG_CODE='" + tagCode+"'";
        QueryResult result = influxDB.query(new Query(command, database));
    }

    @Override
    public List<CodeInfo> getCodeInfo() {
        String command = "select * from aaaa";
        QueryResult results = influxDB.query(new Query(command, database));
        List<CodeInfo> lists = new ArrayList<CodeInfo>();
        for (QueryResult.Result result : results.getResults()) {
            List<QueryResult.Series> series = result.getSeries();
            for (QueryResult.Series serie : series) {
                // Map<String, String> tags = serie.getTags();
                List<List<Object>> values = serie.getValues();
                List<String> columns = serie.getColumns();
                lists.addAll(getQueryData(columns, values));
            }
        }
        return lists;
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
