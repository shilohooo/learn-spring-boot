package org.shiloh.app.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author lxlei
 * @date Created in 2020/1/17 9:57
 * @description
 */
public class MyFilter implements TypeFilter {
    /**
     * 当match方法返回true时说明匹配成功，false则说明匹配失败
     * @author lxlei
     * @param metadataReader 当前正在扫描的类的信息
     * @param metadataReaderFactory 可以通过它来获取其他类的信息
     * @return boolean
     * @date 2020/1/17 9:58
     **/
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前正在扫描的类的注解信息
        AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的类信息
        final ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前正在扫描的类的路径信息
        final Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        // 指定了当被扫描的类名包含er时候，匹配成功，配合excludeFilters使用意指当被扫描的类名包含er时，该类不被纳入IOC容器中
        // 在@ComponentScan注解中可以使用这个自定义的过滤策略
        return StringUtils.hasText("er");
    }
}
