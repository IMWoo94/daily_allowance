package com.daily.allowance.batch.runner;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class JobRunnerConfiguration {

	private final JobLauncher jobLauncher;
	private final Job retryPaymentJob;

	/**
	 * TODO
	 * 임의로 스프링에서 제공하는 스케쥴러 적용
	 * 단일 애플리케이션에서는 문제가 없지만 멀티 애플리케이션 환경에서는 문제 될 수 있다.
	 */
	// cron  초 분 시 일 월 주 (년)
	@Scheduled(cron = "30 * * * * 7")
	public void run() throws
		JobInstanceAlreadyCompleteException,
		JobExecutionAlreadyRunningException,
		JobParametersInvalidException,
		JobRestartException {
		// JobParameter 미변경 시 동일 Job 으로 인식
		JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("date", LocalDateTime.now())
			.toJobParameters();
		jobLauncher.run(retryPaymentJob, jobParameters);
	}
}
