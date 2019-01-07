### 原型模式

**简介：** </br>
原型模式（Prototype Pattern），原型模式是一种创建型设计模式，Prototype模式允许一个对象再创建另外一个可定制的对象，根本无需知道任何如何创建的细节,当需要大量创建相同或者相似的对象是，可以通过一个已有对象的复制获取更多对象。这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。原型模式实现了Cloneable接口

**模式结构**
 ![原型模式](https://github.com/lqcool/source/blob/master/designpatterns/src/main/resources/images/%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F%E7%BB%93%E6%9E%84.png)

**应用与作用：** </br>
当直接创建对象的代价比较大时，则采用这种模式。例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。

**有点与缺点：** </br>
- 优点：1、性能提高。 2、逃避构造函数的约束。
- 缺点：1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候。 2、必须实现 Cloneable 接口。

**使用场景：**
- 资源优化场景。 
- 类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等。 
- 性能和安全要求的场景。 
- 通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。 
- 一个对象多个修改者的场景。 
- 一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用。 
- 在实际项目中，原型模式很少单独出现，一般是和工厂方法模式一起出现，通过 clone 的方法创建一个对象，然后由工厂方法提供给调用者。原型模式已经与 Java 融为浑然一体，大家可以随手拿来使用。

**相关代码**
```java
    /**
     * 浅克隆方法，该方法返回的对象所有普通成员都与原对象有相同的值，而所有其它对象的引用任然指向原来的对象，而不是复制他所有的成员引用
     * @return
     */
    public Object clone(){
        Object clone = null;
        try{
            clone = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }  
    /**
     * 深度克隆，该方法会深度克隆对象
     * @return
     */
    public Object deepClone() {
        try{
            //将对象写入流中
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            //将对象从流中取出
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (ois.readObject());
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        return null;
    }
    
```


参考文献：</br>
[原型模式-百度百科](https://baike.baidu.com/item/%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F)
[原型模式-菜鸟教程](http://www.runoob.com/design-pattern/prototype-pattern.html)
