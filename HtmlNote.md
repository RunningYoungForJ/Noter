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

   - <p>标签：段落标签，在该标签中的内容会独立成段显示。
   - <strong>标签：加粗标签。
   - <em>标签：斜体标签。
   - <del>标签：中划线标签，标签中的内容中间会有划线。
   - <h1~6>标签：标题标签，标签中的内容会比正文加粗，字号更大，并且独立成段。
   - <address>标签：地址标签，但很少这么直接去使用这个标签显示地址，一般都在js中做优化。
   - <div>标签：块标签，默认多个div标签的布局是上下的。而且具有捆绑管理的特点，能够统一管理块内的所有标签内容。
   - <span>标签：与div标签类似，但默认的布局不是上下的。但也具有捆绑管理的特点。

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

