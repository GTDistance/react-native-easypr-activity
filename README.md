## 安装方式一
    react-native install react-native-easypr-activity
## 安装方式二
    npm install react-native-easypr-activity --save
    react-native link react-native-easypr-activity
## 配置
在项目的build.gradle中配置如下
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
## 卸载
	react-native uninstall react-native-easypr-activity
## 使用方法
```
import RecognizeModule from "react-native-easypr-activity"
RecognizeModule.recognize((result)=> {
            alert(result)
        });
```
