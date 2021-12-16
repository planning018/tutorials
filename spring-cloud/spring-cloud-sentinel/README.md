# Sentinel 功能测试

1. 启动 Sentinel Dashboard

   ```shell
   java -Dserver.port=8081 -Dcsp.sentinel.dashboard.server=localhost:8081 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar
   ```

2. 启动 application，添加如下参数

   ```shell
   -Dcsp.sentinel.dashboard.server=localhost:8081
   ```

   

