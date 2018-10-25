package com.lqcool.toolsforjava.filescanner;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileScanner {
    /*存放结果扫描结果*/
    private static List<String> resultFilePaths = new ArrayList<String>();
    /**
     * TODO:递归扫描指定文件夹下面的指定文件
     * @return List<String> 文件夹内所有的文件路径集合
     * @author 李桥
     * @time 2017年11月3日
     */
    public static List<String> getFolderFilesPathWithRecursion(String topFolderPath) throws ScanFilesException{
        List<String> resultFilePaths = new ArrayList<String>();
        /*存放临时的文件夹*/
        List <String> dirctorys = new ArrayList<String>();
        File directory = new File(topFolderPath);
        /*传入的路径非文件夹，报错提示*/
        if(!directory.isDirectory()){
            throw new ScanFilesException('"' + topFolderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if(directory.isDirectory()){
            File [] filelist = directory.listFiles();
            for(int i = 0; i < filelist.length; i ++){
                /**如果当前是文件夹，进入递归扫描文件夹**/
                if(filelist[i].isDirectory()){
                    dirctorys.add(filelist[i].getAbsolutePath());
                    /**递归扫描下面的文件夹**/
                    getFolderFilesPathWithRecursion(filelist[i].getAbsolutePath());
                }
                /**非文件夹**/
                else{
                    resultFilePaths.add(filelist[i].getAbsolutePath());
                }
            }
        }
        return resultFilePaths;
    }

    /**
     *
     * TODO:非递归方式扫描指定文件夹下面的所有文件
     * @return ArrayList<Object> 文件夹内所有的文件路径集合
     * @param topFolderPath 需要进行文件扫描的文件夹路径
     * @author 李桥
     * @time 2017年11月3日
     */
    public static List<String> getFolderFilesPathWithNoRecursion(String topFolderPath) throws ScanFilesException{
        File directory = new File(topFolderPath);
        LinkedList<File> queueFiles = new LinkedList<File>();

        if(!directory.isDirectory()){
            throw new ScanFilesException('"' + topFolderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        else{
            //首先将第一层目录扫描一遍
            File [] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for(int i = 0; i < files.length; i ++){
                if(files[i].isDirectory()){
                    queueFiles.add(files[i]);
                }else{
                    //暂时将文件名放入scanFiles中
                    resultFilePaths.add(files[i].getAbsolutePath());
                }
            }

            //如果linkedList非空遍历linkedList
            while(!queueFiles.isEmpty()){
                //移出linkedList中的第一个
                File headDirectory = queueFiles.removeFirst();
                File [] currentFiles = headDirectory.listFiles();
                for(int j = 0; j < currentFiles.length; j ++){
                    if(currentFiles[j].isDirectory()){
                        //如果仍然是文件夹，将其放入linkedList中
                        queueFiles.add(currentFiles[j]);
                    }else{
                        resultFilePaths.add(currentFiles[j].getAbsolutePath());
                    }
                }
            }
        }
        return resultFilePaths;
    }
}
