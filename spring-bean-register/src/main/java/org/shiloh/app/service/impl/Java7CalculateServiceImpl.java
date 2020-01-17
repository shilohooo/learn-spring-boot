package org.shiloh.app.service.impl;

import org.shiloh.app.service.CalculateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author lxlei
 * @date Created in 2020/1/17 14:16
 * @description
 */
@Service
@Profile("java7")
public class Java7CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... values) {
        System.out.println("java7环境执行的求和...");
        int result = 0;
        for (int i = 0; i <= values.length; i++) {
            result += i;
        }
        return result;
    }
}
