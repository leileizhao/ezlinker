package com.ezlinker.common.utils;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: ezlinker
 * @description: 系统参数获取工具
 * @author: wangwenhai
 * @create: 2019-11-13 09:56
 **/
public class SystemPropertiesUtil {
    public static Map<String, Object> getSystemProperties() {
        Properties properties = System.getProperties();
        Map<String, Object> data = new HashMap<>(16);
        data.put("javaVersion", properties.getProperty("java.vm.version"));
        data.put("jvmVendor", properties.getProperty("java.vm.vendor"));
        data.put("os", properties.getProperty("os.name"));
        data.put("osVersion", properties.getProperty("os.version"));
        data.put("cpuArc", properties.getProperty("sun.cpu.isalist"));
        return data;
    }

    public static Map<String, Object> getRunning() {

        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        long vmTotal = rt.totalMemory() / byteToMb;
        long vmFree = rt.freeMemory() / byteToMb;
        long vmMax = rt.maxMemory() / byteToMb;
        long vmUse = vmTotal - vmFree;
        OperatingSystemMXBean mxBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        long physicalFree = mxBean.getFreePhysicalMemorySize() / byteToMb;
        long physicalTotal = mxBean.getTotalPhysicalMemorySize() / byteToMb;
        long physicalUse = physicalTotal - physicalFree;

        Map<String, Object> data = new HashMap<>(16);

        data.put("vmUse", vmUse);
        data.put("vmFree", vmFree);
        data.put("vmTotal", vmTotal);
        data.put("vmMax", vmMax);
        data.put("physicalFree", physicalFree);
        data.put("physicalTotal", physicalTotal);
        data.put("physicalUse", physicalUse);
        return data;

    }
}
