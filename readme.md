# 1.H2インメモリDBの利用に関して
- H2Database Access<BR>
http://localhost:8080/h2-console/
- DB<BR>
jdbc:h2:mem:testdb



- build.gradle<BR>
Dependencies setting's example<BR>

```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-batch'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	runtimeOnly 'com.h2database:h2'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.batch:spring-batch-test'
	
	compileOnly 'org.projectlombok:lombok:1.18.18'
	annotationProcessor 'org.projectlombok:lombok:1.18.18'
}
```


# 2.パラメータのデフォルト値を設定する
https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/ja/Ch04_JobParameter.html#Ch04_JobParameter_HowToExtends_PropertyConbination

パラメータを任意とした場合、以下の形式でデフォルト値を設定することができる。

```#{jobParameters[パラメータ名] ?: デフォルト値}```


# 3.SpringBatch起動方法に関して
https://qiita.com/kakasak/items/81a2a91ac22a8a05ee2a


|No|システム・プロパティ|説明|
|:---|:---|:---|
|1|spring.profiles.active|アクティブにするプロファイル|
|2|spring.batch.job.names|実行するジョブの名前|
<BR>※spring.batch.job.names で実行するジョブ名が指定できる。

- 環境変数を設定する場合<BR>
java -Dspring.profiles.active=development -Dspring.batch.job.names=importUserJob -jar gs-batch-processing-0.1.0.jar

- Batch実行その1<BR>
java -Dspring.batch.job.names=importUserJob -jar gs-batch-processing-0.1.0.jar

- Batch実行その2<BR>
java SampleApp.jar --spring.batch.job.names=sample --spring.batch.job.enabled=true

## CommandLineJobRunnerでの実現について


MainClass,StartClass<BR>
https://spring.pleiades.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/