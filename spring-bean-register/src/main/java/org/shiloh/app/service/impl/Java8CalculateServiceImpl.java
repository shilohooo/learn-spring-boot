package org.shiloh.app.service.impl;

import org.shiloh.app.service.CalculateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author lxlei
 * @date Created in 2020/1/17 14:16
 * @description
 */
@Service
@Profile("java8")
public class Java8CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java8环境下执行的求和...");
        return Arrays.stream(values).reduce(0, Integer::sum);
    }
}
