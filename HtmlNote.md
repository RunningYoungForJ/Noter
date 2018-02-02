# HTM-Note

[TOC]

## Lesson1

> 初步认识HTML，并学习几个常用标签。

1. HTML代码基本结构

   ```html
   <!DOCTYPE html>
   <html lang="zh_cn">
   <head>
     <meta charset="utf-8">
     <meta content="这是一个你穿了就不想脱的衣服" name="description">
     <title>淘宝网，淘</title>
   </head>
   <body>
   </body>
   </html>
   ```

   - 在head标签中，主要的内容是用于浏览器解析该网页的信息。
   - 在body标签中，主要用于展示需要给用户的内容。

2. 几个特殊属性介绍

   - lang：lang属性用于告诉搜索引擎，我们的网站是关于什么内容的，方便搜索引擎爬虫搜索。
   - charset：charset标签用于设置网页的编码字符集，常见的字符集有gb2312、gbk、utf-8等，解决乱码格式一般从这里入手。

   > 字符集介绍
   > ​    gb2312：包含亚裔简体字符集，不能识别繁体字符集。
   > ​    gbk：gb2312的扩展版本，包含了繁体字符集。
   > ​    utf-8：8比特版本。

3. Lesson1学习的标签

   - <!-- p -->标签：段落标签，在该标签中的内容会独立成段显示。
   - <!--  strong -->标签：加粗标签。
   - <!--em -->标签：斜体标签。
   - <!-- del -->标签：中划线标签，标签中的内容中间会有划线。
   - <!-- h1~6 -->标签：标题标签，标签中的内容会比正文加粗，字号更大，并且独立成段。
   - <!-- address -->标签：地址标签，但很少这么直接去使用这个标签显示地址，一般都在js中做优化。
   - <!-- div -->标签：块标签，默认多个div标签的布局是上下的。而且具有捆绑管理的特点，能够统一管理块内的所有标签内容。
   - <!-- span -->标签：与div标签类似，但默认的布局不是上下的。但也具有捆绑管理的特点。

   > div和span标签作为HTML语言中的容器标签，使用它们能够对网页进行分区分块操作，并且能够对区块内的标签元素等内容进行统一管理。

4. Lesson1学习代码

```html
<!DOCTYPE html>
<!--lang标签作用：告诉搜索引擎，我们的网站是关于什么内容的
 -->
<html lang="zh_cn">
<head>
  <!--字符集介绍
    gb2312：包含亚裔简体字符集，不能识别繁体字符集
    gbk：gb2312的扩展版本，包含了繁体字符集
    utf-8：8比特版本
    unicode
  -->
  <meta charset="utf-8">
  <meta content="这是一个你穿了就不想脱的衣服" name="description">
  <title>淘宝网，淘</title>
</head>
<body>
  Life is shit!!!美好的生活
  <strong>加粗</strong>
  <em>斜体</em>
  <strong>
    <em>举个例子</em>
  </strong>
  <del>中划线标签50</del>
  <address>山西大学</address>
  <div>举个例子</div>
  <div>举个例子</div>
  <div>举个例子</div>
  <span>span</span><span>span</span><span>span</span>
</body>
</html>
```

## Lesson2

> 学习剩余的HTML标签，并了解其基本用法。

