package com.daily.allowance.batch.job;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.daily.allowance.domain.payment.dto.response.PaymentResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RetryPaymentJobConfiguration {

	private final SqlSessionFactory sqlSessionFactory;

	@Bean
	public Job retryPaymentJob(
		JobRepository jobRepository,
		Step retryPaymentStep
	) {
		return new JobBuilder("RetryPaymentJob", jobRepository)
			.start(retryPaymentStep)
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@Bean
	public Step retryPaymentStep(
		MyBatisPagingItemReader<PaymentResponseDto> myBatisPagingItemReader,
		PlatformTransactionManager platformTransactionManager,
		JobRepository jobRepository
	) {
		return new StepBuilder("RetryPaymentStep", jobRepository)
			.chunk(5, platformTransactionManager)
			.reader(myBatisPagingItemReader)
			.processor(it -> {
				/**
				 * TODO 데이터를 기반으로 재 처리 대상 가공
				 * 재 처리 구현 로직
				 */
				return null;
			})
			.writer(it -> {
				/**
				 * TODO 재 처리 DB 등록
				 * 재 처리 결과를 DB에 저장
				 */
			})
			.build();
	}

	@Bean
	public MyBatisPagingItemReader<PaymentResponseDto> myBatisPagingItemReader() {
		return new MyBatisPagingItemReaderBuilder<PaymentResponseDto>()
			.sqlSessionFactory(sqlSessionFactory)
			.pageSize(5)
			.queryId("com.daily.allowance.domain.payment.mapper.PaymentMapper.searchRetryPayment")
			.build();
	}

}
