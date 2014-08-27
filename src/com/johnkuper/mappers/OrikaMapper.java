package com.johnkuper.mappers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.dataformapping.BillingDetails;
import com.johnkuper.dataformapping.CreditCard;

public class OrikaMapper {

	final static Logger logger = LoggerFactory.getLogger(OrikaMapper.class);

	private void configureFactory(Class<?> sourceClass, Class<?> destiClass) {

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		mapperFactory.classMap(sourceClass, destiClass)
				.field("number", "CC_number").field("cardType", "CC_cardType")
				.field("expMonth", "CC_exp_month")
				.field("expYear", "CC_exp_year").byDefault().register();
		configureFacade(mapperFactory);
	}

	private void configureFacade(MapperFactory factory) {

		MapperFacade mapper = factory.getMapperFacade();
		CreditCard card = new CreditCard();
		BillingDetails bilDetails = mapper.map(card, BillingDetails.class);
		sourceDetailsData(card);
		outputDetailsData(bilDetails);

	}

	public void runMapper(Class<?> sourceClass, Class<?> destiClass) {
		configureFactory(sourceClass, destiClass);
	}

	private void outputDetailsData(BillingDetails details) {
		logger.debug("======================");
		logger.debug("Output class data");
		logger.debug(details.getCC_number());
		logger.debug(details.getCC_cardType());
		logger.debug(String.valueOf(details.getCC_exp_month()));
		logger.debug(String.valueOf(details.getCC_exp_year()));

	}

	private void sourceDetailsData(CreditCard card) {
		logger.debug("======================");
		logger.debug("Source class data");
		logger.debug(card.getNumber());
		logger.debug(card.getCardType());
		logger.debug(String.valueOf(card.getExpMonth()));
		logger.debug(String.valueOf(card.getExpYear()));
	}
}