1. 几个常用的HTML特殊编码

   - 空格分隔符：&lt;&nbsp；&gt;，空格在HTML中的作用是做为文字分隔符。在HTML的内容中，如果多个单词之间没有空格进行分隔，在页面展示中，HTML并不能区分出每个单词之间的界限在哪里，因此在显示的时候会将其做为一个单词显示，显示在一行上，如果这样的单词位于div或者span中，那么会有越界的可能。

   ```html
   <!DOCTYPE html>
   <html>
   <head>
     <title></title>
   </head>
   <body>
     	<!-- 在第一个div中，如果该块的长宽设置的比较小，但由于内容没有进行空格分隔，在最终展示上，HTML认为这只是一个单词，会将内容显示在一行上，有可能会越出div的边界。 -->
       <div>
         applebookwaterlookbananahelpkeepseewatchstopaccesss
       </div>
     	<!-- 在第二个div中，单词之间都有空格分隔符分隔，因此HTML在展示的时候，会判断出有几个单词，并根据单词个数和div块大小自动进行换行，不会越界。 -->
     	<div>   			apple&nbsp;book&nbsp;water&nbsp;look&nbsp;banana&nbsp;help&nbsp;keep&nbsp;see&nbsp;watch&nbsp;stop&nbsp;accesss
       </div>
   </body>
   </html>
   ```

   > 注意：对于英文，必须要用&nbsp;空格进行文本分隔操作；但在中文内容下，不需要强制添加&nbsp;空格。

   - 如何把&lt;div&gt;原样输出：&lt；和&gt；
     - 左标签：左标签实际是一个小于号，因此，在HTML中，如果需原样输出的话，可以使用&lt；的格式，其中&和；是固定语法结构，而lt是less than的缩写。
     - 右标签：类似，右标签是一个大于号，原样输出使用&gt；，其中gt是great than的缩写。
   - 回车换行符：&lt;br&gt;,特殊的是，它是一个单标签，不是一个HTML特殊编码。多个&lt;br&gt;表示多个换行。

   > 单标签，双标签：每一个标签的意义在于把它包裹的文本修饰成需要的样子，但有些标签自己代表功能，不需要在它其中包裹修饰什么内容。这样的自己代表功能，没有包裹的叫做单标签。

2. 有序标签：&lt;ol&gt;&lt;li&gt;&lt;/li&gt;&lt;/ol&gt;

   ```html
   <!-- 以阿拉伯数字排序，逆序排序，从第二个序号开始 -->
   <ol type="1" reversed="reversed" start="2">
       <li>marvel</li>
       <li>su 8</li>
       <li>返老还童</li>
       <li>marvel</li>
     </ol>
   ```

   - type属性：指定排序的序号样式，可以有五种选择，1/a／A／i／I，其实i／I是表示以大小写罗马数字去排序。
   - reversed属性：指定逆序排序。
   - start属性：指定第一个排序序号从几开始。

3. 无序标签：&lt;ul&gt;&lt;li&gt;&lt;/li&gt;&lt;/ul&gt;

   ```html
   <!-- 以空心圆进行无序排序 -->
   <ul type="circle">
       <li>天猫</li>
       <li>聚划算</li>
       <li>天猫超市</li>
     </ul>
   ```

   > 在使用场景上，无序标签多用来做导航栏等具有父子结构的功能。

   - type属性：指定排序时，使用实心圆还是空心圆，默认是实心圆disc，circle是空心圆。

4. 图片标签：&lt;img src=""&gt;

   ```html
   <img src="" alt="" title="">
   ```

   - src属性：source，指定图片的资源地址。

     - 网上的url地址。
     - 本地的绝对路径。
     - 本地的相对路径。

     > 图片和html在同一个文件夹下，它们具有相对关系，可以使用相对路径。如果图片和html不在一个文件夹，即使是父子关系的地址，也不能使用相对地址，必须使用绝对地址。

   - alt属性：图片占位符，当图片加载不成功时，会在图片位置显示alt中的内容。

   - title属性：图片提示符，鼠标移到图片上会显示图片的提示符信息。

5. 超链接标签：&lt;a href=""&gt;展示给用户的链接提示信息&lt;/a&gt;

   - href属性：hyperText reference，超文本引用，用来指定链接地址。
   - target属性：有一个_blank值，指定超链接地址在新标签页中打开。

   > &lt;a href=""&gt;的原始作用：做为锚点，用来快速定位其它HTML内容的位置，常见的目录、置顶功能就是基于此做的。
   >
   > &lt;a href=""&gt;的另一个功能：打电话、发邮件。
   >
   > ```html
   > <a href="tel:18896937086">打电话</a>，如果是手机版的话，会直接调用手机的打电话接口，给tel中的号码打过去。
   > <a href="mailto:550457901@qq.com">发邮件</a>
   > ```
   >
   > &lt;a href=""&gt;的最后一个功能：功能限定符。可以在href中使用javascript：强制插入javascript代码，在用户点击后执行href中的javascript代码。
   >
   > ```html
   > <a href="javascriptl：">点我试试呀</a>，会强行调用href中的javascript代码。
   > ```

