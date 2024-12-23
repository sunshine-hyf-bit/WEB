package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSFileViewer {
    public static void main(String[] args) {
        // 设置 HDFS 配置
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.1.7:9000");  // 配置 HDFS 地址（修改为您的 HDFS 地址）

        try {
            // 获取 HDFS 文件系统实例
            FileSystem fs = FileSystem.get(configuration);

            // 要查看的 HDFS 路径（例如: "/user/hadoop/"）
            Path path = new Path("/user/hadoop/");

            // 获取路径下的文件状态
            FileStatus[] fileStatuses = fs.listStatus(path);

            // 列出文件和目录
            if (fileStatuses != null) {
                for (FileStatus fileStatus : fileStatuses) {
                    System.out.println("File: " + fileStatus.getPath().toString());
                    // 判断是否是目录
                    if (fileStatus.isDirectory()) {
                        System.out.println("This is a directory.");
                    } else {
                        System.out.println("This is a file.");
                    }
                }
            }

            // 关闭文件系统连接
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

