package com.github.datalking.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 文件资源操作 工具类
 *
 * @author yaoo on 4/9/18
 */
public class ResourceUtils {

    /**
     * 获取指定包下的所有class对象
     *
     * @param fullyQualifiedPack 包权限定名
     * @param recursive          是否递归搜索子包
     * @return 包下所有类
     */
    public static Set<Class> getAllClassFromPackage(String fullyQualifiedPack, boolean recursive) {

        Set<Class> clazzs = new HashSet<>();
        String packageName = fullyQualifiedPack;
        // 包名转换成路径
        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    //System.out.println("文件是file");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

                    // 所有filePath目录的文件
                    getClassInPackageByFile(packageName, filePath, recursive, clazzs);
                } else if ("jar".equals(protocol)) {
                    //System.out.println("jar类型的扫描");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazzs;
    }


    public static void getClassInPackageByFile(String packageName, String filePath, final boolean recursive, Set<Class> clazzs) {

        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();
                boolean acceptClass = file.getName().endsWith("class");
                return acceptDir || acceptClass;
            }
        });

        if (dirFiles == null) {
            return;
        }

        for (File file : dirFiles) {

            /// 如果是目录，则递归寻找.class文件
            if (file.isDirectory()) {
                getClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
            }
            /// 如果不是目录，则加载类，加入set
            else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public Set<Class> getClasssFromJarFile(String jarPaht, String filePaht) {
        Set<Class> clazzs = new HashSet<Class>();

        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPaht);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Set<JarEntry> jarEntryList = new HashSet<JarEntry>();

        Enumeration<JarEntry> ee = jarFile.entries();
        while (ee.hasMoreElements()) {
            JarEntry entry = (JarEntry) ee.nextElement();
            // 过滤我们出满足我们需求的东西
            if (entry.getName().startsWith(filePaht) && entry.getName().endsWith(".class")) {
                jarEntryList.add(entry);
            }
        }
        for (JarEntry entry : jarEntryList) {
            String className = entry.getName().replace('/', '.');
            className = className.substring(0, className.length() - 6);

            try {
                clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return clazzs;

    }


}
