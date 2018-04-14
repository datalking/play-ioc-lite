package com.github.datalking.context.annotation;

import com.github.datalking.annotation.meta.AnnotationAttributes;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yaoo on 4/14/18
 */
public class ComponentScanAnnotationParser {


    private final BeanDefinitionRegistry registry;

    public ComponentScanAnnotationParser(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    /**
     * 解析@ComponentScan注解
     *
     * @param componentScan  注解信息
     * @param declaringClass 类全限定名
     * @return BeanDefinitionHolder集合
     */
    public Set<BeanDefinitionHolder> parse(AnnotationAttributes componentScan, final String declaringClass) {
        // 创建待扫描的包 集合
        Set<String> basePackages = new LinkedHashSet<>();

        // 获取@ComponentScan注解中配置的扫描包
        String[] basePackagesArray = componentScan.getStringArray("basePackages");
        String delimiter = "";

        // 将basePackages配置的多个包用 逗号/分号 分隔 todo 更优雅的解决方法
        for (String pkg : basePackagesArray) {
            delimiter = validateDelimiter(pkg);
            if (delimiter.equals("")) {
                basePackages.add(pkg);
            } else {
                basePackages.addAll(Arrays.asList(pkg.split(delimiter)));
            }
        }

//        for (Class<?> clazz : componentScan.getClassArray("basePackageClasses")) {
//            // 获取类所在的包全名
//            basePackages.add(ClassUtils.getPackageName(clazz));
//        }


        // 若未配置扫描的包，则将当前类所在的包作为待扫描的包
        if (basePackages.isEmpty()) {
            basePackages.add(getPackageOfClass(declaringClass));
        }

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);

//        scanner.addExcludeFilter(new AbstractTypeHierarchyTraversingFilter(false, false) {

        // 执行扫描，寻找@Component注解标注的类，创建BeanDefinition并注册
        return scanner.doScan(basePackages.toArray(new String[basePackages.size()]));

    }


    /**
     * 获取所配置的多个包的分隔符
     *
     * @return 分隔符
     */
    private String validateDelimiter(String multiPack) {

        // 只能使用1种分隔符
        if (multiPack.contains(",") && multiPack.contains(";")) {
            try {
                throw new Exception("多个包的分隔符只能使用 , 或 ; 中的一种");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (multiPack.contains(",")) {
            return ",";
        }

        if (multiPack.contains(",")) {
            return ";";
        }

        // 默认为空，代表只有1个包
        return "";
    }


    private String getPackageOfClass(String fullClassName) {

        int dotIdx = fullClassName.lastIndexOf(".");
        if (dotIdx == -1) {
            try {
                throw new Exception("类必须在包中，且全限定名中要含有.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fullClassName.substring(0, dotIdx);

    }

}