6. 表单标签：&lt;form method="" action=""&gt;&lt;/form&gt;

   - method属性：指定发送数据的方式，有get、post两种。
   - action属性：指定数据的接收地址。

   > &lt;form method="" action=""&gt;需要结合&lt;input type="" name="" value=""&gt;来一起使用。

   ```html
   <form method="get/post" action="要把数据发送到的地址">
         <p>
           username:<input type="text" name="username" value="输入用户名" onfocus="" onblur="">
         </p>
         <p>
           password:<input type="password" name="password">
         </p>
         通常密码会使用md5加密，是不可逆的。
         2g数据是不加密的，3/4g会加密数据
         <input type="submit">
     </form>
   ```

   - type属性：指定input输入框接受的数据格式，这里常用的有四种，分别是text、password、submit、login。
   - name属性：指定输入的数据信息最后会赋给哪一个变量，相当于key值。
   - value属性：指定在用户未输入的情况下，默认值是什么。
   - onfocus属性：当鼠标聚焦输入框时。
   - onblur属性：当鼠标离开输入框时。

   > 发送数据需要数据名和数据值，可以理解为一个map，每一项对应一个变量和一个值，即key+value的结构。input标签在输入中会将接受到的内容作为value， name属性的值作为数据名key，value属性如果有值的话，会作为input的提示默认值。用户输入数据后，value是用户输入的值。

   ```html
   <input type="radio" name="" value="">:在单选的name属性中，指定单选标识，用来确定哪几个选项需要单选。具有相同name的是一组单选值,在选定后，value是数据值，发送的方式会是name=value的形式。
   ```

   - type="radio"：表示一个单选输入框。

   > 需要注意的是，在name属性中，要指定单选标识，用来确定哪几个单选输入框做为一个单选项。具有相同name值的输入框组成一组单选操作。value是单选的数据值，当用户单选对应输入框后，其value值做为数据值传给name，以name=value的形式发送出去。

7. Lesson2学习代码

   ```html
   <!DOCTYPE html>
   <html>
   <head>
     <title></title>
     <style type="text/css">
       *{
         margin: 0
         padding:0;
       }
       ul{
         list-style: none;
       }
       li{
         margin: 0 10px;
         float: left;
         color: #f40;
         font-weight: bold;
         font-size: 14px
         height:25px;
         line-height:25px;
       }
       li:hover{
         border-radius: 15px;
         background-color:#f40;
         color:#fff;
       }
     </style>
   </head>
   <body>
     我       很帅
     <div style="">
       HTML几个有用的特殊编码：
       空格文本的展示形式：&nbsp；
       div设置了块大小后，根据空格来区分单词等内容的个数，并基于个数进行div的块内换行。
       但如果不用空格的话，很多单词相互连接，div不能区分出单词个数，因此会在div块内一行显示
       在div内，它认为没有空格就只是一个单词，不会换行显示，会直接显示在一行上，有越界出div块的可能

       空格分隔符：&nbsp；在HTML中，空格的含义是文字分隔符。代表一个空格文本。

       我&nbsp；&nbsp；&nbsp；&nbsp；&nbsp；&nbsp；很帅

       接下来，我要给大家讲解一个标签，叫<div>: &lt;div&gt;

       如何把<>替换掉，使<div>原样输出：&lt;(less than),&gt;(great than)

     </div>

     回车换行符：<br>,它是一个标签，不是一个特殊的HTML编码。多个br表示多个换行。
     单标签，双标签：每一个标签的意义在于把它包裹的文本修饰成需要的样子，但有些标签自己代表功能，不需要在它其中包裹修饰什么内容。这样的自己代表功能，没有包裹的叫做单标签。
     单标签：<meta> <br>

     有序标签：
     type属性有五种值可以选1/a／A／i／I，指定排序序号的样式，type=a表示用26字母进行排序，type=i表示用罗马数字排序
     reversed：逆序排序
     start：指定从第几开始排序，
     <ol type="1" reversed="reversed" start="2">
       <li>marvel</li>
       <li>su 8</li>
       <li>返老还童</li>
       <li>marvel</li>
     </ol>

     无序列表：在使用场景上，多用来做导航栏等具有父子结构的
     只有一个type属性，默认值是disc，表示实心圈，circle是空心圈
     <ul type="circle">
       <li>天猫</li>
       <li>聚划算</li>
       <li>天猫超市</li>
     </ul>

     图片标签；
     src:source,图片的资源地址
       1. 网上url地址
       2. 本地的绝对路径
       3. 本地的相对路径：图片和html在同一个文件夹下，它们具有相对关系，可以使用相对路径。如果图片和html不在一个文件夹，即使是父子关系的地址，也不能使用相对地址，必须使用绝对地址。
     <img src="" alt="" title="">
     alt属性：图片占位符，文字叙述图片，在图片加载不成功时，展示文字信息。
     title属性：图片提示符，鼠标移到图片上会提示图片提示符信息。

     超链接标签：anchor
     <a href="https://www.baidu.com">www.baidu.com</a>
     href：hyperText reference，超文本引用。用来设置链接的目标地址
     在a标签中包裹的任何内容都可以作为一个超链接的用户点击符。
     target属性：_blank，超链接在新的标签页中打开
     a标签最开始的功能，不是作为超链接，而是作为锚点，置顶功能、目录功能就是这样做的
     a标签的另一个功能，打电话，发邮件
     <a href="tel:18896937086">打电话</a>，如果是手机版的话，会直接调用手机的打电话接口，给tel中的号码打过去。
     <a href="mailto:550457901@qq.com">发邮件</a>
     a标签的另一个功能，协议限定符
     <a href="javascriptl：">点我试试呀</a>，会强行调用href中的javascript代码。

     表单标签：可以发送数据
     发送数据需要数据名和数据值，即key+value的结构。
     input标签在输入中会将接受到的内容作为value，
     name属性的值作为数据名key，
     value属性如果有值的话，会作为input的提示默认值。用户输入数据后，value是用户输入的值。
     onfocus:当鼠标聚焦输入框时
     onblur：当鼠标离开输入框时
     <form method="get/post" action="要把数据发送到的地址">
         <p>
           username:<input type="text" name="username" value="输入用户名" onfocus="" onblur="">
         </p>
         <p>
           password:<input type="password" name="password">
         </p>
         通常密码会使用md5加密，是不可逆的。
         2g数据是不加密的，3/4g会加密数据
         <input type="submit">
     </form>
     <input type="radio" name="" value="">:在单选的name属性中，指定单选标识，用来确定哪几个选项需要单选。具有相同name的是一组单选值,在选定后，value是数据值，发送的方式会是name=value的形式。
   </body>
   </html>
   ```


