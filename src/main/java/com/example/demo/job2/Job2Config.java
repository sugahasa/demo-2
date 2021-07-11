package com.example.demo.job2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

// バッチ構成クラス
@Configuration // Bean 定義クラスであることを示すアノテーション
@EnableBatchProcessing // Spring Batch を有効にする
@RequiredArgsConstructor // Lombok によるコンストラクタ自動生成
public class Job2Config {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job job2FooJob() {
    System.out.println("fooJob メソッドを実行(param)");
    return jobBuilderFactory.get("job2FooJob") // 一意となる任意のジョブ名を指定
      .flow(job2HelloStep()) // 実行する Step を指定
      .end()
      .build();
  }

  @Bean
  public Job job2BarJob() {
    System.out.println("barJob メソッドを実行(param)");
    return jobBuilderFactory.get("job2BarJob") // 一意となる任意のジョブ名を指定
      .flow(job2HelloStep()) // 実行する Step を指定
      .next(job2WorldStep()) // 実行する Step を指定
      .end()
      .build();
  }

  @Bean
  public Step job2HelloStep() {
    System.out.println("helloStep メソッドを実行(param)");
    return stepBuilderFactory.get("job2HelloStep") // 任意のステップ名を指定
      .tasklet(new Job2Tasklet("Hello!")) // 実行する Tasklet を指定
      .build();
  }

  @Bean
  public Step job2WorldStep() {
    System.out.println("worldStep メソッドを実行(param)");
    return stepBuilderFactory.get("job2WorldStep") // 任意のステップ名を指定
      .tasklet(new Job2Tasklet("World!")) // 実行する Tasklet を指定
      .build();
  }
}