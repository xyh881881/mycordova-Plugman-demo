# mycordova-Plugman-demo
使用plugman命令行创建的一个包含ios和android平台的cordova插件外壳，
本项目中包含了cordova app使用该cordova plugin的demo

插件id为xyh插件名为MyPlugin，在前端直接调用MyPlugin.plus([参数1,参数2],function(success){},function error(err){})

创建过程
准备工作
  1.安装 node.js 主要使用了node.js 的包管理工具npm，这个笔者就不详细介绍了，相信作为一个开发者，不管前后端对这个东西都不会说完全不认识。
npm 安装完成了以后可以执行 npm -v 验证下是否安装成功

  2.安装 plugman (全局安装)
  npm install -g plugman
  安装完可以执行 plugman -v 验证下是否安装成功
 
创建插件 
到这一步基本准备工作已完成，下面就可以开始开发你的插件了。
  
  1. cd <到你需要存放插件的地方>
  2. 执行一下命令创建插件
  plugman create --name <pluginName> --plugin_id <pluginID> --plugin_version <version> [--path <directory>] [--variableNAME=VALUE]
  
  参数:
  pluginName: 插件名字
  pluginID: 插件id, egg : coolPlugin 
  oversion: 版本, egg : 0.0.1
  directory:一个绝对或相对路径的目录，该目录将创建插件项目
  variable NAME=VALUE: 额外的描述，如作者信息和相关描述

  egg : plugman create --name MyPlugin --plugin_id xyh --plugin_version 0.0.1
  
  创建成功以后MyPlugins文件夹下会出现一个MyPlugin的文件夹:
  
  3. 为插件添加支持平台
  cd 到你的插件根目录
  执行一下命令:
  plugman platform add --platform_name <platform>
  egg : plugman platform add --platform_name ios
  
  .m 文件当然就是你实现功能的原生代码
  .js 文件就是开放给前端js调用的接口代码
  
  4. 编写你的插件实现功能
  打开 MyPlugin.m 文件，编写一下代码；
  或者根据你的喜欢写自己的功能，因为单个文件不好编辑，所以你也可以先在一个demo中编辑功能代码并测试功能是否有效。
  不过对js开发的类一定要继承CDVPlugin
  
  5. 创建package.json文件
  plugman createpackagejson <directory>
  egg: plugman createpackagejson 
  然后需要你根据提示填写相关的内容，最后生成一个这个插件的描述信息文件。
  
  6. 注意项
  基本到了这一步，一个简单的插件已经创建完了, 下面就是验证工作了。

  不过，还有些地方应该注意的。
  
  在plugin.xml中
  clobbers 中的 target 限制了你如何调用插件
  config-file 中的内容会被配置到工程的config.xml中
  source-file 指定了插件的路径
  
  
  使用插件
  新建或打开一个cordova项目,然后执行:
  cordova plugin add <你的插件(名称\路径\git地址)>
  egg: cordova plugin add /Users/xyh/Desktop/MyPlugins/MyPlugin
  
  此插件可以直接下载到本地安装到项目中然后更改一下函数就可以使用

