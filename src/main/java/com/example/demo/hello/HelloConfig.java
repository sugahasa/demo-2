package com.example.demo.hello;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.MyBatchConfigurer;

import lombok.RequiredArgsConstructor;

// バッチ構成クラス
@Configuration // Bean 定義クラスであることを示すアノテーション
@EnableBatchProcessing // Spring Batch を有効にする
@RequiredArgsConstructor // Lombok によるコンストラクタ自動生成
public class HelloConfig {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job fooJob() {
    System.out.println("fooJob メソッドを実行");
    return jobBuilderFactory.get("myFooJob") // 一意となる任意のジョブ名を指定
      .flow(helloStep()) // 実行する Step を指定
      .end()
      .build();
  }

  @Bean
  public Job barJob() {
    System.out.println("barJob メソッドを実行");
    return jobBuilderFactory.get("myBarJob") // 一意となる任意のジョブ名を指定
      .flow(helloStep()) // 実行する Step を指定
      .next(worldStep()) // 実行する Step を指定
      .end()
      .build();
  }

  @Bean
  public Step helloStep() {
    System.out.println("helloStep メソッドを実行");
    return stepBuilderFactory.get("myHelloStep") // 任意のステップ名を指定
      .tasklet(new MessageTasklet("Hello!")) // 実行する Tasklet を指定
      .build();
  }

  @Bean
  public Step worldStep() {
    System.out.println("worldStep メソッドを実行");
    return stepBuilderFactory.get("myWorldStep") // 任意のステップ名を指定
      .tasklet(new MessageTasklet("World!")) // 実行する Tasklet を指定
      .build();
  }
}