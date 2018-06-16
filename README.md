# Maxent
java实现，此最大熵结合了java上opennlp包的最大熵及python上nltk的最大熵部分实现，并进行了一些改动。算法包含gis及iis实现，，内含详细中文注释，附上训练及测试数据

# 训练执行截图:
![image](https://github.com/sccuncai/Maxent/raw/master/Screenshots/1.png)


# 执行函数截图：
![image](https://github.com/sccuncai/Maxent/raw/master/Screenshots/2.png)

在MaxentTrainer 类中进行主要参数设置
![image](https://github.com/sccuncai/Maxent/raw/master/Screenshots/3.png)
需要说明的是：
1.isDiscretization=false，输入特征数据为类别类型
2.isDiscretization=true，输入特征数据为数值类型，同时需要设置 section 的值


# 适用范围及数据格式
训练数据：
1.类别类型数据
"featureName1=a featureName2=b featureName3=c 1"

2.数值类型数据
"featureName1=10 featureName2=11 featureName3=12 1"

测试数据：
如提供的 test_data.txt，最后一列标记结果是用来验证预测结果和标记结果用的。
意思是训练数据可以为，例如："featureName1=10 featureName2=11 featureName3=12",省略最后的标记，作为线上预测

=号左边为特征名称，右边为值，最后一列为标记结果

# 注意事项
1.如果输入数据同时包含类别类型和数值类型，那么麻烦去数值处理的类 ContinuumToDiscretization 中自行处理，或者新增自定义的类。
2.如果实际迭代次数小于设置的迭代次数，去类 ConvergenceCheck 中调整下参数（使用iss的话 MaxentClassifierWithIis 类中也可以调整）
![image](https://github.com/sccuncai/Maxent/raw/master/Screenshots/4.png)

123