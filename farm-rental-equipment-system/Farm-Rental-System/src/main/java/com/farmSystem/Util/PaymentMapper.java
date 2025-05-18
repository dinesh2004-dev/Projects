package com.farmSystem.Util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Payments;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
	 	@Mapping(target = "booking", source = "booking")
	    @Mapping(target = "amount", source = "booking.totalCost")
	    @Mapping(target = "paymentStatus", source = "booking.paymentStatus")
	    @Mapping(target = "razorPayOrderId", source = "razorpayOrderId")
	    @Mapping(target = "razorPayPayMentId", source = "razorpayPaymentId")
	    @Mapping(target = "payoutStatus", constant = "false")
	    Payments toPayments(Bookings booking, String razorpayOrderId, String razorpayPaymentId);
}
