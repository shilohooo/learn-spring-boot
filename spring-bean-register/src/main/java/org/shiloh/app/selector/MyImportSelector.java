package org.shiloh.app.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lxlei
 * @date Created in 2020/1/17 14:31
 * @description
 */
public class MyImportSelector implements ImportSelector {
    
    /**
     * 定义需要导入到IOC容器中组件的全类名数组
     * @author lxlei
     * @param annotationMetadata 通过这个参数可以获取到使用ImportSelector的类的全部注解信息
     * @return java.lang.String[]
     * @date 2020/1/17 14:35
     **/
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 一次性导入较多组件，使用ImportSelector来实现。
        return new String[] {
                "org.shiloh.app.entity.Apple",
                "org.shiloh.app.entity.Banana",
                "org.shiloh.app.entity.Watermelon"
        };
    }
}
