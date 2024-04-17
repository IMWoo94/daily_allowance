package com.daily.allowance.domain.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMissionRequestDto {
	@NotNull
	private Long missionId;
	@NotNull
	private String missionName;
	@NotNull
	private Integer missionAmount;
}