## Lesson3

> 学习form表单的另外两种input用法、css样式的导入方式以及css样式选择器。

### HTML部分

1. form单选框

   - type属性：设置属性值为radio表示该input输入框是单选输入框。
   - name属性：标识某几个input元素是一组单选。
   - value属性：设置单选值，当用户选择后，以？name=value的形式发送该数据。

   ```html
   <form method="get" action="">
       <!-- 单选框 -->
       <h1>Choose your favourite fruit!</h1>
         1.apple<input type="radio" name="fruit" value="apple">
         2.orange<input type="radio" name="fruit" value="orange">
         3.banana<input type="radio" name="fruit" value="banana">
     </form>
   ```

2. form复选框：与单选框的用法相同，只是type值不一样。

   - type属性设置为checkbox，表示该input输入框是复选输入框。

   ```html
   <form method="get" action="">
         <!-- 复选框 -->
         1.apple<input type="checkbox" name="fruit" value="apple">
         2.orange<input type="checkbox" name="fruit" value="orange">
         3.banana<input type="checkbox" name="fruit" value="banana">
     </form>
   ```

3. form输入框默认选中

   - 在input标签的checked属性中，设置checked=“checked”来标识该input为默认选中。
   - 单选框、复选框都使用相同的方式。

   ```html
   <form method="get" action="">
       <!-- 默认选中 -->
         在input标签中，使用checked属性来设置默认选中。
       <h1>Choose your sex!!!</h1>
         male:<input type="radio" name="sex" value="male" checked="checked">
         female:<input type="radio" name="sex" value="female">
     </form>
   ```

4. form下拉菜单

   - 下拉菜单使用select标签，并在select标签内使用option子标签来表示每一个下拉选项。
   - select中name属性：声明该下拉菜单的数据名是什么，如下，数据名是province。
   - option中value属性：声明每一个下拉选项的数据值是什么事，如下，有北京等。
   - 在option之间的内容是显示在页面上的内容，并不是最后的选中值，具体选的什么以value为准。

   > 在option标签中，如果有value属性，那么下拉选项的最后结果以value为准，但在显示在页面上，会以option之间包裹的内容为主。

   ```html
   <form method="get" action="">
       <!-- 下拉菜单功能
       在option标签中，如果有value属性，那么下拉选项的最后结果以value为准，但在显示在页面上，会以option之间包裹的内容为主。 -->
       <h1>Province</h1>
       <select name="province">
         <option value="北京">北京</option>
         <option  value="xxx">上海</option>
         <option>天津</option>
         <option>南京</option>
       </select>
         <input type="submit">
     </form>
   ```

