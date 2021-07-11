package com.example.demo.hello;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

// Tasklet 実装クラス
@RequiredArgsConstructor // Lombok によるコンストラクタ自動生成
@Component
@Scope("step")
public class MessageTasklet implements Tasklet {

  // 出力するメッセージ
  private final String message;
  
  @Value("#{jobParameters[str]}")  // (2)
  private String str;

  // 具体的な処理を実装するメソッド
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    System.out.println("Message: " + message); // メッセージを出力
    return RepeatStatus.FINISHED; // 処理が終了したことを示す値を返す
  }
}