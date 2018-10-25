## DES 

FileScanner是扫描文件夹里面的文件的工具类，提供两个工具函数：
```java
 public List<String> getFolderFilesPathWithRecursion(String topFolderPath){}
 public List<String> getFolderFilesPathWithNoRecursion(String topFolderPath){}
```
上面两个函数都是实现讲一个文件夹里面文件扫描出来的类，一个递归版本，一个非递归版本，传入的是顶层文件夹路径，返回一个文件夹里面所有文件路径的集合。