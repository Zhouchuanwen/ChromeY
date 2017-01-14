### 简易测试实践项目:chrome应用篇


####authors:
    Alan
    Fizzxiao
    kameryf

####介绍:
    获取本地chrome浏览器历史记录,对记录的数据做可视化.

####前端：
    grunt+jquery

####后端：
    spring*


####rest api:
    数据时间范围＝> http://127.0.0.1:8080/history/range
    按照时间查询记录＝> http://127.0.0.1:8080/history/query?start=1483397007818&end=1484398007818
    按照时间查询访问次数＝> http://127.0.0.1:8080/history/count?start=1483397007818&end=1484398007818