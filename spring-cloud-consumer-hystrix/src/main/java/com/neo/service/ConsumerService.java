package com.neo.service;

import com.neo.entity.CodeInfo;

import java.util.List;

/**
 * Created by 苏峰 on 2019/5/17.
 */
public interface ConsumerService {
    void add(List<CodeInfo> list);
    void delete(String tagCode);
    List<CodeInfo> getCodeInfo();

}
