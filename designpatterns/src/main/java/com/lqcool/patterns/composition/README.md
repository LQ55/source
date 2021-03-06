### 组合模式

**简介：** </br>
组合模式（Composite Pattern），又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次。这种类型的设计模式属于结构型模式，它创建了对象组的树形结构。
这种模式创建了一个包含自己对象组的类。该类提供了修改相同 对象组的方式。

**应用与作用：** </br>
它在我们树型结构的问题中，模糊了简单元素和复杂元素的概念，客户程序可以向处理简单元素一样来处理复杂元素，从而使得客户程序与复杂元素的内部结构解耦,组合模式使得用户对单个对象和组合对象的使用具有一致性。
组合模式让你可以优化处理递归或分级数据结构。有许多关于分级数据结构的例子，使得组合模式非常有用武之地。关于分级数据结构的一个普遍性的例子是你每次使用电脑时所遇到的:文件系统。文件系统由目录和文件组成。每个目录都可以装内容。目录的内容可以是文件，也可以是目录。按照这种方式，计算机的文件系统就是以递归结构来组织的。如果你想要描述这样的数据结构，那么你可以使用组合模式Composite。

**设计的角色：** </br>
- Component是组合中的对象声明接口，在适当的情况下，实现所有类共有接口的默认行为。声明一个接口用于访问和管理Component子部件。
- Leaf 在组合中表示叶子结点对象，叶子结点没有子结点。
- Composite 定义有枝节点行为，用来存储子部件，在Component接口中实现与子部件有关操作，如增加(add)和删除(remove)等。

**使用场景：**
- 您想表示对象的部分-整体层次结构（树形结构：部分、整体场景，如树形菜单，文件、文件夹的管理。）。
- 您希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。


参考文献：</br>
[组合模式-百度百科](http://baike.baidu.com/link ?url=Ya5mjVEkWCIknSlkYJX-FvXlPDNJmsB16m4-d1eXhOM5kMn0phNQ8xY_326Y5BgrDWj5MYW5lxnCvL_xZ-5kAP6P_ArKyMKKvVPNWorNvGmVpAxoXib9lpWyJlCqKhO7)