package com.alan.serviceImpl;

import com.alan.service.TestService;

/**
 * Created by alan on 17/1/9.
 */
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("hi");
    }
}