### CSS部分

> html结构、css样式、javascript行为。
>
> css：cascading style sheet，层叠样式表。

1. 引入css的三种方式

   - 行间样式：直接在对应标签中使用style属性设置css样式。

   ```html
   <div style="
         width: 100px;
         height: 100px;
         background-color: red;
       ">
   </div>
   ```

   - 页面级css样式：在head中使用style标签写css代码，然后引入到body的相应位置。

   ```html
   <!DOCTYPE html>
   <html>
     <head>
       <title>Document</title>
       <style type="text/css">
         div{
           width: 100px;
           height: 100px;
           background-color: green;
         }
       </style>
     </head>
     <body>
           <div></div>
     </body>
   </html>
   ```

   - 外部css文件：在外部编写css代码，并在html的head部分的link标签中，导入该css文件。

     > 在link标签中，href属性用来指向需要的css样式。如果该css样式与当前HTML在同一个文件夹下，那么直接使用相对路径就好。如果不在同一路径下，必须使用绝对路径来表明该css文件。
     >
     > link标签类似一个import作用。

     ```html
     <!DOCTYPE html>
     <html>
       <head>
         <title>Document</title>
         <link rel="stylesheet" type="text/css" href="lesson3.css">
       </head>
       <body>
             <div></div>
       </body>
     </html>
     ```

     外部css文件使用后缀css来标识。

     ```css
     div{
       width: 100px;
       height: 100px;
       border-radius: 50%;
       background-color: black;
     }
     ```

2. css选择器

   - id选择器：在标签中使用id来做为该标签的唯一标识，并在对应的css文件中，使用#+id名的方式来设置对应id的css样式。一个元素只能有一个id。

   ```html
   <div id="only"></div>-->

   外部css文件：id选择器
   #only{
     width: 100px;
     height: 100px;
     border-radius: 50%;
     background-color: black;
   }
   ```

   - class选择器：在标签元素中哦使用class属性来设置对应的class名。class选择器与元素是多对多的关系，一个class可以对应多个元素，一个元素也可以对应多个class。在css文件中，使用.+class名来设置对应元素的css样式。

   ```html
   <div class="demo"></div>
   <div class="demo demo1"></div>-->

   外部css文件：class选择器
   .demo{
     width: 100px;
     height: 100px;
     border-radius: 50%;
     background-color: red;
   }

   .demo1{
     width: 100px;
     height: 100px;
     border-radius: 50%;
     background-color: yellow;
   }
   ```

   - 标签选择器：直接使用元素标签做为css样式的对应标志。 无论标签嵌套了多少次，使用标签选择器都可以对应到所有的标签上,如下，两个span标签都可以被css样式作用。

   ```html
   <div></div>
   <span>123</span>
   <div>
       <span>123</span>
   </div>

   外部css文件：标签选择器
   div{
     width: 100px;
     height: 100px;
     border-radius: 50%;
     background-color: black;
   }
   span{
     background-color: red;
   }
   ```

   - 通配符选择器：*作为css样式的标识 ,使用星号会成为全局css选择器，任何标签都会被作用css样式。

   ```html
   <span>123</span>
   <div>123</div>
   <strong>123</strong>

   外部css文件：通配符选择器
   *{
     background-color: yellow;
   }
   ```

   - 属性选择器：包含某一属性的标签都会被相应的css样式作用，属性名定义在[]中。

   ```html
   <div id="only" class="demo"></div>
   外部css文件：属性选择器
   [id]{
       background-color: yellow;
   }
   ```

   > 在div标签中，有id和class两个属性，在外部css文件中，定义了[id]属性的css样式，因此，会在div标签中作用该css样式。

3. css权重

   > css权重值的计算是以256进制计算的。

   - ！important：无穷大
   - 行间样式：1000
   - id：100
   - class、属性、伪类：10
   - 标签、伪元素：1
   - 通配符：0

   > 计算机里，无穷大+1>无穷大。